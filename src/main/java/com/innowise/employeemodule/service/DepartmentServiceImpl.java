package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                .orElseThrow(() -> new EntityNotFoundException("Department with id: '" + id + "' not found"));
    }

    @Override
    public List<Department> getAll() {
        return repository.findAll();
    }

    @Override
    public Department add(Department department) {
        if (repository.existsByName(department.getName())) {
            throw new EntityExistsException("Department with name: '" + department.getName() + "' already exists");
        }
        return repository.save(department);
    }

    @Override
    public Department update(Department department) {
        if (!repository.existsById(department.getId())) {
            throw new EntityNotFoundException("Department with id: '" + department.getId() + "' not found");
        }
        Department oldDepartment = getById(department.getId());
        if (!oldDepartment.getName().equals(department.getName())) {
            if (repository.existsByName(department.getName())) {
                throw new EntityExistsException("Department with name: '" + department.getName() + "' already exists");
            }
        }
        return repository.save(department);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
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
        if (manager_id == null) {
            department.setManager(null);
        } else {
            department.setManager(employeeService.getById(manager_id));
        }
        return add(department);
    }

    @Override
    public Department edit(Department department, Long manager_id) {
        System.out.println(manager_id);
        if (manager_id == null) {
            department.setManager(null);
        } else {
            department.setManager(employeeService.getById(manager_id));
        }
        return update(department);
    }

    @Override
    public Page<Department> getAllPage(int size, int page, String column, String order) {
        Pageable pageable;
        if (order.equals("")) {
            pageable = PageRequest.of(page, size);
        } else {
            pageable = PageRequest.of(page, size, new Sort(Sort.Direction.fromString(order), column));
        }
        return repository.findAll( pageable);
    }
}
