package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    boolean existsByName(String name);

    List<Position> findByIsActiveTrue();
}
