package com.innowise.employeemodule.db;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.PositionRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PositionRepositoryTest extends AbstractRepositoryTest<Position, PositionRepository> {

    private Position position;

    @Override
    public Position createObject() {
        this.position = Position.builder().isActive(true).name("God " + Math.random()).build();
        return this.position;
    }

    @Override
    public Position createObject(long id) {
        this.position = Position.builder().isActive(false).name("God " + Math.random()).id(id).build();
        return this.position;
    }

    @Override
    public Long getObjectId() {
        return this.position.getId();
    }

}