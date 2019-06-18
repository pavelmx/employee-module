package com.innowise.employeemodule.db;

import com.innowise.employeemodule.entity.*;
import com.innowise.employeemodule.repository.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PositionEmployeeRepositoryTest extends AbstractRepositoryTest<PositionEmployee, PositionEmployeeRepository> {

    private PositionEmployee positionEmployee;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public PositionEmployee createObject() {
        PersonalInfo personalInfo = PersonalInfo.builder()
                .firstName("Vasya")
                .lastName("Ivanov")
                .email("p@mail.ru")
                .adress("Oktybrskaya Street")
                .description("CCCCC")
                .phoneNumber("++9874546")
                .skype("skype")
                .build();
        personalInfo = personalInfoRepository.saveAndFlush(personalInfo);
        Employee employee = Employee.builder()
                .personalInfo(personalInfo)
                .isActive(true)
                .build();
        employee = employeeRepository.saveAndFlush(employee);
        Position position = Position.builder().name("GOD of programming").isActive(true).build();
        position = positionRepository.saveAndFlush(position);
        this.positionEmployee = PositionEmployee.builder()
                .description("Description One")
                .startDateForPosition(LocalDateTime.now().minusDays(20))
                .endDateForPosition(LocalDateTime.now().minusDays(5))
                .employee(employee)
                .position(position)
                .build();
        return this.positionEmployee;
    }

    @Override
    public PositionEmployee createObject(long id) {
        PersonalInfo personalInfo = PersonalInfo.builder()
                .firstName("katya")
                .lastName("Pushnova")
                .email("katya@mail.ru")
                .adress("Proletarskaya Street")
                .description("dfgvrgrfgvb")
                .phoneNumber("++98126574546")
                .skype("skype")
                .build();
        personalInfo = personalInfoRepository.saveAndFlush(personalInfo);
        Employee employee = Employee.builder()
                .personalInfo(personalInfo)
                .isActive(true)
                .build();
        employee = employeeRepository.saveAndFlush(employee);
        Position position = Position.builder().name("GOD of cooking").isActive(true).build();
        position = positionRepository.saveAndFlush(position);
        this.positionEmployee = PositionEmployee.builder()
                .description("Second Description")
                .startDateForPosition(LocalDateTime.now().minusDays(50))
                .endDateForPosition(LocalDateTime.now().minusDays(15))
                .employee(employee)
                .position(position)
                .id(id)
                .build();
        return this.positionEmployee;
    }

    @Override
    public Long getObjectId() {
        return this.positionEmployee.getId();
    }

}