package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.*;
import com.innowise.employeemodule.service.*;
import org.junit.After;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PositionEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PositionEmployeeService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private PersonalInfoService personalInfoService;

    private Long positionEmployeeId;

    private Long employeeId;

    private Long positionId;

    private PositionEmployee positionEmployee;


    private void create(){
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setPhoneNumber("gre");
        personalInfo.setSkype("jtty");
        personalInfo.setEmail("liu");
        personalInfo.setAdress("wqdq");
        personalInfo.setLastName("jtyj");
        personalInfo.setFirstName("lijdfs");
        personalInfo = personalInfoService.add(personalInfo);
        Employee employee = new Employee();
        employee.setPersonalInfo(personalInfo);
        employee = employeeService.add(employee);
        employeeId = employee.getId();
        Position position = new Position();
        position.setName("test position");
        position = positionService.add(position);
        positionId = position.getId();
        positionEmployee = new PositionEmployee();
        positionEmployee.setEmployee(employee);
        positionEmployee.setPosition(position);
        positionEmployee = service.add(positionEmployee);
        positionEmployeeId = positionEmployee.getId();
    }

    @Before
    public void before(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        create();
    }


    @Test
    public void testGetCurrentPositionEmployee() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/position-employee/current/" + employeeId))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.position.name").value("test position"))
                .andExpect(jsonPath("$.position.active").value(true))
                .andExpect(jsonPath("$.position.id").value(positionId))
                .andExpect(jsonPath("$.employee.id").value(employeeId))
                .andReturn();
    }

    @Test
    public void testChangePosition() throws Exception {
        Employee employee = employeeService.getById(employeeId);
        Position position = new Position();
        position.setName("new test" + Math.random());
        position = positionService.add(position);
        Long newPositionId = position.getId();
        MvcResult mvcResult = this.mockMvc.perform(get("/position-employee/change")
        .param("newposition_id", String.valueOf(newPositionId)).param("employee_id", String.valueOf(employeeId)).param("description", "Description"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Employee '" + employee.getPersonalInfo().getFirstName() + " "
                        + employee.getPersonalInfo().getLastName() + "' promoted to a position '" + position.getName() + "'"))
                .andReturn();
        positionService.deleteById(newPositionId);
    }

    @After
    public void after(){
        employeeService.deleteById(employeeId);
        positionService.deleteById(positionId);
    }

}
