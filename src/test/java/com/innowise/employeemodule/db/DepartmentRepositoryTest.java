package com.innowise.employeemodule.db;


import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.DepartmentRepository;
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
public class DepartmentRepositoryTest extends AbstractRepositoryTest<Department, DepartmentRepository> {

    private Department department;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

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
    public Department createObject(long id) {
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
        this.department = Department.builder()
                .name("Java")
                .manager(employee)
                .id(id)
                .build();
        return this.department;
    }

    @Override
    public Long getObjectId() {
        return this.department.getId();
    }

}
