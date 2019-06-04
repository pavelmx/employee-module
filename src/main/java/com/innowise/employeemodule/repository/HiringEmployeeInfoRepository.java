package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HiringEmployeeInfoRepository extends JpaRepository<HiringEmployeeInfo, Long> {

    Optional<HiringEmployeeInfo> findByEmployee_IdAndDateOfDismissalIsNull(Long id);

    List<HiringEmployeeInfo> findByEmployee_Id(Long employee_id);
}
