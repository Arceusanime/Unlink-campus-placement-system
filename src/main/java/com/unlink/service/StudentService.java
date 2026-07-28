package com.unlink.service;

import com.unlink.model.Student;

import java.util.List;

public interface StudentService {

    boolean registerStudent(Student student);

    Student getStudentById(int id);

    Student getStudentByUsn(String usn);

    List<Student> getAllStudents();

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);

    List<Student> searchStudents(String keyword);

    int getStudentCount();
}