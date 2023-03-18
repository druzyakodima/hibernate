package com.example.hibernate.repositories;

import com.example.hibernate.entities.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private SessionFactory factory;

    public StudentRepositoryImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public Student findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        }
    }

    @Override
    public List<Student> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Student> studentsList = session.createQuery("from Student").list();
            session.getTransaction().commit();
            return studentsList;
        }
    }

    @Override
    public Student merge(Student entity) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Student student = (Student) session.merge(entity);
            session.getTransaction().commit();
            return student;
        }
    }

    @Override
    public List<Student> mergeBatch(List<Student> entities) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            entities.forEach(session::merge);
//            for (Student entity : entities) {
//                Student mergedEntity = (Student) session.merge(entity);
//                entity = mergedEntity;
//            }
            session.getTransaction().commit();
            return entities;
        }
    }

    @Override
    public boolean delete(Student entity) {
        return deleteById(entity.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.getNamedQuery("deleteById")
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("delete from Student").executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public Long countAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Long countResult = (Long) session
                    .getNamedQuery("countAll")
                    .uniqueResult();
            session.getTransaction().commit();
            return countResult;
        }
    }

    @Override
    public List<Student> findByName(String name) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Student> studentsList = session.getNamedQuery("findByName")
                    .setParameter("name", name)
                    .list();
            session.getTransaction().commit();
            return studentsList;
        }
    }
}
