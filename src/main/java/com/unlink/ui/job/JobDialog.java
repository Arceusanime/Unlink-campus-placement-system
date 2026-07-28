package com.unlink.ui.job;

import com.unlink.model.Company;
import com.unlink.model.Job;
import com.unlink.model.JobStatus;
import com.unlink.service.CompanyService;
import com.unlink.service.JobService;
import com.unlink.service.impl.CompanyServiceImpl;
import com.unlink.service.impl.JobServiceImpl;
import com.unlink.ui.common.FormPanel;

import javax.swing.*;
import java.awt.*;

public class JobDialog extends JDialog {

    private JTextField titleField;
    private JComboBox<Company> companyComboBox;
    private JTextField packageField;
    private JTextField locationField;
    private JTextField cgpaField;
    private JTextArea descriptionArea;
    private JComboBox<JobStatus> statusComboBox;

    private JButton saveButton;
    private JButton cancelButton;

    private Job job;

    private final JobService jobService = new JobServiceImpl();
    private final CompanyService companyService = new CompanyServiceImpl();

    private final Runnable onSaveSuccess;

    public JobDialog(
            Frame owner,
            Job job,
            Runnable onSaveSuccess
    ) {

        super(owner, true);

        this.job = job;
        this.onSaveSuccess = onSaveSuccess;

        initializeFrame();

        initializeComponents();

        if (job != null) {
            populateFields();
        }

        registerEvents();

        setVisible(true);
    }

    private void initializeFrame() {

        setTitle(job == null ? "Add Job" : "Edit Job");

        setSize(600, 520);

        setLocationRelativeTo(getOwner());

        setLayout(new BorderLayout());
    }

    private void initializeComponents() {

        FormPanel form = new FormPanel();

        titleField = new JTextField();

        companyComboBox = new JComboBox<>();

        packageField = new JTextField();

        locationField = new JTextField();

        cgpaField = new JTextField();

        descriptionArea = new JTextArea(4, 20);

        statusComboBox = new JComboBox<>(JobStatus.values());

        saveButton = new JButton("Save");

        cancelButton = new JButton("Cancel");

        form.addField("Title", titleField, 0);

        form.addField("Company", companyComboBox, 1);

        form.addField("Package", packageField, 2);

        form.addField("Location", locationField, 3);

        form.addField("Minimum CGPA", cgpaField, 4);

        form.addField("Description", new JScrollPane(descriptionArea), 5);

        form.addField("Status", statusComboBox, 6);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(saveButton);

        buttonPanel.add(cancelButton);

        add(form, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.SOUTH);

        loadCompanies();
    }

    private void loadCompanies() {

        companyComboBox.removeAllItems();

        for (Company company : companyService.getAllCompanies()) {
            companyComboBox.addItem(company);
        }
    }

    private Job buildJob() {

        if (job == null) {
            job = new Job();
        }

        job.setTitle(titleField.getText().trim());

        job.setCompany((Company) companyComboBox.getSelectedItem());

        job.setPackageAmount(
                Double.parseDouble(packageField.getText().trim())
        );

        job.setLocation(locationField.getText().trim());

        job.setMinimumCgpa(
                Double.parseDouble(cgpaField.getText().trim())
        );

        job.setDescription(descriptionArea.getText().trim());

        job.setStatus(
                (JobStatus) statusComboBox.getSelectedItem()
        );

        return job;
    }

    private void populateFields() {

        titleField.setText(job.getTitle());

        companyComboBox.setSelectedItem(job.getCompany());

        packageField.setText(String.valueOf(job.getPackageAmount()));

        locationField.setText(job.getLocation());

        cgpaField.setText(String.valueOf(job.getMinimumCgpa()));

        descriptionArea.setText(job.getDescription());

        statusComboBox.setSelectedItem(job.getStatus());
    }

    private void registerEvents() {

        saveButton.addActionListener(e -> {

            try {

                Job job = buildJob();

                boolean success;

                if (job.getId() == 0) {
                    success = jobService.registerJob(job);
                } else {
                    success = jobService.updateJob(job);
                }

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Job saved successfully."
                    );

                    onSaveSuccess.run();

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to save job."
                    );
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Package and CGPA must be valid numbers."
                );

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
