package com.innowise.employeemodule.repository;

import com.innowise.employeemodule.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>
{

    boolean existsByName(String name);

    /*@Override
    default public void customize(QuerydslBindings bindings, QUser root) {
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.excluding(root.email);
    }*/
}
