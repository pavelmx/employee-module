package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.Position;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PositionService extends AbstractService<Position> {

    void disable(Long id);

    void enable(Long id);

    List<Position> getEnableAll();

    Page<Position> getAllPage(int size, int page, String column, String order);
}
