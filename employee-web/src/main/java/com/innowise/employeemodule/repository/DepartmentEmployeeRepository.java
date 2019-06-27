package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.DepartmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, Long> {

    Optional<DepartmentEmployee> findByEmployeeIdAndIsCurrentDepartmentTrue(Long id);

    List<DepartmentEmployee> findByEmployeeId(Long id);

    List<DepartmentEmployee> findByDepartmentIdAndIsCurrentDepartmentTrue(Long id);

}
