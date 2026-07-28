package com.unlink.test;

import com.unlink.model.Student;
import com.unlink.service.StudentService;
import com.unlink.service.impl.StudentServiceImpl;

public class StudentTest {

    public static void main(String[] args) {

//        Student student = new Student();
//
//        student.setUsn("R25DE179");
//        student.setName("Sudhevan");
//        student.setEmail("sudhevan@gmail.com");
//        student.setPhone("7483831089");
//        student.setDepartment("MCA");
//        student.setYear(2025);
//        student.setCgpa(8.78);
//        student.setResumePath("resume.pdf");
//        student.setPlacementStatus(PlacementStatus.NOT_PLACED);
//
//        StudentService service = new StudentServiceImpl();
//        boolean saved = service.registerStudent(student);
//
//
//        if (saved) {
//            System.out.println("Student Registered Successfully!");
//        } else {
//            System.out.println("Student Registration Failed!");
//        }

        StudentService service =
                new StudentServiceImpl();

        Student student =
                service.getStudentById(1);

        System.out.println(student);
    }
}