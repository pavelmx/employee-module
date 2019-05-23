package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;

public interface EmployeeService extends AbstractService<Employee> {

    void dismissEmployee(Long id);

    Employee create(Employee employee, Long position_id, Long department_id);

    void recoveryEmployee(Long id);
}
