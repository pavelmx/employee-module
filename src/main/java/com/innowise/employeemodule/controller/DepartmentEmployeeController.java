package com.innowise.employeemodule.controller;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.PositionEmployee;
import com.innowise.employeemodule.service.DepartmentEmployeeService;
import com.innowise.employeemodule.service.DepartmentService;
import com.innowise.employeemodule.service.EmployeeService;
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
    public ResponseEntity<DepartmentEmployee> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentEmployee>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DepartmentEmployee> create(@RequestBody DepartmentEmployee departmentEmployee) {
        return new ResponseEntity<>(service.add(departmentEmployee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DepartmentEmployee> update(@RequestBody DepartmentEmployee departmentEmployee) {
        return new ResponseEntity<>(service.update(departmentEmployee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable  Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("DepartmentEmployee with id: '" + id + "' was deleted", HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>("All DepartmentEmployee's deleted", HttpStatus.OK);
    }

    @GetMapping("/all-page")
    public ResponseEntity<Page<DepartmentEmployee>> getAllPage(@RequestParam int size, @RequestParam int page,
                                                       @RequestParam String column, @RequestParam String order) {
        return new ResponseEntity<>(service.getAllPage(size, page, column, order), HttpStatus.OK);
    }

    @GetMapping("all/{employee_id}")
    public ResponseEntity<List<DepartmentEmployee>> getAllByEmployeeId(@PathVariable Long employee_id){
        return new ResponseEntity<>(service.getAllByEmployeeId(employee_id), HttpStatus.OK);
    }

    @GetMapping("current/{employee_id}")
    public ResponseEntity<DepartmentEmployee> getByEmployeeIdAndIsCurrentDepartmentTrue(@PathVariable Long employee_id){
        return new ResponseEntity<>(service.getByEmployeeIdAndIsCurrentDepartmentTrue(employee_id), HttpStatus.OK);
    }

    @GetMapping("/change")
    public ResponseEntity<String> changeDepartment(@RequestParam Long newdepartment_id, @RequestParam Long employee_id){
        service.changeDepartment(newdepartment_id, employee_id);
        Employee employee = employeeService.getById(employee_id);
        Department department = departmentService.getById(newdepartment_id);
        return new ResponseEntity<>("Employee '" + employee.getPersonalInfo().getFirstName() + " "
                + employee.getPersonalInfo().getLastName() + "' moved to department '" + department.getName() + "'", HttpStatus.OK);
    }

}
