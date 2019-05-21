package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Employee with id: '" + id + "' not found"));
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee add(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return repository.save(employee);
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
