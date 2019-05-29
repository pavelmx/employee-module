package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.PersonalInfo;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.repository.PersonalInfoRepository;
import com.innowise.employeemodule.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

    @Autowired
    private PersonalInfoRepository repository;

    @Override
    public PersonalInfo getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PersonalInfo with id: '" + id + "' not found"));
    }

    @Override
    public List<PersonalInfo> getAll() {
        return repository.findAll();
    }

    @Override
    public PersonalInfo add(PersonalInfo personalInfo) {
        if (repository.existsByEmail(personalInfo.getEmail())) {
            throw new EntityExistsException("Employee with such email already exists");
        }
        if (repository.existsBySkype(personalInfo.getSkype())) {
            throw new EntityExistsException("Employee with such skype already exists");
        }
        if (repository.existsByPhoneNumber(personalInfo.getPhoneNumber())) {
            throw new EntityExistsException("Employee with such phone number already exists");
        }
        return repository.save(personalInfo);
    }

    @Override
    public PersonalInfo update(PersonalInfo personalInfo) {
        if (!repository.existsById(personalInfo.getId())) {
            throw new EntityNotFoundException("Employee's personal info with id: '" + personalInfo.getId() + "' not found");
        }
        PersonalInfo oldPersonalInfo = getById(personalInfo.getId());
        if (!oldPersonalInfo.getEmail().equals(personalInfo.getEmail())) {
            if (repository.existsByEmail(personalInfo.getEmail())) {
                throw new EntityExistsException("Employee with such email already exists");
            }}
        if (!oldPersonalInfo.getSkype().equals(personalInfo.getSkype())) {
            if (repository.existsBySkype(personalInfo.getSkype())) {
                throw new EntityExistsException("Employee with such skype already exists");
            }
        }
        if (!oldPersonalInfo.getPhoneNumber().equals(personalInfo.getPhoneNumber())) {
            if (repository.existsByPhoneNumber(personalInfo.getPhoneNumber())) {
                throw new EntityExistsException("Employee with such phone number already exists");
            }
        }
        return repository.save(personalInfo);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("PersonalInfo with id: '" + id + "' not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
