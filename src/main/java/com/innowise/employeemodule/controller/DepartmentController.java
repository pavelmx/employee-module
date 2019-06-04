package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.DepartmentDTO.DepartmentCreationDTO;
import com.innowise.employeemodule.dto.DepartmentDTO.DepartmentGetDTO;
import com.innowise.employeemodule.dto.DepartmentDTO.DepartmentGetWitoutManagerDTO;
import com.innowise.employeemodule.dto.DepartmentDTO.DepartmentUpdateDTO;
import com.innowise.employeemodule.dto.EmployeeDTO.EmployeeFullNameDTO;
import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.RestResponse;
import com.innowise.employeemodule.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/{id}")
    @DTO(DepartmentGetDTO.class)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    @DTO(DepartmentGetWitoutManagerDTO.class)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-page")
    @DTO(DepartmentGetDTO.class)
    public ResponseEntity<?> getAllPage(@RequestParam int size, @RequestParam int page,
                                        @RequestParam String column, @RequestParam String order
                                      //  @QuerydslPredicate(root = Department.class) Predicate predicate
    ) {
        try {
            return new ResponseEntity<>(service.getAllPage(size, page, column, order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@DTO(DepartmentCreationDTO.class) Department department, @RequestParam Long manager_id) {
        try {
            return new ResponseEntity<>(service.create(department, manager_id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@DTO(DepartmentUpdateDTO.class) Department department, @RequestParam Long manager_id) {
        try {
            return new ResponseEntity<>(service.edit(department, manager_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(new RestResponse("Department with id: '" + id + "' was deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        try {
            service.deleteAll();
            return new ResponseEntity<>(new RestResponse("All departments were deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


}
