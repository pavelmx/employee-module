package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.*;
import com.innowise.employeemodule.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PersonalInfoService personalInfoService;

    @Autowired
    private HiringEmployeeInfoService hiringEmployeeInfoService;

    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private PositionEmployeeService positionEmployeeService;

    @Override
    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id: '" + id + "' not found"));
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee add(Employee employee) {
        employee.setActive(true);
        return repository.save(employee);
    }

    @Override
    public Employee create(Employee employee, Long position_id, Long department_id) {
        Department department = departmentService.getById(department_id);
        Position position = positionService.getById(position_id);
        //create employee's info
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setAdress(employee.getPersonalInfo().getAdress());
        personalInfo.setDescription(employee.getPersonalInfo().getDescription());
        personalInfo.setEmail(employee.getPersonalInfo().getEmail());
        personalInfo.setFirstName(employee.getPersonalInfo().getFirstName());
        personalInfo.setLastName(employee.getPersonalInfo().getLastName());
        personalInfo.setPhoneNumber(employee.getPersonalInfo().getPhoneNumber());
        personalInfo.setSkype(employee.getPersonalInfo().getSkype());
        PersonalInfo p = personalInfoService.add(personalInfo);
        //create employee
        employee.setPersonalInfo(p);
        employee.setActive(true);
        Employee createdEmployee = repository.save(employee);
        createHistoryLists(department, position, createdEmployee);

        return createdEmployee;
    }

    private void createHistoryLists(Department department, Position position, Employee employee) {
        //write info of hiring
        HiringEmployeeInfo hiringEmployeeInfo = new HiringEmployeeInfo();
        hiringEmployeeInfo.setDateOfHiring(LocalDate.now());
        hiringEmployeeInfo.setEmployee(employee);
        hiringEmployeeInfoService.add(hiringEmployeeInfo);
        //create position_employee
        PositionEmployee positionEmployee = new PositionEmployee();
        positionEmployee.setEmployee(employee);
        positionEmployee.setPosition(position);
        positionEmployee.setStartDateForPosition(LocalDate.now());
        positionEmployeeService.add(positionEmployee);
        //create department_employee
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        departmentEmployee.setCurrentDepartment(true);
        departmentEmployee.setDepartment(department);
        departmentEmployee.setEmployee(employee);
        departmentEmployee.setStartDateInDepartment(LocalDate.now());
        departmentEmployeeService.add(departmentEmployee);
    }

    @Override
    public Employee update(Employee employee) {
        PersonalInfo truePersonalInfo = getById(employee.getId()).getPersonalInfo();
        Long idInfoDB = truePersonalInfo.getId();
        Long idInfoJson = employee.getPersonalInfo().getId();
        if (!idInfoJson.equals(idInfoDB)) {
            throw new RuntimeException("Incorrect personal information for employee with id: '" + employee.getId() + "'");
        }
        personalInfoService.update(employee.getPersonalInfo());
        return repository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        Long idPersonalInfo = getById(id).getPersonalInfo().getId();
        personalInfoService.deleteById(idPersonalInfo);
        hiringEmployeeInfoService.deleteByEmployeeId(id);
        positionEmployeeService.deleteByEmployeeId(id);
        departmentEmployeeService.deleteByEmployeeId(id);
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
        personalInfoService.deleteAll();
        hiringEmployeeInfoService.deleteAll();
        positionEmployeeService.deleteAll();
        departmentEmployeeService.deleteAll();
    }

    @Override
    public void dismissEmployee(Long id) {
        Employee employee = getById(id);
        if (employee.isActive() == true) {
            employee.setActive(false);
            employee = repository.save(employee);
            hiringEmployeeInfoService.setDismissEmployee(employee);
            DepartmentEmployee departmentEmployee = departmentEmployeeService.getCurrentByEmployeeIdAndIsCurrentDepartmentTrue(id);
            departmentEmployeeService.leaveDepartment(departmentEmployee);
            PositionEmployee positionEmployee = positionEmployeeService.getCurrentByEmployeeIdAndEndDateForPositionIsNull(id);
            positionEmployeeService.leavePosition(positionEmployee);
        } else {
            throw new RuntimeException("Employee is unactive now");
        }
    }

    @Override
    public void recoveryEmployee(Long id, Long position_id, Long department_id) {
        Employee employee = getById(id);
        Department department = departmentService.getById(department_id);
        Position position = positionService.getById(position_id);
        if (employee.isActive() == false) {
            employee.setActive(true);
            Employee recoveryEmployee = repository.save(employee);
            createHistoryLists(department, position, recoveryEmployee);
        } else {
            throw new RuntimeException("Employee is active now");
        }
    }

    @Override
    public Page<Employee> getAllPage(int size, int page, String column, String order) {
        Pageable pageable;
        if (order.equals("")) {
            pageable = PageRequest.of(page, size);
        } else {
            pageable = PageRequest.of(page, size, new Sort(Sort.Direction.fromString(order), column));
        }
        return repository.findAll(pageable);
    }


}
