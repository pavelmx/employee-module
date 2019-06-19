package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest<Employee, EmployeeRepository> {

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    private Employee employee;

    @Override
    public Employee createObject() {
        PersonalInfo personalInfo = PersonalInfo.builder()
                .firstName("Vasya")
                .lastName("Pupkin")
                .email("Mail@mail.ru")
                .adress("Lenina Street")
                .description("AAAAAAAA")
                .phoneNumber("+375298785653")
                .skype("skype")
                .build();
        personalInfo = personalInfoRepository.saveAndFlush(personalInfo);
        this.employee = Employee.builder()
                .personalInfo(personalInfo)
                .isActive(true)
                .build();
        return this.employee;
    }

    @Override
    public Employee updateObject(Employee entity) {
        PersonalInfo personalInfo = PersonalInfo.builder()
                .firstName("petya")
                .lastName("Popkin")
                .email("Mafgvil@mail.ru")
                .adress("Lenina DDDD Street")
                .description("BBBBBBBBBB")
                .phoneNumber("+88888888888")
                .skype("skype")
                .build();
        personalInfo = personalInfoRepository.saveAndFlush(personalInfo);
        entity = Employee.builder()
                .personalInfo(personalInfo)
                .isActive(true)
                .id(entity.getId())
                .build();
        return entity;
    }

}
