package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.QEmployee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends AbstractRepository<Employee, Long, QEmployee> {

    List<Employee> findByIsActiveTrue();
}
