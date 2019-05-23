package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.service.DepartmentService;
import com.innowise.employeemodule.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department department, @RequestParam Long manager_id) {
        return new ResponseEntity<>(service.create(department, manager_id), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Department> update(@RequestBody Department department) {
        return new ResponseEntity<>(service.update(department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("Department with id: '" + id + "' was deleted", HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>("All departments deleted", HttpStatus.OK);
    }

}
