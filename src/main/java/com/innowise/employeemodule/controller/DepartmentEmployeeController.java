package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.service.DepartmentEmployeeService;
import com.innowise.employeemodule.service.DepartmentService;
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

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEmployee> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentEmployee>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentEmployee> create(DepartmentEmployee departmentEmployee) {
        return new ResponseEntity<>(service.add(departmentEmployee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DepartmentEmployee> update(DepartmentEmployee departmentEmployee) {
        return new ResponseEntity<>(service.update(departmentEmployee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(Long id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
