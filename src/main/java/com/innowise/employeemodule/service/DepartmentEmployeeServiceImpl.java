package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.repository.DepartmentEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class DepartmentEmployeeServiceImpl implements DepartmentEmployeeService {

    @Autowired
    private DepartmentEmployeeRepository repository;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public DepartmentEmployee getById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("DepartmentEmployee with id: '" + id + "' not found"));
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
        if(!repository.existsById(departmentEmployee.getId())){
            throw new EntityNotFoundException("DepartmentEmployee with id: '" + departmentEmployee.getId() + "' not found");
        }
        return repository.save(departmentEmployee);
    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("DepartmentEmployee with id: '" + id + "' not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public DepartmentEmployee getByEmployeeIdAndIsCurrentDepartmentTrue(Long employee_id){
        DepartmentEmployee departmentEmployee = repository.findByEmployee_IdAndIsCurrentDepartmentTrue(employee_id)
                .orElseThrow( () -> new EntityNotFoundException("DepartmentEmployee by employee's id: '" + employee_id + "' where IsCurrentDepartment = true not found"));
        return departmentEmployee;
    }

    @Override
    public void changeDepartment(Long newdepartment_id, Long employee_id){
        DepartmentEmployee oldDepartmentEmployee = getByEmployeeIdAndIsCurrentDepartmentTrue(employee_id);
        oldDepartmentEmployee.setCurrentDepartment(false);
        oldDepartmentEmployee.setEndDateInDepartment(LocalDate.now());
        update(oldDepartmentEmployee);
        DepartmentEmployee newDepartmentEmployee = new DepartmentEmployee();
        newDepartmentEmployee.setStartDateInDepartment(LocalDate.now());
        newDepartmentEmployee.setEmployee(oldDepartmentEmployee.getEmployee());
        newDepartmentEmployee.setCurrentDepartment(true);
        newDepartmentEmployee.setPositionEmployee(oldDepartmentEmployee.getPositionEmployee());
        newDepartmentEmployee.setDepartment(departmentService.getById(newdepartment_id));
        add(newDepartmentEmployee);
    }
}
