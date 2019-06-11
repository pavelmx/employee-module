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
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Find PositionEmployee by id")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    @ApiOperation(value = "Find all PositionEmployees")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @ApiOperation(value = "Create new PositionEmployee")
    public ResponseEntity<?> create(@RequestBody PositionEmployee positionEmployee) {
        try {
            return new ResponseEntity<>(service.add(positionEmployee), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @ApiOperation(value = "Update PositionEmployee")
    public ResponseEntity<?> update(@RequestBody PositionEmployee positionEmployee) {
        try {
            return new ResponseEntity<>(service.update(positionEmployee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete PositionEmployee by id")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(new RestResponse("PositionEmployee with id: '" + id + "' was deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/all")
    @ApiOperation(value = "Delete all PositionEmployees")
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
    @ApiOperation(value = "Get all pages of PositionEmployees")
    public ResponseEntity<?> getAllPage(@RequestParam int size, @RequestParam int page,
                                        @RequestParam String column, @RequestParam String order) {
        try {
            return new ResponseEntity<>(service.getAllPage(size, page, column, order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all/{employee_id}")
    @ApiOperation(value = "Get all PositionEmployees by employee's id")
    public ResponseEntity<?> getAllByEmployeeId(@PathVariable Long employee_id) {
        try {
            return new ResponseEntity<>(service.getAllByEmployeeId(employee_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("current/{employee_id}")
    @DTO(PositionEmployeeGetCurrentDTO.class)
    @ApiOperation(value = "Get PositionEmployee by employee's id where his position is current")
    public ResponseEntity<?> getByEmployeeIdAndEndDateForPositionIsNull(@PathVariable Long employee_id) {
        try {
            return new ResponseEntity<>(service.getCurrentByEmployeeIdAndEndDateForPositionIsNull(employee_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("change")
    @ApiOperation(value = "Change position at employee with id: 'employee_id' to new position with id: 'newposition_id'")
    public ResponseEntity<?> changePosition(@RequestParam Long employee_id, @RequestParam Long newposition_id) {
        try {
            service.changePosition(employee_id, newposition_id);
            Position position = positionService.getById(newposition_id);
            Employee employee = employeeService.getById(employee_id);
            return new ResponseEntity<>(new RestResponse("Employee '" + employee.getPersonalInfo().getFirstName() + " "
                    + employee.getPersonalInfo().getLastName() + "' promoted to a position '" + position.getName() + "'"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
