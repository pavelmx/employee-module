package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.DepartmentEmployeeRepository;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentEmployeeRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest<DepartmentEmployee, DepartmentEmployeeRepository> {

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    private DepartmentEmployee departmentEmployee;

    @Test
    public void findByEmployeeIdAndIsCurrentDepartmentTrueTest(){
        createObject();
        departmentEmployee.setCurrentDepartment(true);
        departmentEmployee = departmentEmployeeRepository.saveAndFlush(departmentEmployee);
        boolean isPresent = departmentEmployeeRepository.findByEmployeeIdAndIsCurrentDepartmentTrue(departmentEmployee.getEmployee().getId()).isPresent();
        Assert.assertTrue(isPresent);
    }

    @Test
    public void findByEmployeeIdTest(){
        createObject();
        departmentEmployeeRepository.saveAndFlush(departmentEmployee);
        List<DepartmentEmployee> departmentEmployeeList = departmentEmployeeRepository.findByEmployeeId(departmentEmployee.getEmployee().getId());
        Assert.assertEquals(Collections.singletonList(departmentEmployee), departmentEmployeeList);
    }

    @Test
    public void findByDepartmentIdAndIsCurrentDepartmentTrueTest(){
        createObject();
        departmentEmployee.setCurrentDepartment(true);
        departmentEmployee = departmentEmployeeRepository.saveAndFlush(departmentEmployee);
        List<DepartmentEmployee> departmentEmployeeList = departmentEmployeeRepository.findByDepartmentIdAndIsCurrentDepartmentTrue(departmentEmployee.getDepartment().getId());
        Assert.assertEquals(Collections.singletonList(departmentEmployee), departmentEmployeeList);
    }

    @Override
    protected DepartmentEmployee createObject() {
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
        Department department = Department.builder()
                .name("JS")
                .manager(employee)
                .build();
        department = departmentRepository.saveAndFlush(department);
        this.departmentEmployee = DepartmentEmployee.builder()
                .department(department)
                .description("Description 1")
                .employee(employee)
                .startDateInDepartment(LocalDateTime.now().minusDays(60))
                .endDateInDepartment(null)
                .isCurrentDepartment(true)
                .build();
        return this.departmentEmployee;
    }

    @Override
    protected DepartmentEmployee updateObject(DepartmentEmployee entity) {
        PersonalInfo personalInfo = PersonalInfo.builder()
                .firstName("Petya")
                .lastName("Kukpin")
                .email("Magfril@mail.ru")
                .adress("Lenina AVenu")
                .description("BBBBB")
                .phoneNumber("+66666666")
                .skype("skype")
                .build();
        personalInfo = personalInfoRepository.saveAndFlush(personalInfo);
        Employee employee = Employee.builder()
                .personalInfo(personalInfo)
                .isActive(true)
                .build();
        employee = employeeRepository.saveAndFlush(employee);
        Department department = Department.builder()
                .name("Java")
                .manager(employee)
                .build();
        department = departmentRepository.saveAndFlush(department);
        entity = DepartmentEmployee.builder()
                .department(department)
                .description("Description 2")
                .employee(employee)
                .startDateInDepartment(LocalDateTime.now().minusDays(60))
                .endDateInDepartment(null)
                .isCurrentDepartment(true)
                .id(entity.getId())
                .build();
        return entity;
    }

}
