package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Department;

public interface DepartmentService extends AbstractService<Department> {

    Department create(Department department, Long manager_id);
}
