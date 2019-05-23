package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeUpdateDTO;
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
    @DTO(EmployeeUpdateDTO.class)
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee,
                                           @RequestParam Long position_id, @RequestParam Long department_id) {
        return new ResponseEntity<>(service.create(employee, position_id, department_id), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> update(@RequestBody  Employee employee) {
        return new ResponseEntity<>(service.update(employee), HttpStatus.OK);
    }
////////////////////
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("Employee with id: '" + id + "' was deleted", HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>("All employees deleted", HttpStatus.OK);
    }
////////////////////
    @GetMapping("dismissal/{id}")
    public ResponseEntity<String> dismissEmployeeById(@PathVariable Long id) {
        service.dismissEmployee(id);
        return new ResponseEntity<>("Employee with id: '" + id + "' was dismissed", HttpStatus.OK);
    }

    @GetMapping("recovery/{id}")
    public ResponseEntity<String> recoveryEmployee(@PathVariable Long id) {
        service.recoveryEmployee(id);
        return new ResponseEntity<>("Employee with id: '" + id + "' was reinstated", HttpStatus.OK);
    }
}
