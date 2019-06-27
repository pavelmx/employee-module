package com.innowise.employeemodule.service;

import com.innowise.employeemodule.dto.DepartmentPositionDTO.DepartmentPositionDTO;
import com.innowise.employeemodule.entity.DepartmentPosition;

import java.util.List;

public interface DepartmentPositionService {
    List<DepartmentPosition> getByEmployeeId(Long id);
}
