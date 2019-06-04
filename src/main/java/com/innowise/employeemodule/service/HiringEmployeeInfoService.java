package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HiringEmployeeInfoService extends AbstractService<HiringEmployeeInfo> {

    HiringEmployeeInfo setDismissEmployee(Employee employee);

    Page<HiringEmployeeInfo> getAllPage(int size, int page, String column, String order);

    List<HiringEmployeeInfo> getByEmployeeId(Long employee_id);

    void deleteByEmployeeId(Long employee_id);
}
