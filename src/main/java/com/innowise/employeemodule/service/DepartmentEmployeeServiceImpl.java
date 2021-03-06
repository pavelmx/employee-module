package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.repository.DepartmentEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        departmentEmployee.setStartDateInDepartment(LocalDate.now());
        departmentEmployee.setCurrentDepartment(true);
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
    public List<DepartmentEmployee> getAllByEmployeeId(Long employee_id){
        List<DepartmentEmployee> departmentEmployeeList = repository.findByEmployee_Id(employee_id);
        return departmentEmployeeList;
    }

    @Override
    public List<DepartmentEmployee> getAllByDepartmentId(Long department_id){
        List<DepartmentEmployee> departmentEmployeeList = repository.findByDepartment_IdAndIsCurrentDepartmentTrue(department_id);
        return departmentEmployeeList;
    }

    @Override
    public DepartmentEmployee getCurrentByEmployeeIdAndIsCurrentDepartmentTrue(Long employee_id){
        DepartmentEmployee departmentEmployee = repository.findByEmployee_IdAndIsCurrentDepartmentTrue(employee_id)
                .orElseThrow( () -> new EntityNotFoundException("DepartmentEmployee by employee's id: '" + employee_id + "' where IsCurrentDepartment = true not found"));
        return departmentEmployee;
    }

    @Override
    public void leaveDepartment(DepartmentEmployee departmentEmployee){
        departmentEmployee.setCurrentDepartment(false);
        departmentEmployee.setEndDateInDepartment(LocalDate.now());
        update(departmentEmployee);
    }

    @Override
    public void changeDepartment(Long newdepartment_id, Long employee_id){
        DepartmentEmployee oldDepartmentEmployee = getCurrentByEmployeeIdAndIsCurrentDepartmentTrue(employee_id);
        leaveDepartment(oldDepartmentEmployee);
        DepartmentEmployee newDepartmentEmployee = new DepartmentEmployee();
        newDepartmentEmployee.setStartDateInDepartment(LocalDate.now());
        newDepartmentEmployee.setEmployee(oldDepartmentEmployee.getEmployee());
        newDepartmentEmployee.setDepartment(departmentService.getById(newdepartment_id));
        add(newDepartmentEmployee);
    }

    @Override
    public Page<DepartmentEmployee> getAllPage(int size, int page, String column, String order) {
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
        List<DepartmentEmployee> departmentEmployees = new ArrayList<>();
        departmentEmployees = getAllByEmployeeId(employee_id);
        for (DepartmentEmployee departmentEmployee : departmentEmployees) {
            deleteById(departmentEmployee.getId());
        }
    }


}
