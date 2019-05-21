package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.HiringEmployeeInfo;
import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.repository.HiringEmployeeInfoRepository;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiringEmployeeInfoServiceImpl implements HiringEmployeeInfoService {

    @Autowired
    private HiringEmployeeInfoRepository repository;

    @Override
    public HiringEmployeeInfo getById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("HiringEmployeeInfo with id: '" + id + "' not found"));
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
        return repository.save(hiringEmployeeInfo);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
