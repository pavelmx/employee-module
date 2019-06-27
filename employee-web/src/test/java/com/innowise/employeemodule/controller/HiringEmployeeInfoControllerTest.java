package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.HiringEmployeeInfoRepository;
import com.innowise.employeemodule.service.EmployeeService;
import com.innowise.employeemodule.service.HiringEmployeeInfoService;
import com.innowise.employeemodule.service.PersonalInfoService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HiringEmployeeInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private HiringEmployeeInfoService service;

    @Autowired
    private PersonalInfoService personalInfoService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HiringEmployeeInfoRepository repository;

    private Long id;

    private Long employeeId;

    private HiringEmployeeInfo hiringEmployeeInfo;


    private void create(){
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setPhoneNumber("gre");
        personalInfo.setSkype("jtty");
        personalInfo.setEmail("liu");
        personalInfo.setAdress("wqdq");
        personalInfo.setLastName("jtyj");
        personalInfo.setFirstName("test");
        personalInfo = personalInfoService.add(personalInfo);
        Employee employee = new Employee();
        employee.setPersonalInfo(personalInfo);
        employee = employeeService.add(employee);
        employeeId = employee.getId();
        hiringEmployeeInfo = new HiringEmployeeInfo();
        hiringEmployeeInfo.setEmployee(employee);
        hiringEmployeeInfo = service.add(hiringEmployeeInfo);
        id = hiringEmployeeInfo.getId();
    }

    @Before
    public void before(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        create();
    }

    @Test
    public void testGetById() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/hiring-employee-info/" + id))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.employee.personalInfo.firstName").value("test"))
                .andReturn();

        Assert.assertEquals("application/json;charset=UTF-8",
                mvcResult.getResponse().getContentType());

    }

    @After
    public void after(){
        employeeService.deleteById(employeeId);
    }

}
