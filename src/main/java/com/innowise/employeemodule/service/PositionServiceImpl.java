package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        position.setActive(true);
        return repository.save(position);
    }

    @Override
    public Position update(Position position) {
        return repository.save(position);
    }

    @Override
    public void deleteById(Long id) {
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
