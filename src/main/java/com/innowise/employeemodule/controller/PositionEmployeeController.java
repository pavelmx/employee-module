package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.PositionEmployeeDTO.PositionEmployeeGetCurrentDTO;
import com.innowise.employeemodule.dto.PositionEmployeeDTO.PositionEmployeeGetDTO;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.entity.PositionEmployee;
import com.innowise.employeemodule.entity.RestResponse;
import com.innowise.employeemodule.service.EmployeeService;
import com.innowise.employeemodule.service.PositionEmployeeService;
import com.innowise.employeemodule.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PositionEmployee positionEmployee) {
        try {
            return new ResponseEntity<>(service.add(positionEmployee), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PositionEmployee positionEmployee) {
        try {
            return new ResponseEntity<>(service.update(positionEmployee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(new RestResponse("PositionEmployee with id: '" + id + "' was deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        try {
            service.deleteAll();
            return new ResponseEntity<>(new RestResponse("All PositionEmployee's were deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-page")
    @DTO(PositionEmployeeGetDTO.class)
    public ResponseEntity<?> getAllPage(@RequestParam int size, @RequestParam int page,
                                        @RequestParam String column, @RequestParam String order) {
        try {
            return new ResponseEntity<>(service.getAllPage(size, page, column, order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all/{employee_id}")
    public ResponseEntity<?> getAllByEmployeeId(@PathVariable Long employee_id) {
        try {
            return new ResponseEntity<>(service.getAllByEmployeeId(employee_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("current/{employee_id}")
    @DTO(PositionEmployeeGetCurrentDTO.class)
    public ResponseEntity<?> getByEmployeeIdAndEndDateForPositionIsNull(@PathVariable Long employee_id) {
        try {
            return new ResponseEntity<>(service.getCurrentByEmployeeIdAndEndDateForPositionIsNull(employee_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("change")
    public ResponseEntity<?> changePosition(@RequestParam Long employee_id, @RequestParam Long newposition_id, @RequestParam String description) {
        try {
            service.changePosition(employee_id, newposition_id, description);
            Position position = positionService.getById(newposition_id);
            Employee employee = employeeService.getById(employee_id);
            return new ResponseEntity<>(new RestResponse("Employee '" + employee.getPersonalInfo().getFirstName() + " "
                    + employee.getPersonalInfo().getLastName() + "' promoted to a position '" + position.getName() + "'"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
