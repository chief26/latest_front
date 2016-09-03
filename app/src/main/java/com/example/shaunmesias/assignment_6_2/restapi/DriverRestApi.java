package com.example.shaunmesias.assignment_6_2.restapi;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/08/30.
 */
public interface DriverRestApi<E, Id> {
    E findById(long id);
    E save(E entity);
    E update(E entity);
    void delete(E entity);
    Set<E> findAll();
}
