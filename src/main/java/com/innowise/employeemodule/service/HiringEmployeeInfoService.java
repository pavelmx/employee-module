package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;

public interface HiringEmployeeInfoService extends AbstractService<HiringEmployeeInfo> {

    HiringEmployeeInfo setDismissEmployee(Employee employee);

}
