package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.HiringEmployeeInfoRepository;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<HiringEmployeeInfo> getByEmployeeId(Long employee_id) {
        return repository.findByEmployee_Id(employee_id);
    }

    @Override
    public List<HiringEmployeeInfo> getAll() {
        return repository.findAll();
    }

    @Override
    public HiringEmployeeInfo add(HiringEmployeeInfo hiringEmployeeInfo) {
        hiringEmployeeInfo.setDateOfHiring(LocalDate.now());
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
    public void deleteByEmployeeId(Long employee_id) {
        List<HiringEmployeeInfo> hiringEmployeeInfos = new ArrayList<>();
        hiringEmployeeInfos = getByEmployeeId(employee_id);
        for (HiringEmployeeInfo hiringEmployeeInfo : hiringEmployeeInfos) {
            deleteById(hiringEmployeeInfo.getId());
        }
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

    @Override
    public Page<HiringEmployeeInfo> getAllPage(int size, int page, String column, String order) {
        Pageable pageable;
        if(order.equals("")){
            pageable = PageRequest.of(page, size);
        }else{
            pageable = PageRequest.of(page, size, new Sort(Sort.Direction.fromString(order), column));
        }
        return repository.findAll(pageable);
    }
}
