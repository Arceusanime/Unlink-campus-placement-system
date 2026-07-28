package com.unlink.dao;

import com.unlink.model.Student;
import java.util.List;

public interface StudentDAO {
    boolean addStudent(Student student);

    Student getStudentById(int id);

    Student getStudentByUsn(String usn);

    List<Student> getAllStudents();

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);

    List<Student> searchStudents(String keyword);

    int getStudentCount();
}
