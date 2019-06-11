package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.DepartmentEmployeeDTO.DepartmentEmployeeGetCurrentDTO;
import com.innowise.employeemodule.dto.DepartmentEmployeeDTO.DepartmentEmployeeGetDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeGetDTO;
import com.innowise.employeemodule.entity.*;
import com.innowise.employeemodule.service.DepartmentEmployeeService;
import com.innowise.employeemodule.service.DepartmentService;
import com.innowise.employeemodule.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @ApiOperation(value = "Find DepartmentEmployee by id")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    @ApiOperation(value = "Find all DepartmentEmployees")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @ApiOperation(value = "Create new DepartmentEmployee")
    public ResponseEntity<?> create(@RequestBody DepartmentEmployee departmentEmployee) {
        try {
            return new ResponseEntity<>(service.add(departmentEmployee), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @ApiOperation(value = "Update DepartmentEmployee")
    public ResponseEntity<?> update(@RequestBody DepartmentEmployee departmentEmployee) {
        try {
            return new ResponseEntity<>(service.update(departmentEmployee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete DepartmentEmployee by id")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(new RestResponse("DepartmentEmployee with id: '" + id + "' was deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/all")
    @ApiOperation(value = "Delete all DepartmentEmployees")
    public ResponseEntity<?> deleteAll() {
        try {
            service.deleteAll();
            return new ResponseEntity<>(new RestResponse("All DepartmentEmployee's were deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-page")
    @DTO(DepartmentEmployeeGetDTO.class)
    @ApiOperation(value = "Get all pages of DepartmentEmployees")
    public ResponseEntity<?> getAllPage(@RequestParam int size, @RequestParam int page,
                                        @RequestParam String column, @RequestParam String order) {
        try {
            return new ResponseEntity<>(service.getAllPage(size, page, column, order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("all/{employee_id}")
    @ApiOperation(value = "Get all DepartmentEmployees by employee's id")
    public ResponseEntity<?> getAllByEmployeeId(@PathVariable Long employee_id) {
        try {
            return new ResponseEntity<>(service.getAllByEmployeeId(employee_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("current/{employee_id}")
    @DTO(DepartmentEmployeeGetCurrentDTO.class)
    @ApiOperation(value = "Get DepartmentEmployee by employee's id where his department is current")
    public ResponseEntity<?> getByEmployeeIdAndIsCurrentDepartmentTrue(@PathVariable Long employee_id) {
        try {
            return new ResponseEntity<>(service.getCurrentByEmployeeIdAndIsCurrentDepartmentTrue(employee_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/change")
    @ApiOperation(value = "Change department at employee with id: 'employee_id' to new department with id: 'newdepartment_id'")
    public ResponseEntity<?> changeDepartment(@RequestParam Long newdepartment_id, @RequestParam Long employee_id) {
        try {
            service.changeDepartment(newdepartment_id, employee_id);
            Employee employee = employeeService.getById(employee_id);
            Department department = departmentService.getById(newdepartment_id);
            return new ResponseEntity<>(new RestResponse("Employee '" + employee.getPersonalInfo().getFirstName() + " "
                    + employee.getPersonalInfo().getLastName() + "' moved to department '" + department.getName() + "'"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
