package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.config.DTO;
import com.innowise.employeemodule.dto.PositionDTO.PositionCreationDTO;
import com.innowise.employeemodule.dto.PositionDTO.PositionGetDTO;
import com.innowise.employeemodule.dto.PositionDTO.PositionGetWitoutActiveDTO;
import com.innowise.employeemodule.dto.PositionDTO.PositionUpdateDTO;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.entity.RestResponse;
import com.innowise.employeemodule.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("position")
public class PositionController {

    @Autowired
    private PositionService service;

    @GetMapping("/{id}")
    @DTO(PositionGetWitoutActiveDTO.class)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    @DTO(PositionGetWitoutActiveDTO.class)
    public ResponseEntity<?> getAll() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all-page")
    @DTO(PositionGetDTO.class)
    public ResponseEntity<?> getAllPage(@RequestParam int size, @RequestParam int page,
                                        @RequestParam String column, @RequestParam String order) {
        try {
            return new ResponseEntity<>(service.getAllPage(size, page, column, order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@DTO(PositionCreationDTO.class) Position position) {
        try {
            return new ResponseEntity<>(service.add(position), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@DTO(PositionUpdateDTO.class) Position position) {
        try {
            return new ResponseEntity<>(service.update(position), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(new RestResponse("Position with id: '" + id + "' was deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        try {
            service.deleteAll();
            return new ResponseEntity<>(new RestResponse("All positions were deleted"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("disable/{id}")
    public ResponseEntity<?> disablePositionById(@PathVariable("id") Long id) {
        try {
            Position position = service.getById(id);
            service.disable(id);
            return new ResponseEntity<>(new RestResponse("Position '" + position.getName() + "' disabled"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("enable/{id}")
    public ResponseEntity<?> enablePositionById(@PathVariable("id") Long id) {
        try {
            Position position = service.getById(id);
            service.enable(id);
            return new ResponseEntity<>(new RestResponse("Position '" + position.getName() + "' enabled"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("enable/all")
    @DTO(PositionGetWitoutActiveDTO.class)
    public ResponseEntity<?> getEnableAll() {
        try {
            return new ResponseEntity<>(service.getEnableAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
