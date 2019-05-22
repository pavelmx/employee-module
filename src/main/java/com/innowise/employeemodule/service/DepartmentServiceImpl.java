package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department getById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Department with id: '" + id + "' not found"));
    }

    @Override
    public List<Department> getAll() {
        return repository.findAll();
    }

    @Override
    public Department add(Department department) {
        return repository.save(department);
    }

    @Override
    public Department update(Department department) {
        return repository.save(department);
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
