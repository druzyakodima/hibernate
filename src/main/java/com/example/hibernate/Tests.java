package com.example.hibernate;

import com.example.hibernate.entities.Student;
import com.example.hibernate.repositories.StudentRepository;
import com.example.hibernate.repositories.StudentRepositoryImpl;
import com.example.hibernate.services.StudentService;
import com.example.hibernate.utils.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class Tests {

    private StudentService studentService;

    public Tests() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        StudentRepository studentRepository = new StudentRepositoryImpl(factory);
        studentService = new StudentService(studentRepository, factory);
    }

    public void start() {

        studentService.printTotalCount();
        System.out.println("\nСоздаем список из 1000 студентов и добавляем в БД...");
        List<Student> students = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {
            students.add(new Student("Student_" + "0".repeat(4 - Integer.toString(i).length()) + i, (int) (Math.random() * 10)));
        }
        studentService.addStudents(students);

        studentService.printTotalCount();
        studentService.removeAllStudents();
        studentService.printTotalCount();
        System.out.println();

        System.out.println("Создаем новый список из 10 студентов и добавляем в БД...");
        List<Student> studentsShort = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            studentsShort.add(new Student("Student_" + "0".repeat(4 - Integer.toString(i).length()) + i, (int) (Math.random() * 10)));
        }
        studentService.addStudents(studentsShort);
        studentService.printAllStudents();
        studentService.printTotalCount();
        System.out.println();

        List<Student> studentsList = studentService.findAllStudents();
        Long id = studentsList.get(2).getId();
        System.out.println("Удаление 3 записи в таблице, Student_Id = " + id);
        studentService.deleteStudentById(id);
        studentService.printAllStudents();
        System.out.println();

        String newName = "NEW_STUDENT_0000";
        studentsList = studentService.findAllStudents();
        Student student = studentsList.get(studentsList.size()-2);
        System.out.println("Изменить имя предпоследнего в списке студента...");
        id = student.getId();
        System.out.println("старое имя – " + student.getName() + " новое имя – " + newName);
        studentService.renameStudent(id, newName);
        System.out.println(studentService.findStudentById(id));
        System.out.println();

        studentService.printAllStudents();
        studentService.printTotalCount();
        System.out.println();

        System.out.println("Удалить все записи из таблицы...");
        studentService.removeAllStudents();

        System.out.println("Закрыть фабрику HibernateUtil...");
        HibernateUtil.shutdown();
    }
}
