package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.service.DepartmentService;
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
public class DepatmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private DepartmentService service;

    private Long departmentId;

    private Department department;


    private void createDepartment(){
        department = new Department();
        department.setName("test department");
        department = service.add(department);
        departmentId = department.getId();
    }

    @Before
    public void before(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        createDepartment();
    }

    @Test
    public void testGetById() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/department/" + departmentId))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departmentId))
                .andExpect(jsonPath("$.name").value(department.getName()))
                .andReturn();

        Assert.assertEquals("application/json;charset=UTF-8",
                mvcResult.getResponse().getContentType());
    }

//    @Test
//    public void testGetAll() throws Exception {
//        MvcResult mvcResult = this.mockMvc.perform(get("/department/all"))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("$.[0].id").value(departmentId))
//                .andExpect(jsonPath("$.[0].name").value(department.getName()))
//                .andReturn();
//
//        Assert.assertEquals("application/json;charset=UTF-8",
//                mvcResult.getResponse().getContentType());
//    }

    @Test
    public void testAdd() throws Exception {
        Department department = new Department();
        department.setName("new department");
        MvcResult mvcResult = this.mockMvc.perform(post("/department?manager_id=")
                .content(JsonUtil.toJson(department)).contentType("application/json;charset=UTF-8")
                ).andDo(print()).andExpect(status().isCreated())
                .andReturn();
        String[] array =  mvcResult.getResponse().getContentAsString().split(",");
        Long id = (Long.valueOf(array[2].substring(5)));
        service.deleteById(id);
    }

    @Test
    public void testUpdate() throws Exception {
        department.setName("test update department");
        MvcResult mvcResult = this.mockMvc.perform(put("/department?manager_id=")
                .content(JsonUtil.toJson(department)).contentType("application/json;charset=UTF-8")
        ).andDo(print()).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {
        Department department = new Department();
        department.setName("test delete department");
        Long id = service.add(department).getId();
        MvcResult mvcResult = this.mockMvc.perform(delete("/department/" + id))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Department with id: '"+ id +"' was deleted"))
                .andReturn();

    }

//    @Test
//    public void testAllDelete() throws Exception {
//        Department department1 = new Department();
//        department.setName("test1 delete department");
//        Long id1 = service.add(department).getId();
//        Department department2 = new Department();
//        department.setName("test2 delete department");
//        Long id2 = service.add(department).getId();
//        MvcResult mvcResult = this.mockMvc.perform(delete("/department/all"))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("message").value("All departments were deleted"))
//                .andReturn();
//
//    }

    @After
    public void after(){
      service.deleteById(departmentId);
    }

}
