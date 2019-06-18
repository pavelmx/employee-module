package com.innowise.employeemodule.db;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.HiringEmployeeInfoRepository;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class HiringEmployeeInfoRepositoryTest extends AbstractRepositoryTest<HiringEmployeeInfo, HiringEmployeeInfoRepository> {

    private HiringEmployeeInfo hiringEmployeeInfo;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public HiringEmployeeInfo createObject() {
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
        this.hiringEmployeeInfo = HiringEmployeeInfo.builder()
                .employee(employee)
                .dateOfDismissal(LocalDate.now().minusDays(10))
                .dateOfDismissal(LocalDate.now())
                .build();
        return this.hiringEmployeeInfo;
    }

    @Override
    public HiringEmployeeInfo createObject(long id) {
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
        this.hiringEmployeeInfo = HiringEmployeeInfo.builder()
                .employee(employee)
                .dateOfDismissal(LocalDate.now().minusDays(15))
                .dateOfDismissal(LocalDate.now())
                .id(id)
                .build();
        return this.hiringEmployeeInfo;
    }

    @Override
    public Long getObjectId() {
        return this.hiringEmployeeInfo.getId();
    }

}