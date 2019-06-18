package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Department;
import com.innowise.employeemodule.entity.DepartmentEmployee;
import com.innowise.employeemodule.entity.DepartmentPosition;
import com.innowise.employeemodule.entity.PositionEmployee;
import com.innowise.employeemodule.repository.DepartmentEmployeeRepository;
import com.innowise.employeemodule.repository.PositionEmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DepartmentPositionServiceImpl implements DepartmentPositionService {

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    private PositionEmployeeRepository positionEmployeeRepository;

    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    private static final long maxTimeDiffrence = 10000;

    @Override
    public List<DepartmentPosition> getByEmployeeId(Long id) {
        long incr = 0;
        List<DepartmentEmployee> departmentEmployeeListAll = departmentEmployeeRepository.findByEmployeeId(id);
        List<PositionEmployee> positionEmployeeList = positionEmployeeRepository.findByEmployeeId(id);
        List<DepartmentPosition> depPosList = new ArrayList<>();
        DepartmentEmployee lastDepEmpl = departmentEmployeeListAll.get(0);
        Department lastDep = lastDepEmpl.getDepartment();
        for(PositionEmployee positionEmployee : positionEmployeeList) {
            LocalDateTime endDate = positionEmployee.getEndDateForPosition() == null ? LocalDateTime.now() : positionEmployee.getEndDateForPosition();
            List<DepartmentEmployee> departmentEmployeeList = departmentEmployeeService.getAllByEmployeeIdBetweenDate(id, positionEmployee.getStartDateForPosition(), endDate);
            if (departmentEmployeeList.isEmpty()) {
                DepartmentPosition departmentPosition = new DepartmentPosition();
                departmentPosition.setDepartment(lastDep);
                departmentPosition.setPosition(positionEmployee.getPosition());
                departmentPosition.setStartDate(positionEmployee.getStartDateForPosition());
                departmentPosition.setEndDate(positionEmployee.getEndDateForPosition());
                departmentPosition.setId(incr++);
                depPosList.add(departmentPosition);
            }
            if (!departmentEmployeeList.isEmpty()) {
                LocalDateTime stDate = departmentEmployeeList.get(0).getStartDateInDepartment();
                LocalDateTime edDate = positionEmployee.getStartDateForPosition();
                long diffrence = Math.abs(stDate.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli() - edDate.toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
                if(diffrence > maxTimeDiffrence){
                    DepartmentPosition departmentPosition = new DepartmentPosition();
                    departmentPosition.setDepartment(lastDepEmpl.getDepartment());
                    departmentPosition.setPosition(positionEmployee.getPosition());
                    departmentPosition.setStartDate(positionEmployee.getStartDateForPosition());
                    LocalDateTime endDate2 = lastDepEmpl.getEndDateInDepartment() == null ? positionEmployee.getEndDateForPosition() : lastDepEmpl.getEndDateInDepartment();
                    departmentPosition.setEndDate(endDate2);
                    departmentPosition.setId(incr++);
                    depPosList.add(departmentPosition);
                }
                for (DepartmentEmployee departmentEmployee : departmentEmployeeList) {
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
                }
            }
        }
        log.info("Return DepPosList {} for EmployeeID: {} ", depPosList, id);
        return depPosList;
    }
}
