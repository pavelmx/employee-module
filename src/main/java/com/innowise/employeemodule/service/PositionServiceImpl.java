package com.innowise.employeemodule.service;

import com.innowise.employeemodule.entity.Employee;
import com.innowise.employeemodule.entity.Position;
import com.innowise.employeemodule.repository.EmployeeRepository;
import com.innowise.employeemodule.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                .orElseThrow(() -> new EntityNotFoundException("Position with id: '" + id + "' not found"));
    }

    @Override
    public List<Position> getAll() {
        return repository.findAll();
    }

    @Override
    public Position add(Position position) {
        if (repository.existsByName(position.getName())) {
            throw new EntityExistsException("Position with name: '" + position.getName() + "' already exists");
        }
        position.setActive(true);
        return repository.save(position);
    }

    @Override
    public Position update(Position position) {
        if (!repository.existsById(position.getId())) {
            throw new EntityNotFoundException("Position with id: '" + position.getId() + "' not found");
        }
        Position oldPosition = getById(position.getId());
        if(!oldPosition.getName().equals(position.getName())){
            if (repository.existsByName(position.getName())) {
                throw new EntityExistsException("Position with name: '" + position.getName() + "' already exists");
            }
        }
        return repository.save(position);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Position with id: '" + id + "' not found");
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void disable(Long id) {
        Position position = getById(id);
        if (position.isActive() == true) {
            position.setActive(false);
            repository.save(position);
        } else {
            throw new RuntimeException("Position is disable now");
        }
    }

    @Override
    public void enable(Long id) {
        Position position = getById(id);
        if (position.isActive() == false) {
            position.setActive(true);
            repository.save(position);
        } else {
            throw new RuntimeException("Position is enable now");
        }
    }

    @Override
    public List<Position> getEnableAll() {
        return repository.findByIsActiveTrue();
    }

    @Override
    public Page<Position> getAllPage(int size, int page, String column, String order) {
        Pageable pageable;
        if(order.equals("")){
            pageable = PageRequest.of(page, size);
        }else{
            pageable = PageRequest.of(page, size, new Sort(Sort.Direction.fromString(order), column));
        }
        return repository.findAll(pageable);
    }
}
