package com.unlink.ui.application;

import com.unlink.model.Application;
import com.unlink.model.ApplicationStatus;
import com.unlink.model.Job;
import com.unlink.model.Student;
import com.unlink.service.ApplicationService;
import com.unlink.service.JobService;
import com.unlink.service.StudentService;
import com.unlink.service.impl.ApplicationServiceImpl;
import com.unlink.service.impl.JobServiceImpl;
import com.unlink.service.impl.StudentServiceImpl;
import com.unlink.ui.common.FormPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
public class ApplicationDialog extends JDialog {

    private JComboBox<Student> studentComboBox;
    private JComboBox<Job> jobComboBox;
    private JTextField applicationDateField;
    private JComboBox<ApplicationStatus> statusComboBox;

    private JButton saveButton;
    private JButton cancelButton;

    private Application application;

    private final ApplicationService applicationService = new ApplicationServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final JobService jobService = new JobServiceImpl();

    private final Runnable onSaveSuccess;

    public ApplicationDialog(
            Frame owner,
            Application application,
            Runnable onSaveSuccess
    ) {

        super(owner, true);

        this.application = application;
        this.onSaveSuccess = onSaveSuccess;

        initializeFrame();

        initializeComponents();

        if (application != null) {
            populateFields();
        }

        registerEvents();

        setVisible(true);
    }

    private void initializeFrame() {

        setTitle(application == null
                ? "Add Application"
                : "Edit Application");

        setSize(500, 350);

        setLocationRelativeTo(getOwner());

        setLayout(new BorderLayout());
    }

    private void initializeComponents() {

        FormPanel form = new FormPanel();

        studentComboBox = new JComboBox<>();

        jobComboBox = new JComboBox<>();

        applicationDateField = new JTextField(
                LocalDate.now().toString()
        );

        statusComboBox =
                new JComboBox<>(ApplicationStatus.values());

        saveButton = new JButton("Save");

        cancelButton = new JButton("Cancel");

        loadStudents();

        loadJobs();

        form.addField("Student", studentComboBox, 0);
        form.addField("Job", jobComboBox, 1);
        form.addField("Application Date", applicationDateField, 2);
        form.addField("Status", statusComboBox, 3);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(form, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadStudents() {

        studentComboBox.removeAllItems();

        for (Student student : studentService.getAllStudents()) {
            studentComboBox.addItem(student);
        }
    }

    private void loadJobs() {

        jobComboBox.removeAllItems();

        for (Job job : jobService.getAllJobs()) {
            jobComboBox.addItem(job);
        }
    }

    private Application buildApplication() {

        if (application == null) {
            application = new Application();
        }

        application.setStudent(
                (Student) studentComboBox.getSelectedItem()
        );

        application.setJob(
                (Job) jobComboBox.getSelectedItem()
        );

        application.setApplicationDate(
                LocalDate.parse(applicationDateField.getText().trim())
        );

        application.setStatus(
                (ApplicationStatus) statusComboBox.getSelectedItem()
        );

        return application;
    }

    private void populateFields() {

        studentComboBox.setSelectedItem(application.getStudent());

        jobComboBox.setSelectedItem(application.getJob());

        applicationDateField.setText(
                application.getApplicationDate().toString()
        );

        statusComboBox.setSelectedItem(
                application.getStatus()
        );

    }

    private void registerEvents() {

        saveButton.addActionListener(e -> {

            try {

                Application application = buildApplication();

                boolean success;

                if (application.getId() == 0) {

                    success = applicationService.registerApplication(application);

                } else {

                    success = applicationService.updateApplication(application);

                }

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Application saved successfully."
                    );

                    onSaveSuccess.run();

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to save application."
                    );

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );

            }

        });

        cancelButton.addActionListener(e -> dispose());

    }
}