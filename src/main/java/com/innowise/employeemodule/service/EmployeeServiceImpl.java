package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.*;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
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
        //write info of hiring
        HiringEmployeeInfo hiringEmployeeInfo = new HiringEmployeeInfo();
        hiringEmployeeInfo.setDateOfHiring(LocalDate.now());
        hiringEmployeeInfo.setEmployee(createdEmployee);
        hiringEmployeeInfoService.add(hiringEmployeeInfo);
        //create position_employee
        PositionEmployee positionEmployee = new PositionEmployee();
        positionEmployee.setEmployee(createdEmployee);
        positionEmployee.setPosition(position);
        positionEmployee.setStartDateForPosition(LocalDate.now());
        PositionEmployee createdPositionEmployee = positionEmployeeService.add(positionEmployee);
        //create department_employee
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        departmentEmployee.setCurrentDepartment(true);
        departmentEmployee.setDepartment(department);
        departmentEmployee.setEmployee(createdEmployee);
        departmentEmployee.setPositionEmployee(createdPositionEmployee);
        departmentEmployee.setStartDateInDepartment(LocalDate.now());
        departmentEmployeeService.add(departmentEmployee);

        return createdEmployee;
    }

    @Override
    public Employee update(Employee employee) {
        PersonalInfo truePersonalInfo = getById(employee.getId()).getPersonalInfo();
        Long idInfo = truePersonalInfo.getId();
        Long idEmp = employee.getPersonalInfo().getId();
        if (!idEmp.equals(idInfo)) {
            throw new RuntimeException("Incorrect personal information for employee with id: '" + employee.getId() + "'");
        }
        personalInfoService.update(employee.getPersonalInfo());
        return repository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        Long idPersonalInfo = getById(id).getPersonalInfo().getId();
        repository.deleteById(id);
        personalInfoService.deleteById(idPersonalInfo);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
        personalInfoService.deleteAll();
    }

    @Override
    public void dismissEmployee(Long id) {
        Employee employee = getById(id);
        if (employee.isActive() == true) {
            employee.setActive(false);
            repository.save(employee);
            hiringEmployeeInfoService.setDismissEmployee(employee);
        } else {
            throw new RuntimeException("Employee is unactive now");
        }
    }

    @Override
    public void recoveryEmployee(Long id) {
        Employee employee = getById(id);
        if (employee.isActive() == false) {
            employee.setActive(true);
            Employee createdEmployee = repository.save(employee);
            HiringEmployeeInfo hiringEmployeeInfo = new HiringEmployeeInfo();
            hiringEmployeeInfo.setDateOfHiring(LocalDate.now());
            hiringEmployeeInfo.setEmployee(createdEmployee);
            hiringEmployeeInfoService.add(hiringEmployeeInfo);
        } else {
            throw new RuntimeException("Employee is active now");
        }
    }

    @Override
    public Page<Employee> getAllPage(int size, int page, String column, String order) {
        Pageable pageable;
        if(order.equals("")){
            pageable = PageRequest.of(page, size);
        }else{
            pageable = PageRequest.of(page, size, new Sort(Sort.Direction.fromString(order), column));
        }
        return repository.findAll(pageable);
    }


}
