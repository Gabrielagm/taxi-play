package com.isil.dao;

import java.util.List;

public interface DAO<T , K> {
    public void create(T t);
    public boolean delete(K k);
    public boolean update(T t);
    public T findById(K k);
    public List<T> findAll();
}
