package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiringEmployeeInfoRepository extends JpaRepository<HiringEmployeeInfo, Long> {

}
