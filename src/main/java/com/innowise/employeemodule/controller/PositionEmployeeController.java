package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.entity.PositionEmployee;
import com.innowise.employeemodule.service.EmployeeService;
import com.innowise.employeemodule.service.PositionEmployeeService;
import com.innowise.employeemodule.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("position-employee")
public class PositionEmployeeController {

    @Autowired
    private PositionEmployeeService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @GetMapping("/{id}")
    public ResponseEntity<PositionEmployee> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PositionEmployee>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PositionEmployee> create(@RequestBody PositionEmployee positionEmployee) {
        return new ResponseEntity<>(service.add(positionEmployee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PositionEmployee> update(@RequestBody PositionEmployee positionEmployee) {
        return new ResponseEntity<>(service.update(positionEmployee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("PositionEmployee with id: '" + id + "' was deleted" ,HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>("All PositionEmployee's deleted", HttpStatus.OK);
    }

    @GetMapping("change")
    public ResponseEntity<String> changePosition(@RequestParam Long employee_id, @RequestParam Long newposition_id) {
        service.changePosition(employee_id, newposition_id);
        Position position = positionService.getById(newposition_id);
        Employee employee = employeeService.getById(employee_id);
        return new ResponseEntity<>("Employee '" + employee.getPersonalInfo().getFirstName() + " "
                + employee.getPersonalInfo().getLastName() + "' promoted to a position '" + position.getName() + "'", HttpStatus.OK);
    }

}
