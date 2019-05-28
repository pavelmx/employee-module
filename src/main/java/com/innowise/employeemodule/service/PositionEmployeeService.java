package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.PositionEmployee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PositionEmployeeService extends AbstractService<PositionEmployee>{

    void changePosition(Long employee_id, Long newposition_id);

    PositionEmployee getByEmployeeIdAndEndDateForPositionIsNull(Long employee_id);

    List<PositionEmployee> getAllByEmployeeId(Long employee_id);

    Page<PositionEmployee> getAllPage(int size, int page, String column, String order);
}