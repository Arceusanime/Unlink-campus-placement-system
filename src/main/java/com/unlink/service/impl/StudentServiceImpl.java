package com.unlink.service.impl;

import com.unlink.dao.StudentDAO;
import com.unlink.dao.impl.StudentDAOImpl;
import com.unlink.model.Student;
import com.unlink.service.StudentService;
import com.unlink.exception.InvalidStudentDataException;
import com.unlink.validation.StudentValidator;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    public StudentServiceImpl() {
        studentDAO = new StudentDAOImpl();
    }

    @Override
    public boolean registerStudent(Student student) {

        try {

            StudentValidator.validate(student);

            return studentDAO.addStudent(student);

        }
        catch (InvalidStudentDataException e){

            System.out.println(e.getMessage());

            return false;

        }

    }

    @Override
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    @Override
    public Student getStudentByUsn(String usn) {
        return studentDAO.getStudentByUsn(usn);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @Override
    public boolean updateStudent(Student student) {

        try {

            StudentValidator.validate(student);

            return studentDAO.updateStudent(student);

        } catch (InvalidStudentDataException e) {

            System.out.println(e.getMessage());

            return false;
        }
    }

    @Override
    public boolean deleteStudent(int id) {
        return studentDAO.deleteStudent(id);
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        return studentDAO.searchStudents(keyword);
    }

    @Override
    public int getStudentCount() {
        return studentDAO.getStudentCount();
    }
}