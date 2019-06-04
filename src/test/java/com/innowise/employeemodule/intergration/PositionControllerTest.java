package com.innowise.employeemodule.intergration;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.repository.PositionRepository;
import com.innowise.employeemodule.service.DepartmentService;
import com.innowise.employeemodule.service.PositionService;
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
public class PositionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PositionService service;

    @Autowired
    private PositionRepository repository;

    private Long positionId;

    private Position position;


    private void create(){
        position = new Position();
        position.setName("test position");
        position = service.add(position);
        positionId = position.getId();
    }

    @Before
    public void before(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        create();
    }

    @Test
    public void testGetById() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/position/" + positionId))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(positionId))
                .andExpect(jsonPath("$.name").value(position.getName()))
                .andReturn();

        Assert.assertEquals("application/json;charset=UTF-8",
                mvcResult.getResponse().getContentType());
    }

    @Test
    public void testAdd() throws Exception {
        Position position = new Position();
        position.setName("new position");
        MvcResult mvcResult = this.mockMvc.perform(post("/position")
                .content(JsonUtil.toJson(position)).contentType("application/json;charset=UTF-8")
                ).andDo(print()).andExpect(status().isCreated())
                .andReturn();
        String[] array =  mvcResult.getResponse().getContentAsString().split(",");
        Long id = (Long.valueOf(array[2].substring(5)));
        service.deleteById(id);
    }

    @Test
    public void testUpdate() throws Exception {
        position.setName("test update position");
        MvcResult mvcResult = this.mockMvc.perform(put("/position")
                .content(JsonUtil.toJson(position)).contentType("application/json;charset=UTF-8")
        ).andDo(print()).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {
        Position position = new Position();
        position.setName("test delete position");
        Long id = service.add(position).getId();
        MvcResult mvcResult = this.mockMvc.perform(delete("/position/" + id))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Position with id: '"+ id +"' was deleted"))
                .andReturn();

    }

//    @Test
//    public void testAllDelete() throws Exception {
//        Position position1 = new Position();
//        position1.setName("test1 delete position1");
//        Long id1 = service.add(position1).getId();
//        Position position2 = new Position();
//        position2.setName("test2 delete position2");
//        Long id2 = service.add(position2).getId();
//        MvcResult mvcResult = this.mockMvc.perform(delete("/position/all"))
//                .andDo(print()).andExpect(status().isOk())
//                .andExpect(jsonPath("message").value("All positions were deleted"))
//                .andReturn();
//
//    }

    @Test
    public void testEnable() throws Exception {
        Position position = service.getById(positionId);
        position.setActive(false);
        repository.save(position);
        MvcResult mvcResult = this.mockMvc.perform(get("/position/enable/" + positionId))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Position '" + position.getName() + "' enabled"))
                .andReturn();

    }

    @Test
    public void testGetEnableAll() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/position/enable/all"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();
        Assert.assertNotEquals(mvcResult.getResponse().getContentAsString().length(), 0);
    }

    @Test
    public void testDisable() throws Exception {
        Position position = service.getById(positionId);
        position.setActive(true);
        repository.save(position);
        MvcResult mvcResult = this.mockMvc.perform(get("/position/disable/" + positionId))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Position '" + position.getName() + "' disabled"))
                .andReturn();

    }

    @After
    public void after(){
      service.deleteById(positionId);
    }

}
