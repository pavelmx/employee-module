package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Position;

import java.util.List;

public interface PositionService extends AbstractService<Position> {

    void disable(Long id);

    void enable(Long id);

    List<Position> getEnableAll();
}
