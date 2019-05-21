package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.repository.DepartmentEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService {

    @Autowired
    private DepartmentEmployeeRepository repository;

    @Override
    public DepartmentEmployee getById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("DepartmentEmployee with id: '" + id + "' not found"));
    }

    @Override
    public List<DepartmentEmployee> getAll() {
        return repository.findAll();
    }

    @Override
    public DepartmentEmployee add(DepartmentEmployee departmentEmployee) {
        return repository.save(departmentEmployee);
    }

    @Override
    public DepartmentEmployee update(DepartmentEmployee departmentEmployee) {
        return repository.save(departmentEmployee);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
