package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> create(Employee employee) {
        return new ResponseEntity<>(service.add(employee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> update(Employee employee) {
        return new ResponseEntity<>(service.update(employee), HttpStatus.OK);
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
