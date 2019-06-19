package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PersonalInfoRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest<PersonalInfo, PersonalInfoRepository> {

    private PersonalInfo personalInfo;

    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    @Test
    public void existsByEmailTest(){
        createObject();
        personalInfoRepository.saveAndFlush(personalInfo);
        boolean exists = personalInfoRepository.existsByEmail(personalInfo.getEmail());
        Assert.assertTrue(exists);
    }

    @Test
    public void existsByPhoneNumberTest(){
        createObject();
        personalInfoRepository.saveAndFlush(personalInfo);
        boolean exists = personalInfoRepository.existsByPhoneNumber(personalInfo.getPhoneNumber());
        Assert.assertTrue(exists);
    }

    @Test
    public void existsBySkypeTest(){
        createObject();
        personalInfoRepository.saveAndFlush(personalInfo);
        boolean exists = personalInfoRepository.existsBySkype(personalInfo.getSkype());
        Assert.assertTrue(exists);
    }

    @Override
    public PersonalInfo createObject() {
        this.personalInfo = PersonalInfo.builder()
                .firstName("Vasya")
                .lastName("Pupkin")
                .email("Mail@mail.ru")
                .adress("Lenina Street")
                .description("AAAAAAAA")
                .phoneNumber("+375298785653")
                .skype("skype")
                .build();
        return this.personalInfo;
    }

    @Override
    public PersonalInfo updateObject(PersonalInfo entity) {
        entity = PersonalInfo.builder()
                .firstName("Vasya")
                .lastName("Pupkin")
                .email("Mail@mail.ru")
                .adress("Lenina Street")
                .description("AAAAAAAA")
                .phoneNumber("+375298785653")
                .skype("skype")
                .id(entity.getId())
                .build();
        return entity;
    }

}