package com.example.hibernate.repositories;

import java.util.List;

public interface Repository <T, ID> {
    T findById(ID id);
    List<T> findAll();
    T merge(T entity);
    boolean delete(T entity);
    boolean deleteById(ID id);

}
