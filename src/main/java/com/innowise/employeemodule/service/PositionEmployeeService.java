package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.PositionEmployee;

public interface PositionEmployeeService extends AbstractService<PositionEmployee>{

    void changePosition(Long employee_id, Long newposition_id);

    PositionEmployee getByEmployeeIdAndEndDateForPositionIsNull(Long employee_id);

}
