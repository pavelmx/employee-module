package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.HiringEmployeeInfoDTO.HiringEmployeeInfoGetDTO;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.entity.RestResponse;
import com.innowise.employeemodule.service.HiringEmployeeInfoService;
import com.innowise.employeemodule.service.PositionService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Find HiringEmployeeInfo by id")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("employee/{id}")
    @ApiOperation(value = "Find all HiringEmployeeInfos by employee's id")
    public ResponseEntity<?> getByEmployeeId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getByEmployeeId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    @ApiOperation(value = "Find all HiringEmployeeInfos")
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-page")
    @DTO(HiringEmployeeInfoGetDTO.class)
    @ApiOperation(value = "Get all pages of HiringEmployeeInfos")
    public ResponseEntity<?> getAllPage(@RequestParam int size, @RequestParam int page,
                                        @RequestParam String column, @RequestParam String order) {
        try {
            return new ResponseEntity<>(service.getAllPage(size, page, column, order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @ApiOperation(value = "Create new HiringEmployeeInfo")
    public ResponseEntity<?> create(@RequestBody HiringEmployeeInfo hiringEmployeeInfo) {
        try {
            return new ResponseEntity<>(service.add(hiringEmployeeInfo), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @ApiOperation(value = "Update HiringEmployeeInfo")
    public ResponseEntity<?> update(@RequestBody HiringEmployeeInfo hiringEmployeeInfo) {
        try {
            return new ResponseEntity<>(service.update(hiringEmployeeInfo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete HiringEmployeeInfo by id")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(new RestResponse("HiringEmployeeInfo with id: '" + id + "' was deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("employee/{id}")
    @ApiOperation(value = "Delete all HiringEmployeeInfos by employee's id")
    public ResponseEntity<?> deleteByEmployeeId(@PathVariable Long id) {
        try {
            service.deleteByEmployeeId(id);
            return new ResponseEntity<>(new RestResponse("All HiringEmployeeInfo's by Employee's id:'" + id + "' were deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/all")
    @ApiOperation(value = "Delete all HiringEmployeeInfos")
    public ResponseEntity<?> deleteAll() {
        try {
            service.deleteAll();
            return new ResponseEntity<>(new RestResponse("All HiringEmployeeInfo's were deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
