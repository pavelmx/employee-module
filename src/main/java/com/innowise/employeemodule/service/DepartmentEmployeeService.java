package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentEmployeeService extends AbstractService<DepartmentEmployee> {

    void changeDepartment(Long newdepartment_id, Long employee_id);

    DepartmentEmployee getCurrentByEmployeeIdAndIsCurrentDepartmentTrue(Long employee_id);

    List<DepartmentEmployee> getAllByEmployeeId(Long employee_id);

    void leaveDepartment(DepartmentEmployee departmentEmployee);

    Page<DepartmentEmployee> getAllPage(int size, int page, String column, String order);
}
