package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.DepartmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, Long> {

    Optional<DepartmentEmployee> findByEmployee_IdAndIsCurrentDepartmentTrue(Long id);

    List<DepartmentEmployee> findByEmployee_Id(Long id);

    List<DepartmentEmployee> findByDepartment_IdAndIsCurrentDepartmentTrue(Long id);

}
