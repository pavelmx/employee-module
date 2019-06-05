package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeCreationDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeFullNameDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeGetDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeUpdateDTO;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.filter.EmployeeFilter;
import com.innowise.employeemodule.entity.RestResponse;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeRepository repository;


    @GetMapping("/{id}")
    @DTO(EmployeeGetDTO.class)
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    @DTO(EmployeeFullNameDTO.class)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@DTO(EmployeeCreationDTO.class) Employee employee,
                                    @RequestParam Long position_id, @RequestParam Long department_id) {
        try {
            return new ResponseEntity<>(service.create(employee, position_id, department_id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@DTO(EmployeeUpdateDTO.class) Employee employee) {
        try {
            return new ResponseEntity<>(service.update(employee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(new RestResponse("Employee with id: '" + id + "' was deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        try {
            service.deleteAll();
            return new ResponseEntity<>(new RestResponse("All employees were deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/all-page")
    @DTO(EmployeeGetDTO.class)
    public ResponseEntity<?> getAllPage(@RequestParam int size, @RequestParam int page,
                                        @RequestParam String column, @RequestParam String order,
                                        @RequestBody EmployeeFilter employeeFilter) {
        try {
            return new ResponseEntity<>(service.getAllPage(size, page, column, order, employeeFilter), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("dismissal/{id}")
    public ResponseEntity<?> dismissEmployeeById(@PathVariable Long id) {
        try {
            service.dismissEmployee(id);
            return new ResponseEntity<>(new RestResponse("Employee with id: '" + id + "' was dismissed"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("recovery/{id}")
    public ResponseEntity<?> recoveryEmployeeById(@PathVariable Long id, @RequestParam Long position_id,
                                                  @RequestParam Long department_id) {
        try {
            service.recoveryEmployee(id, position_id, department_id);
            return new ResponseEntity<>(new RestResponse("Employee with id: '" + id + "' was reinstated"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
