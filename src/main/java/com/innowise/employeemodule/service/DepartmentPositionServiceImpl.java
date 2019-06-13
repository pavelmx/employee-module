package com.innowise.employeemodule.service;

import com.innowise.employeemodule.dto.DepartmentPositionDTO.DepartmentPositionDTO;
import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.DepartmentPosition;
import com.innowise.employeemodule.entity.PositionEmployee;
import com.innowise.employeemodule.repository.DepartmentEmployeeRepository;
import com.innowise.employeemodule.repository.PositionEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentPositionServiceImpl implements DepartmentPositionService {

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    private PositionEmployeeRepository positionEmployeeRepository;

    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    @Override
    public List<DepartmentPosition> getByEmployeeId(Long id) {
        long incr = 0;
        List<DepartmentEmployee> departmentEmployeeListAll = departmentEmployeeRepository.findByEmployee_Id(id);
        List<PositionEmployee> positionEmployeeList = positionEmployeeRepository.findByEmployee_Id(id);
        List<DepartmentPosition> depPosList = new ArrayList<>();
        DepartmentEmployee lastDepEmpl = departmentEmployeeListAll.get(0);
        Department lastDep = lastDepEmpl.getDepartment();
        for(PositionEmployee positionEmployee : positionEmployeeList) {
            LocalDateTime endDate = positionEmployee.getEndDateForPosition() == null ? LocalDateTime.now() : positionEmployee.getEndDateForPosition();
            List<DepartmentEmployee> departmentEmployeeList = departmentEmployeeService.getAllByEmployeeIdBetweenDate(id, positionEmployee.getStartDateForPosition(), endDate);
            if (departmentEmployeeList.size() == 0) {
                System.out.println("departmentEmployeeList.size() == 0");
                DepartmentPosition departmentPosition = new DepartmentPosition();
                departmentPosition.setDepartment(lastDep);
                departmentPosition.setPosition(positionEmployee.getPosition());
                departmentPosition.setStartDate(positionEmployee.getStartDateForPosition());
                departmentPosition.setEndDate(positionEmployee.getEndDateForPosition());
                departmentPosition.setId(incr++);
                depPosList.add(departmentPosition);
            }
            if (departmentEmployeeList.size() > 0) {
                System.out.println("departmentEmployeeList.size() > 0");
                LocalDateTime stDate = departmentEmployeeList.get(0).getStartDateInDepartment();
                LocalDateTime edDate = positionEmployee.getStartDateForPosition();
                long diffrence = Math.abs(stDate.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() - edDate.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
                System.out.println("Diff: " + diffrence);
                if(diffrence > 10000){
                    DepartmentPosition departmentPosition = new DepartmentPosition();
                    //lastDepEmpl = departmentEmployeeList.get(0);
                    departmentPosition.setDepartment(lastDepEmpl.getDepartment());
                    departmentPosition.setPosition(positionEmployee.getPosition());
                    departmentPosition.setStartDate(positionEmployee.getStartDateForPosition());
                    LocalDateTime endDate2 = lastDepEmpl.getEndDateInDepartment() == null ? positionEmployee.getEndDateForPosition() : lastDepEmpl.getEndDateInDepartment();
                    departmentPosition.setEndDate(endDate2);
                    departmentPosition.setId(incr++);
                    depPosList.add(departmentPosition);
                    System.out.println("In diffrence");
                }
                for (int i = 0; i < departmentEmployeeList.size(); i++) {
                    DepartmentEmployee departmentEmployee = departmentEmployeeList.get(i);
                    DepartmentPosition departmentPosition = new DepartmentPosition();
                    departmentPosition.setDepartment(departmentEmployee.getDepartment());
                    departmentPosition.setPosition(positionEmployee.getPosition());
                    departmentPosition.setStartDate(departmentEmployee.getStartDateInDepartment());
                    LocalDateTime endDate2 = departmentEmployee.getEndDateInDepartment() == null ? positionEmployee.getEndDateForPosition() : departmentEmployee.getEndDateInDepartment();
                    departmentPosition.setEndDate(endDate2);
                    departmentPosition.setId(incr++);
                    depPosList.add(departmentPosition);
                    lastDepEmpl = departmentEmployee;
                    lastDep = departmentEmployee.getDepartment();
                    System.out.println("In FOR. lastDep: " + lastDep.getName());
                }
            }
        }
        return depPosList;
    }
}
