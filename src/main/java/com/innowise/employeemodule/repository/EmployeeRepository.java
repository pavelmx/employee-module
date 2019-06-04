package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
