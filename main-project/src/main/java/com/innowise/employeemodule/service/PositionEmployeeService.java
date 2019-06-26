package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.PositionEmployee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PositionEmployeeService extends AbstractService<PositionEmployee>{

    void changePosition(Long employee_id, Long newposition_id, String description);

    PositionEmployee getCurrentByEmployeeIdAndEndDateForPositionIsNull(Long employee_id);

    List<PositionEmployee> getAllByEmployeeId(Long employee_id);

    List<PositionEmployee> getAllByPositionId(Long position_id);

    void leavePosition(PositionEmployee positionEmployee);

    Page<PositionEmployee> getAllPage(int size, int page, String column, String order);

    void deleteByEmployeeId(Long employee_id);
}
