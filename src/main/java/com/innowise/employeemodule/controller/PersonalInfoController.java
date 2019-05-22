package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.service.PersonalInfoService;
import com.innowise.employeemodule.service.PositionService;
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
    public ResponseEntity<PersonalInfo> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonalInfo>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> create(@RequestBody PersonalInfo personalInfo) {
        return new ResponseEntity<>(service.add(personalInfo), HttpStatus.CREATED);
    }

    @PutMapping
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
