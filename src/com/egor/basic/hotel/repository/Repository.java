package com.egor.basic.hotel.repository;

import com.egor.basic.hotel.annotations.Annotations.*;

import java.util.List;


public interface Repository<T> {
    void save(T entity);
    List<T> findAll();
}
