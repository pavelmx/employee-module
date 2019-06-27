package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.repository.PositionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PositionRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest<Position, PositionRepository> {

    private Position position;

    @Autowired
    private PositionRepository positionRepository;

    @Test
    public void existsByNameTest(){
        createObject();
        positionRepository.saveAndFlush(position);
        boolean exists = positionRepository.existsByName(position.getName());
        Assert.assertTrue(exists);
    }

    @Test
    public void findByIsActiveTrueTest(){
        createObject();
        position.setActive(true);
        positionRepository.saveAndFlush(position);
        List<Position> positionList = positionRepository.findByIsActiveTrue();
        Assert.assertEquals(Collections.singletonList(position), positionList);
    }

    @Override
    public Position createObject() {
        this.position = Position.builder().isActive(true).name("God " + Math.random()).build();
        return this.position;
    }

    @Override
    public Position updateObject(Position entity) {
        entity = Position.builder().isActive(false).name("God " + Math.random()).id(entity.getId()).build();
        return entity;
    }

}