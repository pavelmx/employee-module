package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.HiringEmployeeInfoRepository;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class HiringEmployeeInfoRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest<HiringEmployeeInfo, HiringEmployeeInfoRepository> {

    private HiringEmployeeInfo hiringEmployeeInfo;

    @Autowired
    private HiringEmployeeInfoRepository hiringEmployeeInfoRepository;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void findByEmployeeIdTest(){
        createObject();
        hiringEmployeeInfoRepository.saveAndFlush(hiringEmployeeInfo);
        List<HiringEmployeeInfo> hiringEmployeeInfoList = hiringEmployeeInfoRepository.findByEmployeeId(hiringEmployeeInfo.getEmployee().getId());
        Assert.assertEquals(Collections.singletonList(hiringEmployeeInfo), hiringEmployeeInfoList);
    }

    @Test
    public void findByEmployeeIdAndDateOfDismissalIsNullTest(){
        createObject();
        hiringEmployeeInfo.setDateOfDismissal(null);
        hiringEmployeeInfoRepository.saveAndFlush(hiringEmployeeInfo);
        boolean isPresent = hiringEmployeeInfoRepository.findByEmployeeIdAndDateOfDismissalIsNull(hiringEmployeeInfo.getEmployee().getId()).isPresent();
        Assert.assertTrue(isPresent);
    }

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
    public HiringEmployeeInfo updateObject(HiringEmployeeInfo entity) {
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
        entity = HiringEmployeeInfo.builder()
                .employee(employee)
                .dateOfDismissal(LocalDate.now().minusDays(15))
                .dateOfDismissal(LocalDate.now())
                .id(entity.getId())
                .build();
        return entity;
    }

}