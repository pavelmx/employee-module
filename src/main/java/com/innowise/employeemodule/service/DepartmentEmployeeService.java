package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface DepartmentEmployeeService extends AbstractService<DepartmentEmployee> {

    void changeDepartment(Long newdepartment_id, Long employee_id, String description);

    DepartmentEmployee getCurrentByEmployeeIdAndIsCurrentDepartmentTrue(Long employee_id);

    List<DepartmentEmployee> getAllByEmployeeId(Long employee_id);

    List<DepartmentEmployee> getAllByEmployeeIdBetweenDate(Long employee_id, LocalDateTime start, LocalDateTime end);

    List<DepartmentEmployee> getAllByDepartmentId(Long department_id);

    void leaveDepartment(DepartmentEmployee departmentEmployee);

    Page<DepartmentEmployee> getAllPage(int size, int page, String column, String order);

    void deleteByEmployeeId(Long employee_id);
}
