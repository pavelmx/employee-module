package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.PositionDTO.PositionCreationDTO;
import com.innowise.employeemodule.dto.PositionDTO.PositionUpdateDTO;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.service.EmployeeService;
import com.innowise.employeemodule.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("position")
public class PositionController {

    @Autowired
    private PositionService service;

    @GetMapping("/{id}")
    public ResponseEntity<Position> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Position>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/all-page")
    public ResponseEntity<Page<Position>> getAllPage(@RequestParam int size, @RequestParam int page,
                                                       @RequestParam String column, @RequestParam String order) {
        return new ResponseEntity<>(service.getAllPage(size, page, column, order), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Position> create(@RequestBody Position position) {
        return new ResponseEntity<>(service.add(position), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Position> update(@RequestBody Position position) {
        return new ResponseEntity<>(service.update(position), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("Position with id: '" + id + "' was deleted" ,HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>("All positions deleted", HttpStatus.OK);
    }

    @GetMapping("disable/{id}")
    public ResponseEntity<String> disablePositionById(@PathVariable("id") Long id) {
        service.disable(id);
        return new ResponseEntity<>("Position with id: '" + id + "' disabled", HttpStatus.OK);
    }

    @GetMapping("enable/{id}")
    public ResponseEntity<String> enablePositionById(@PathVariable("id") Long id) {
        service.enable(id);
        return new ResponseEntity<>("Position with id: '" + id + "' enabled", HttpStatus.OK);
    }

    @GetMapping("enable/all")
    public ResponseEntity<List<Position>> getEnableAll() {
        return new ResponseEntity<>(service.getEnableAll(), HttpStatus.OK);
    }

}