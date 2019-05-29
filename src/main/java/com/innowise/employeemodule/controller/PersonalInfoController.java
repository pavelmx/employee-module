package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoCreationDTO;
import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoCreationInputDTO;
import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoGetDTO;
import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoUpdateDTO;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.entity.RestResponse;
import com.innowise.employeemodule.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personal-info")
public class PersonalInfoController {

    @Autowired
    private PersonalInfoService service;

    //////////////////////
    @GetMapping("/{id}")
    @DTO(PersonalInfoGetDTO.class)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    @DTO(PersonalInfoGetDTO.class)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @DTO(PersonalInfoCreationDTO.class)
    public ResponseEntity<?> create(@DTO(PersonalInfoCreationInputDTO.class) PersonalInfo personalInfo) {
        try {
            return new ResponseEntity<>(service.add(personalInfo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @DTO(PersonalInfoUpdateDTO.class)
    public ResponseEntity<?> update(@RequestBody PersonalInfo personalInfo) {
        try {
            return new ResponseEntity<>(service.update(personalInfo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(new RestResponse("PersonalInfo with id: '" + id + "' was deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        try {
            service.deleteAll();
            return new ResponseEntity<>(new RestResponse("All PersonalInfo's deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
///////////////////////
}
