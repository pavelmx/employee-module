package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.PositionEmployee;
import com.innowise.employeemodule.repository.PositionEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        positionEmployee.setStartDateForPosition(LocalDateTime.now());
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
    public List<PositionEmployee> getAllByEmployeeId(Long employee_id){
        List<PositionEmployee> positionEmployeeList = repository.findByEmployeeId(employee_id);
        return positionEmployeeList;
    }

    @Override
    public List<PositionEmployee> getAllByPositionId(Long position_id){
        List<PositionEmployee> positionEmployeeList = repository.findByPositionIdAndEndDateForPositionIsNull(position_id);
        return positionEmployeeList;
    }

    @Override
    public PositionEmployee getCurrentByEmployeeIdAndEndDateForPositionIsNull(Long employee_id){
        PositionEmployee positionEmployee = repository.findByEmployeeIdAndEndDateForPositionIsNull(employee_id)
                .orElseThrow( () -> new EntityNotFoundException("PositionEmployee by employee's id: '" + employee_id + "' where EndDateForPosition = null not found"));
        return positionEmployee;
    }

    @Override
    public void leavePosition(PositionEmployee positionEmployee){
        positionEmployee.setEndDateForPosition(LocalDateTime.now());
        update(positionEmployee);
    }

    @Override
    public void changePosition(Long employee_id, Long newposition_id, String description) {
        PositionEmployee oldPositionEmployee = getCurrentByEmployeeIdAndEndDateForPositionIsNull(employee_id);
        leavePosition(oldPositionEmployee);
        PositionEmployee newPositionEmployee = new PositionEmployee();
        newPositionEmployee.setStartDateForPosition(LocalDateTime.now());
        newPositionEmployee.setPosition(positionService.getById(newposition_id));
        newPositionEmployee.setEmployee(oldPositionEmployee.getEmployee());
        newPositionEmployee.setDescription(description);
        add(newPositionEmployee);
    }

    @Override
    public Page<PositionEmployee> getAllPage(int size, int page, String column, String order) {
        Pageable pageable;
        if(order.equals("")){
            pageable = PageRequest.of(page, size);
        }else{
            pageable = PageRequest.of(page, size, new Sort(Sort.Direction.fromString(order), column));
        }
        return repository.findAll(pageable);
    }

    @Override
    public void deleteByEmployeeId(Long employee_id) {
        List<PositionEmployee> positionEmployees = new ArrayList<>();
        positionEmployees = getAllByEmployeeId(employee_id);
        for (PositionEmployee positionEmployee : positionEmployees) {
            deleteById(positionEmployee.getId());
        }
    }
}
