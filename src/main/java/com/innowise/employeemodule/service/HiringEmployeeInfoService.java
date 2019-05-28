package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import org.springframework.data.domain.Page;

public interface HiringEmployeeInfoService extends AbstractService<HiringEmployeeInfo> {

    HiringEmployeeInfo setDismissEmployee(Employee employee);

    Page<HiringEmployeeInfo> getAllPage(int size, int page, String column, String order);
}
