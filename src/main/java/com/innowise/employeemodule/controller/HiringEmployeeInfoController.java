package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.service.HiringEmployeeInfoService;
import com.innowise.employeemodule.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hiring-employee-info")
public class HiringEmployeeInfoController {

    @Autowired
    private HiringEmployeeInfoService service;

    @GetMapping("/{id}")
    public ResponseEntity<HiringEmployeeInfo> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HiringEmployeeInfo>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/all-page")
    public ResponseEntity<Page<HiringEmployeeInfo>> getAllPage(@RequestParam int size, @RequestParam int page,
                                                               @RequestParam String column, @RequestParam String order) {
        return new ResponseEntity<>(service.getAllPage(size, page, column, order), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HiringEmployeeInfo> create(@RequestBody HiringEmployeeInfo hiringEmployeeInfo) {
        return new ResponseEntity<>(service.add(hiringEmployeeInfo), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HiringEmployeeInfo> update(@RequestBody HiringEmployeeInfo hiringEmployeeInfo) {
        return new ResponseEntity<>(service.update(hiringEmployeeInfo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("HiringEmployeeInfo with id: '" + id + "' was deleted", HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>("All HiringEmployeeInfo's deleted", HttpStatus.OK);
    }

}
