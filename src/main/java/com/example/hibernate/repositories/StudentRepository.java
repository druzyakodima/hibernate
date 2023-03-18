package com.example.hibernate.repositories;

import com.example.hibernate.entities.Student;

import java.util.List;

public interface StudentRepository extends Repository<Student, Long> {
    Long countAll();

    List<Student> mergeBatch(List<Student> entities);

    List<Student> findByName(String name);

    boolean removeAll();
}
