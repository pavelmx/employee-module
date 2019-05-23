package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeService employeeService;

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
        if(repository.existsByName(department.getName())){
            throw new EntityExistsException("Department with name: '" + department.getName() + "' already exists");
        }
        return repository.save(department);
    }

    @Override
    public Department update(Department department) {
        if(!repository.existsById(department.getId())){
            throw new EntityNotFoundException("Department with id: '" + department.getId() + "' not found");
        }
        if(!repository.existsByName(department.getName())){
            throw new EntityNotFoundException("Department with name: '" + department.getName() + "' not found");
        }
        return repository.save(department);
    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Department with id: '" + id + "' not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Department create(Department department, Long manager_id) {
        department.setManager(employeeService.getById(manager_id));
        return add(department);
    }
}
