package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.service.DepartmentEmployeeService;
import com.innowise.employeemodule.service.DepartmentService;
import com.innowise.employeemodule.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department-employee")
public class DepartmentEmployeeController {

    @Autowired
    private DepartmentEmployeeService service;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEmployee> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentEmployee>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentEmployee> create(@RequestBody DepartmentEmployee departmentEmployee) {
        return new ResponseEntity<>(service.add(departmentEmployee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DepartmentEmployee> update(@RequestBody DepartmentEmployee departmentEmployee) {
        return new ResponseEntity<>(service.update(departmentEmployee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable  Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("DepartmentEmployee with id: '" + id + "' was deleted", HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>("All DepartmentEmployee's deleted", HttpStatus.OK);
    }

    @GetMapping("/change")
    public ResponseEntity<String> changeDepartment(@RequestParam Long newdepartment_id, @RequestParam Long employee_id){
        service.changeDepartment(newdepartment_id, employee_id);
        Employee employee = employeeService.getById(employee_id);
        Department department = departmentService.getById(newdepartment_id);
        return new ResponseEntity<>("Employee '" + employee.getPersonalInfo().getFirstName() + " "
                + employee.getPersonalInfo().getLastName() + "' moved to department '" + department.getName() + "'", HttpStatus.OK);
    }

}
