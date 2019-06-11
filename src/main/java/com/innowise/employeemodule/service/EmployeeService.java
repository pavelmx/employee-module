package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.candidate.EmployeeCandidate;
import com.innowise.employeemodule.entity.filter.EmployeeFilter;
import org.springframework.data.domain.Page;

import java.util.List;


public interface EmployeeService extends AbstractService<Employee> {

    List<Employee> getAllActive();

    List<EmployeeCandidate> getAllActiveEmployeeCandidates();

    void dismissEmployee(Long id);

    Employee create(Employee employee, Long position_id, Long department_id);

    void recoveryEmployee(Long id, Long position_id, Long department_id);

    Page<Employee> getAllPage(int size, int page, String column, String order, EmployeeFilter employeeFilter);
}
