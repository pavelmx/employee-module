package com.innowise.employeemodule.db;

import com.innowise.employeemodule.entity.AbstractEntity;
import com.innowise.employeemodule.repository.AbstractRepository;
import com.querydsl.core.types.dsl.EntityPathBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public abstract class AbstractRepositoryTest<E extends AbstractEntity, R extends JpaRepository<E, Long>> {

    @Autowired
    private R repository;

    private E entity;

    public abstract E createObject();

    public abstract E createObject(long id);

    public abstract Long getObjectId();

    @Before
    public void setUp() {
        entity = createObject();
    }

    @After
    public void tearDown() {
        entity = null;
        repository.deleteAll();
    }

    @Test
    public void testSave(){
        E entityFromDB = repository.saveAndFlush(entity);
        Assert.assertEquals(entity, entityFromDB);
    }

    @Test
    public void testFindById(){
        entity = repository.saveAndFlush(entity);
        E entityFromDB =  repository.findById(getObjectId()).get();
        Assert.assertEquals(entity, entityFromDB);
    }

    @Test
    public void testFindAll(){
        repository.saveAndFlush(entity);
        List<E> entities = repository.findAll();
        Assert.assertEquals(entities, Arrays.asList(entity));
    }

    @Test
    public void testUpdate(){
        repository.save(entity);
        E newEntity = createObject(getObjectId());
        repository.save(newEntity);
        E entityFromDB =  repository.getOne(getObjectId());
        Assert.assertEquals(newEntity, entityFromDB);
    }

    @Test
    public void testDelete(){
        repository.save(entity);
        repository.delete(entity);
        boolean entityFromDB =  repository.findById(getObjectId()).isPresent();
        Assert.assertEquals(false, entityFromDB);
    }

    @Test
    public void testDeleteAll(){
        repository.save(entity);
        repository.deleteAll();
        List<E> entitiesFromDB =  repository.findAll();
        Assert.assertEquals(0, entitiesFromDB.size());
    }

}
