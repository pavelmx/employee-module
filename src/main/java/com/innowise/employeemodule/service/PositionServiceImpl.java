package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository repository;

    @Override
    public Position getById(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Position with id: '" + id + "' not found"));
    }

    @Override
    public List<Position> getAll() {
        return repository.findAll();
    }

    @Override
    public Position add(Position position) {
        if(repository.existsByName(position.getName())){
            throw new EntityExistsException("Position with name: '" + position.getName() + "' already exists");
        }
        position.setActive(true);
        return repository.save(position);
    }

    @Override
    public Position update(Position position) {
        if(!repository.existsByName(position.getName())){
            throw new EntityNotFoundException("Position with name: '" + position.getName() + "' not found");
        }
        if(!repository.existsById(position.getId())){
            throw new EntityNotFoundException("Position with id: '" + position.getId() + "' not found");
        }
        return repository.save(position);
    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Position with id: '" + id + "' not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void disable(Long id){
        Position position = getById(id);
        position.setActive(false);
        repository.save(position);
    }
}
