package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoCreationDTO;
import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoCreationInputDTO;
import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoGetDTO;
import com.innowise.employeemodule.dto.PersonalInfoDTO.PersonalInfoUpdateDTO;
import com.innowise.employeemodule.entity.PersonalInfo;
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
    public ResponseEntity<PersonalInfo> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @DTO(PersonalInfoGetDTO.class)
    public ResponseEntity<List<PersonalInfo>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @DTO(PersonalInfoCreationDTO.class)
    public ResponseEntity<PersonalInfo> create(@DTO(PersonalInfoCreationInputDTO.class) PersonalInfo personalInfo) {
        return new ResponseEntity<>(service.add(personalInfo), HttpStatus.CREATED);
    }

    @PutMapping
    @DTO(PersonalInfoUpdateDTO.class)
    public ResponseEntity<PersonalInfo> update(@RequestBody PersonalInfo personalInfo) {
        return new ResponseEntity<>(service.update(personalInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("PersonalInfo with id: '" + id + "' was deleted", HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>("All PersonalInfo's deleted", HttpStatus.OK);
    }
///////////////////////
}
