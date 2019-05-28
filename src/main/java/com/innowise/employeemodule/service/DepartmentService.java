package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Department;
import org.springframework.data.domain.Page;

public interface DepartmentService extends AbstractService<Department> {

    Department create(Department department, Long manager_id);

    Page<Department> getAllPage(int size, int page, String column, String order);
}
