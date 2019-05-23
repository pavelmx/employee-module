package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.entity.PositionEmployee;
import com.innowise.employeemodule.repository.PositionEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PositionEmployeeServiceImpl implements  PositionEmployeeService{

    @Autowired
    private PositionEmployeeRepository repository;

    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    @Autowired
    private PositionService positionService;

    @Override
    public PositionEmployee getById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("PositionEmployee with id: '" + id + "' not found"));
    }

    @Override
    public List<PositionEmployee> getAll() {
        return repository.findAll();
    }

    @Override
    public PositionEmployee add(PositionEmployee positionEmployee) {
        return repository.save(positionEmployee);
    }

    @Override
    public PositionEmployee update(PositionEmployee positionEmployee) {
        if(!repository.existsById(positionEmployee.getId())){
            throw new EntityNotFoundException("PositionEmployee with id: '" + positionEmployee.getId() + "' not found");
        }
        return repository.save(positionEmployee);
    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("PositionEmployee with id: '" + id + "' not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public PositionEmployee getByEmployeeIdAndEndDateForPositionIsNull(Long employee_id){
        PositionEmployee positionEmployee = repository.findByEmployee_IdAndEndDateForPositionIsNull(employee_id)
                .orElseThrow( () -> new EntityNotFoundException("PositionEmployee by employee's id: '" + employee_id + "' where EndDateForPosition = null not found"));
        return positionEmployee;
    }

    @Override
    public void changePosition(Long employee_id, Long newposition_id) {
        PositionEmployee oldPositionEmployee = getByEmployeeIdAndEndDateForPositionIsNull(employee_id);
        oldPositionEmployee.setEndDateForPosition(LocalDate.now());
        update(oldPositionEmployee);
        PositionEmployee newPositionEmployee = new PositionEmployee();
        newPositionEmployee.setStartDateForPosition(LocalDate.now());
        newPositionEmployee.setPosition(positionService.getById(newposition_id));
        newPositionEmployee.setEmployee(oldPositionEmployee.getEmployee());
        add(newPositionEmployee);
        DepartmentEmployee departmentEmployee = departmentEmployeeService.getByEmployeeIdAndIsCurrentDepartmentTrue(employee_id);
        departmentEmployee.setPositionEmployee(newPositionEmployee);
        departmentEmployeeService.update(departmentEmployee);
    }
}
