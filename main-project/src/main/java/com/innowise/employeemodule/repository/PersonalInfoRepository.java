package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Long> {

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsBySkype(String skype);
}
