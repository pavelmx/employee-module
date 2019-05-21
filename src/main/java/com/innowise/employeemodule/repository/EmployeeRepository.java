package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
