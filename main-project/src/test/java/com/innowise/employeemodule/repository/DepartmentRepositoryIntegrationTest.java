package com.innowise.employeemodule.repository;


import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.DepartmentRepository;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest<Department, DepartmentRepository> {

    private Department department;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Test
    public void existsByNameTest(){
        createObject();
        departmentRepository.saveAndFlush(department);
        boolean exists = departmentRepository.existsByName(department.getName());
        Assert.assertTrue(exists);
    }

    @Override
    public Department createObject() {
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
        Employee employee = Employee.builder()
                .personalInfo(personalInfo)
                .isActive(true)
                .build();
        employee = employeeRepository.saveAndFlush(employee);
        this.department = Department.builder()
                .name("JS")
                .manager(employee)
                .build();
        return this.department;
    }

    @Override
    public Department updateObject(Department entity) {
        PersonalInfo personalInfo = PersonalInfo.builder()
                .firstName("Petya")
                .lastName("Kinpuk")
                .email("Maggdfil@mail.ru")
                .adress("Stalina Street")
                .description("CCCCC")
                .phoneNumber("++999959")
                .skype("skype")
                .build();
        personalInfo = personalInfoRepository.saveAndFlush(personalInfo);
        Employee employee = Employee.builder()
                .personalInfo(personalInfo)
                .isActive(true)
                .build();
        employee = employeeRepository.saveAndFlush(employee);
        entity = Department.builder()
                .name("Java")
                .manager(employee)
                .id(entity.getId())
                .build();
        return entity;
    }

}
