package com.innowise.employeemodule.service;

import java.util.List;

public interface AbstractService <E>{

    E getById(Long id);

    List<E> getAll();

    E add(E e);

    E update(E e);

    void deleteById(Long id);

    void deleteAll();

}
