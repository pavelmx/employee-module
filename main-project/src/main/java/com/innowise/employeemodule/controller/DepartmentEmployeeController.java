package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.DepartmentDTO.DepartmentGetWitoutManagerDTO;
import com.innowise.employeemodule.dto.DepartmentEmployeeDTO.DepartmentEmployeeGetCurrentDTO;
import com.innowise.employeemodule.dto.DepartmentEmployeeDTO.DepartmentEmployeeGetDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeGetDTO;
import com.innowise.employeemodule.entity.*;
import com.innowise.employeemodule.service.DepartmentEmployeeService;
import com.innowise.employeemodule.service.DepartmentService;
import com.innowise.employeemodule.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
    public ResponseEntity<?> create(@RequestBody DepartmentEmployee departmentEmployee) {
        try {
            return new ResponseEntity<>(service.add(departmentEmployee), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody DepartmentEmployee departmentEmployee) {
        try {
            return new ResponseEntity<>(service.update(departmentEmployee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(new RestResponse("DepartmentEmployee with id: '" + id + "' was deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/all")
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


    @GetMapping("/all-by-date")
    @DTO(DepartmentEmployeeGetDTO.class)
    public ResponseEntity<?> getAllByDate(@RequestParam Long employeeId, @RequestParam String start, @RequestParam String end) {
        try {
            LocalDateTime startDate = LocalDateTime.parse(start);
            LocalDateTime endDate;
            if(end.equals("null"))
                endDate = LocalDateTime.now();
            else
                endDate = LocalDateTime.parse(end);
            return new ResponseEntity<>(service.getAllByEmployeeIdBetweenDate(employeeId, startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("current/{employee_id}")
    @DTO(DepartmentEmployeeGetCurrentDTO.class)
    public ResponseEntity<?> getByEmployeeIdAndIsCurrentDepartmentTrue(@PathVariable Long employee_id) {
        try {
            return new ResponseEntity<>(service.getCurrentByEmployeeIdAndIsCurrentDepartmentTrue(employee_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/change")
    public ResponseEntity<?> changeDepartment(@RequestParam Long newdepartment_id, @RequestParam Long employee_id, @RequestParam String description) {
        try {
            service.changeDepartment(newdepartment_id, employee_id, description);
            Employee employee = employeeService.getById(employee_id);
            Department department = departmentService.getById(newdepartment_id);
            return new ResponseEntity<>(new RestResponse("Employee '" + employee.getPersonalInfo().getFirstName() + " "
                    + employee.getPersonalInfo().getLastName() + "' moved to department '" + department.getName() + "'"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
