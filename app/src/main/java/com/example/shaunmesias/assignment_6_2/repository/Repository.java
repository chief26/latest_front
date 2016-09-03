package com.example.shaunmesias.assignment_6_2.repository;

import android.database.Cursor;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public interface Repository<E, Id> {
    E findById(long id);
    E save(E entity);
    E update(E entity);
    E delete(E entity);
    Cursor selectAll();
    Set<E> findAll();
    int deleteAll();
}
