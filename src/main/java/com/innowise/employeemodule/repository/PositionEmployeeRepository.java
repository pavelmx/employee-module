package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.PositionEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionEmployeeRepository extends JpaRepository<PositionEmployee, Long> {

}
