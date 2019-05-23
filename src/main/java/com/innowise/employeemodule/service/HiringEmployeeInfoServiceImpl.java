package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.HiringEmployeeInfoRepository;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class HiringEmployeeInfoServiceImpl implements HiringEmployeeInfoService {

    @Autowired
    private HiringEmployeeInfoRepository repository;

    @Override
    public HiringEmployeeInfo getById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("HiringEmployeeInfo with id: '" + id + "' not found"));
    }

    @Override
    public List<HiringEmployeeInfo> getAll() {
        return repository.findAll();
    }

    @Override
    public HiringEmployeeInfo add(HiringEmployeeInfo hiringEmployeeInfo) {
        return repository.save(hiringEmployeeInfo);
    }

    @Override
    public HiringEmployeeInfo update(HiringEmployeeInfo hiringEmployeeInfo) {
        if(!repository.existsById(hiringEmployeeInfo.getId())){
            throw new EntityNotFoundException("HiringEmployeeInfo with id: '" + hiringEmployeeInfo.getId() + "' not found");
        }
        return repository.save(hiringEmployeeInfo);
    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("HiringEmployeeInfo with id: '" + id + "' not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public HiringEmployeeInfo setDismissEmployee(Employee employee) {
        HiringEmployeeInfo hiringEmployeeInfo = repository.findByEmployee_IdAndDateOfDismissalIsNull(employee.getId())
                .orElseThrow( () -> new EntityNotFoundException("HiringEmployeeInfo by employee's id: '" + employee.getId() + "' not found"));
        hiringEmployeeInfo.setDateOfDismissal(LocalDate.now());
        return repository.save(hiringEmployeeInfo);
    }
}
