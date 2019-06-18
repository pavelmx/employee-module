package com.innowise.employeemodule.db;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.DepartmentEmployeeRepository;
import com.innowise.employeemodule.repository.DepartmentRepository;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentEmployeeRepositoryTest extends AbstractRepositoryTest<DepartmentEmployee, DepartmentEmployeeRepository> {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    private DepartmentEmployee departmentEmployee;

    @Override
    public DepartmentEmployee createObject() {
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
    public DepartmentEmployee createObject(long id) {
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
        this.departmentEmployee = DepartmentEmployee.builder()
                .department(department)
                .description("Description 2")
                .employee(employee)
                .startDateInDepartment(LocalDateTime.now().minusDays(60))
                .endDateInDepartment(null)
                .isCurrentDepartment(true)
                .id(id)
                .build();
        return this.departmentEmployee;
    }

    @Override
    public Long getObjectId() {
        return this.departmentEmployee.getId();
    }

}
