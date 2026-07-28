package com.unlink.validation;

import com.unlink.exception.InvalidStudentDataException;
import com.unlink.model.Student;

public class StudentValidator {

    public static void validate(Student student)
            throws InvalidStudentDataException {

        if (student == null) {
            throw new InvalidStudentDataException(
                    "Student object cannot be null."
            );
        }

        if (student.getUsn() == null || student.getUsn().isBlank()) {
            throw new InvalidStudentDataException(
                    "USN is required."
            );
        }

        if (student.getName() == null || student.getName().isBlank()) {
            throw new InvalidStudentDataException(
                    "Student name is required."
            );
        }

        if (student.getEmail() == null || student.getEmail().isBlank()) {
            throw new InvalidStudentDataException(
                    "Email is required."
            );
        }

        if (!student.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidStudentDataException(
                    "Invalid email address."
            );
        }

        if (student.getDepartment() == null ||
                student.getDepartment().isBlank()) {

            throw new InvalidStudentDataException(
                    "Department is required."
            );
        }

        if (student.getYear() < 1 || student.getYear() > 5) {
            throw new InvalidStudentDataException(
                    "Year must be between 1 and 5."
            );
        }

        if (student.getCgpa() < 0 || student.getCgpa() > 10) {
            throw new InvalidStudentDataException(
                    "CGPA must be between 0 and 10."
            );
        }

        if (student.getPlacementStatus() == null) {
            throw new InvalidStudentDataException(
                    "Placement status is required."
            );
        }
    }
}