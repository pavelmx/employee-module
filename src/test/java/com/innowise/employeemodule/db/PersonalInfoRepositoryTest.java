package com.innowise.employeemodule.db;

import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import com.innowise.employeemodule.repository.PositionRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PersonalInfoRepositoryTest extends AbstractRepositoryTest<PersonalInfo, PersonalInfoRepository> {

    private PersonalInfo personalInfo;

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
    public PersonalInfo createObject(long id) {
        this.personalInfo = PersonalInfo.builder()
                .firstName("Vasya")
                .lastName("Pupkin")
                .email("Mail@mail.ru")
                .adress("Lenina Street")
                .description("AAAAAAAA")
                .phoneNumber("+375298785653")
                .skype("skype")
                .id(id)
                .build();
        return this.personalInfo;
    }

    @Override
    public Long getObjectId() {
        return this.personalInfo.getId();
    }

}