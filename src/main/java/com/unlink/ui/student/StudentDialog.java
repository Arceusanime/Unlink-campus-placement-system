package com.unlink.ui.student;

import com.unlink.ui.common.FormPanel;
import com.unlink.model.PlacementStatus;
import com.unlink.model.Student;
import com.unlink.service.StudentService;
import com.unlink.service.impl.StudentServiceImpl;

import javax.swing.JOptionPane;

import javax.swing.*;
import java.awt.*;

public class StudentDialog extends JDialog {

    private JTextField usnField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField departmentField;
    private JTextField yearField;
    private JTextField cgpaField;
    private JTextField resumeField;
    private final Runnable onSaveSuccess;

    private JComboBox<PlacementStatus> statusBox;

    private JButton saveButton;
    private JButton cancelButton;
    private Student student;


    private final StudentService studentService = new StudentServiceImpl();

    public StudentDialog(
            Frame owner,
            Student student,
            Runnable onSaveSuccess
    ) {

        super(
                owner,
                student == null
                        ? "Add Student"
                        : "Edit Student",
                true
        );

        this.student = student;
        this.onSaveSuccess = onSaveSuccess;

        initializeFrame();
        initializeComponents();

        if (student != null) {
            populateFields();
        }

        registerEvents();

        setVisible(true);
    }
    private void initializeFrame() {

        setSize(550, 600);

        setLocationRelativeTo(getOwner());

        setLayout(new BorderLayout(10, 10));
    }

    private void initializeComponents() {

        FormPanel form = new FormPanel();

        usnField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();
        phoneField = new JTextField();
        departmentField = new JTextField();
        yearField = new JTextField();
        cgpaField = new JTextField();
        resumeField = new JTextField();

        statusBox = new JComboBox<>(PlacementStatus.values());

        form.addField("USN", usnField, 0);
        form.addField("Name", nameField, 1);
        form.addField("Email", emailField, 2);
        form.addField("Phone", phoneField, 3);
        form.addField("Department", departmentField, 4);
        form.addField("Year", yearField, 5);
        form.addField("CGPA", cgpaField, 6);
        form.addField("Resume", resumeField, 7);
        form.addField("Status", statusBox, 8);

        add(form, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        saveButton = new JButton("Save");

        cancelButton = new JButton("Cancel");

        buttons.add(saveButton);
        buttons.add(cancelButton);

        add(buttons, BorderLayout.SOUTH);
    }

    private void registerEvents() {

        cancelButton.addActionListener(e -> dispose());

        saveButton.addActionListener(e -> {

            try {

                Student newStudent = buildStudent();

                boolean success;

                if (student != null) {

                    newStudent.setId(student.getId());

                    success = studentService.updateStudent(newStudent);

                } else {

                    success = studentService.registerStudent(newStudent);

                }

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            student == null
                                    ? "Student added successfully."
                                    : "Student updated successfully."
                    );

                    if (onSaveSuccess != null) {
                        onSaveSuccess.run();
                    }

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to save student."
                    );

                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Year and CGPA must be valid numbers."
                );

            }

        });



    }

    private Student buildStudent() {

        Student student = new Student();

        student.setUsn(usnField.getText().trim());
        student.setName(nameField.getText().trim());
        student.setEmail(emailField.getText().trim());
        student.setPhone(phoneField.getText().trim());
        student.setDepartment(departmentField.getText().trim());

        student.setYear(Integer.parseInt(yearField.getText().trim()));
        student.setCgpa(Double.parseDouble(cgpaField.getText().trim()));

        student.setResumePath(resumeField.getText().trim());

        student.setPlacementStatus(
                (PlacementStatus) statusBox.getSelectedItem()
        );

        return student;
    }

    private void populateFields() {

        usnField.setText(student.getUsn());
        nameField.setText(student.getName());
        emailField.setText(student.getEmail());
        phoneField.setText(student.getPhone());
        departmentField.setText(student.getDepartment());

        yearField.setText(String.valueOf(student.getYear()));
        cgpaField.setText(String.valueOf(student.getCgpa()));

        resumeField.setText(student.getResumePath());

        statusBox.setSelectedItem(student.getPlacementStatus());
    }



}