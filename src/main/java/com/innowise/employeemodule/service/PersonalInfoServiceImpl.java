package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import com.innowise.employeemodule.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

    @Autowired
    private PersonalInfoRepository repository;

    @Override
    public PersonalInfo getById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("PersonalInfo with id: '" + id + "' not found"));
    }

    @Override
    public List<PersonalInfo> getAll() {
        return repository.findAll();
    }

    @Override
    public PersonalInfo add(PersonalInfo personalInfo) {
        return repository.save(personalInfo);
    }

    @Override
    public PersonalInfo update(PersonalInfo personalInfo) {
        return repository.save(personalInfo);
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
