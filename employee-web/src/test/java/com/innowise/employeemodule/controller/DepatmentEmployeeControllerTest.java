package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.service.DepartmentEmployeeService;
import com.innowise.employeemodule.service.DepartmentService;
import com.innowise.employeemodule.service.EmployeeService;
import com.innowise.employeemodule.service.PersonalInfoService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DepatmentEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private DepartmentEmployeeService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PersonalInfoService personalInfoService;

    private Long departmentEmployeeId;

    private Long employeeId;

    private Long departmentId;

    private DepartmentEmployee departmentEmployee;


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
        Department department = new Department();
        department.setName("test");
        department = departmentService.add(department);
        departmentId = department.getId();
        departmentEmployee = new DepartmentEmployee();
        departmentEmployee.setEmployee(employee);
        departmentEmployee.setDepartment(department);
        departmentEmployee = service.add(departmentEmployee);
        departmentEmployeeId = departmentEmployee.getId();
    }

    @Before
    public void before(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        create();
    }


    @Test
    public void testGetCurrentDepartmentEmployee() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/department-employee/current/" + employeeId))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.department.name").value("test"))
                .andExpect(jsonPath("$.department.id").value(departmentId))
                .andExpect(jsonPath("$.employee.id").value(employeeId))
                .andReturn();
    }

    @Test
    public void testChangeDepartment() throws Exception {
        Employee employee = employeeService.getById(employeeId);
        Department department = new Department();
        department.setName("new test" + Math.random());
        department = departmentService.add(department);
        Long newdepartmentId = department.getId();
        MvcResult mvcResult = this.mockMvc.perform(get("/department-employee/change")
        .param("newdepartment_id", String.valueOf(newdepartmentId)).param("employee_id", String.valueOf(employeeId)).param("description", "Description"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Employee '" + employee.getPersonalInfo().getFirstName() + " "
                        + employee.getPersonalInfo().getLastName() + "' moved to department '" + department.getName() + "'"))
                .andReturn();
        departmentService.deleteById(newdepartmentId);
    }

    @After
    public void after(){
        employeeService.deleteById(employeeId);
        departmentService.deleteById(departmentId);
    }

}
