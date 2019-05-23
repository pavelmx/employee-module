package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.DepartmentEmployee;

public interface DepartmentEmployeeService extends AbstractService<DepartmentEmployee> {

    void changeDepartment(Long newdepartment_id, Long employee_id);

    DepartmentEmployee getByEmployeeIdAndIsCurrentDepartmentTrue(Long employee_id);

}
