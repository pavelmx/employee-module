package com.innowise.employeemodule.controller;


import com.innowise.employeemodule.entity.RestResponse;
import com.innowise.employeemodule.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("department-position")
public class DepartmentPositionController {

    @Autowired
    private DepartmentPositionService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getByEmployeeId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getByEmployeeId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
