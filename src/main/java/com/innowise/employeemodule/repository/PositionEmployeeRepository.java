package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.PositionEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionEmployeeRepository extends JpaRepository<PositionEmployee, Long> {

    Optional<PositionEmployee> findByEmployee_IdAndEndDateForPositionIsNull(Long id);

}
