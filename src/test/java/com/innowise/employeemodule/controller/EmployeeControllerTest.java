package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.*;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.PositionRepository;
import com.innowise.employeemodule.service.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private PersonalInfoService pservice;

    @Autowired
    private HiringEmployeeInfoService hiringEmployeeInfoService;

    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    @Autowired
    private PositionEmployeeService positionEmployeeService;

    private Long employeeId;
    private Long positionId;

    private Employee employee;

    private Position position;

    private PersonalInfo personalInfo;

    private void createEmployee(){
        employee = new Employee();
        personalInfo = new PersonalInfo();
        personalInfo.setPhoneNumber("test phone");
        personalInfo.setLastName("test last name");
        personalInfo.setFirstName("test first name");
        personalInfo.setEmail("test email");
        personalInfo.setAdress("test adress");
        personalInfo.setSkype("test skype");
        personalInfo = pservice.add(personalInfo);
        employee.setPersonalInfo(personalInfo);
        employee = service.add(employee);
        employeeId = employee.getId();
    }

    private void createPosition(){
        position = new Position();
        position.setActive(true);
        position.setName("Position " + Math.random());
        position = positionService.add(position);
        positionId = position.getId();
    }

    @Before
    public void before(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        createPosition();
        createEmployee();
    }

    @Test
    public void testGetById() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/employee/" + employeeId))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employeeId))
                .andExpect(jsonPath("$.active").value(true))
                .andExpect(jsonPath("$.personalInfo.firstName").value(personalInfo.getFirstName()))
                .andExpect(jsonPath("$.personalInfo.lastName").value(personalInfo.getLastName()))
                .andExpect(jsonPath("$.personalInfo.skype").value(personalInfo.getSkype()))
                .andExpect(jsonPath("$.personalInfo.adress").value(personalInfo.getAdress()))
                .andExpect(jsonPath("$.personalInfo.email").value(personalInfo.getEmail()))
                .andExpect(jsonPath("$.personalInfo.phoneNumber").value(personalInfo.getPhoneNumber()))
                .andReturn();

        Assert.assertEquals("application/json;charset=UTF-8",
                mvcResult.getResponse().getContentType());
    }

//    @Test
//    public void testGetAll() throws Exception {
//        MvcResult mvcResult = this.mockMvc.perform(get("/employee/all"))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.[0].id").value(employeeId))
//                .andExpect(jsonPath("$.[0].active").value(true))
//                .andExpect(jsonPath("$.[0].personalInfo.firstName").value(personalInfo.getFirstName()))
//                .andExpect(jsonPath("$.[0].personalInfo.lastName").value(personalInfo.getLastName()))
//                .andExpect(jsonPath("$.[0].personalInfo.skype").value(personalInfo.getSkype()))
//                .andExpect(jsonPath("$.[0].personalInfo.adress").value(personalInfo.getAdress()))
//                .andExpect(jsonPath("$.[0].personalInfo.email").value(personalInfo.getEmail()))
//                .andExpect(jsonPath("$.[0].personalInfo.phoneNumber").value(personalInfo.getPhoneNumber()))
//                .andReturn();
//
//        Assert.assertEquals("application/json;charset=UTF-8",
//                mvcResult.getResponse().getContentType());
//    }

    @Test
    public void testAdd() throws Exception {
        Employee employee = new Employee();
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setPhoneNumber("test post phone");
        personalInfo.setLastName("test post last name");
        personalInfo.setFirstName("test post first name");
        personalInfo.setEmail("test post email");
        personalInfo.setAdress("test post adress");
        personalInfo.setSkype("test post skype");
        employee.setPersonalInfo(personalInfo);
        MvcResult mvcResult = this.mockMvc.perform(post("/employee?position_id=" + positionId + "&department_id=1")
                .content(JsonUtil.toJson(employee)).contentType("application/json;charset=UTF-8")
        ).andDo(print()).andExpect(status().isCreated())
                .andReturn();
        String[] array =  mvcResult.getResponse().getContentAsString().split(",");
        Long id = (Long.valueOf(array[2].substring(5)));
        service.deleteById(id);
    }

    @Test
    public void testUpdate() throws Exception {
        personalInfo.setSkype("test update skype");
        employee.setPersonalInfo(personalInfo);
        MvcResult mvcResult = this.mockMvc.perform(put("/employee")
                .content(JsonUtil.toJson(employee)).contentType("application/json;charset=UTF-8")
        ).andDo(print()).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDeleteById() throws Exception {
        Employee employee = new Employee();
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setEmail("test delete");
        personalInfo.setSkype("test delete");
        personalInfo.setPhoneNumber("test delete");
        employee.setPersonalInfo(personalInfo);
        employee = service.create(employee,positionId,1L);
        Long id = employee.getId();
        MvcResult mvcResult = this.mockMvc.perform(delete("/employee/" + id))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Employee with id: '"+ id +"' was deleted"))
                .andReturn();

    }

    @Test
    public void testDismissal() throws Exception {
        Employee employee = new Employee();
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setEmail("test dismissal");
        personalInfo.setSkype("test dismissal");
        personalInfo.setPhoneNumber("test dismissal");
        employee.setPersonalInfo(personalInfo);
        employee = service.create(employee, positionId, 1L);
        Long id = employee.getId();
        MvcResult mvcResult = this.mockMvc.perform(get("/employee/dismissal/" + id))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Employee with id: '" + id + "' was dismissed"))
                .andReturn();

        Assert.assertFalse(service.getById(id).isActive());
        service.deleteById(id);
    }

    @Test
    public void testRecovery() throws Exception {
        Employee employee = new Employee();
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setEmail("test recovery");
        personalInfo.setSkype("test recovery");
        personalInfo.setPhoneNumber("test recovery");
        employee.setPersonalInfo(personalInfo);
        employee = service.create(employee, positionId,1L);
        employee.setActive(false);
        employee = repository.save(employee);
        Long id = employee.getId();
        MvcResult mvcResult = this.mockMvc.perform(get("/employee/recovery/" + id)
        .param("position_id", positionId.toString()).param("department_id", "1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Employee with id: '"+ id +"' was reinstated"))
                .andReturn();
        Assert.assertTrue(service.getById(id).isActive());
        service.deleteById(id);
    }

    @After
    public void after(){
        service.deleteById(employeeId);
        service.deleteAll();
    }

}
