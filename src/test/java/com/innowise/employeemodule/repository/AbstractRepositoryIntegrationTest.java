package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.BaseEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?currentSchema=employee_schema_test",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public abstract class AbstractRepositoryIntegrationTest<E extends BaseEntity, R extends JpaRepository<E, Long>> {

    @Autowired
    private R repository;

    private E entity;

    protected abstract E createObject();

    protected abstract E updateObject(E entity);

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
        E entityFromDB =  repository.findById(entity.getId()).get();
        Assert.assertEquals(entity, entityFromDB);
    }

    @Test
    public void testFindAll(){
        repository.saveAndFlush(entity);
        List<E> entities = repository.findAll();
        Assert.assertEquals(entities, Collections.singletonList(entity));
    }

    @Test
    public void testUpdate(){
        repository.save(entity);
        E newEntity = updateObject(entity);
        repository.save(newEntity);
        E entityFromDB =  repository.getOne(entity.getId());
        Assert.assertEquals(newEntity, entityFromDB);
    }

    @Test
    public void testDelete(){
        repository.save(entity);
        repository.delete(entity);
        boolean entityFromDB =  repository.findById(entity.getId()).isPresent();
        Assert.assertFalse(entityFromDB);
    }

    @Test
    public void testDeleteAll(){
        repository.save(entity);
        repository.deleteAll();
        List<E> entitiesFromDB =  repository.findAll();
        Assert.assertEquals(0, entitiesFromDB.size());
    }

}
