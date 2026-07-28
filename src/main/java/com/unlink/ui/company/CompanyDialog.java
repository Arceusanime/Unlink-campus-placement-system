package com.unlink.ui.company;

import com.unlink.model.Company;
import com.unlink.service.CompanyService;
import com.unlink.service.impl.CompanyServiceImpl;
import com.unlink.ui.common.FormPanel;

import javax.swing.*;
import java.awt.*;

public class CompanyDialog extends JDialog {

    private JTextField nameField;
    private JTextField industryField;
    private JTextField locationField;
    private JTextField websiteField;
    private JTextField hrNameField;
    private JTextField hrEmailField;
    private JTextField hrPhoneField;

    private JButton saveButton;
    private JButton cancelButton;

    private Company company;

    private final Runnable onSaveSuccess;

    private final CompanyService companyService = new CompanyServiceImpl();

    public CompanyDialog(
            Frame owner,
            Company company,
            Runnable onSaveSuccess
    ) {

        super(
                owner,
                company == null
                        ? "Add Company"
                        : "Edit Company",
                true
        );

        this.company = company;
        this.onSaveSuccess = onSaveSuccess;

        initializeFrame();
        initializeComponents();

        if (company != null) {
            populateFields();
        }

        registerEvents();

        setVisible(true);
    }

    private void initializeFrame() {

        setSize(550, 500);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout(10, 10));

    }

    private void initializeComponents() {

        FormPanel form = new FormPanel();

        nameField = new JTextField();
        industryField = new JTextField();
        locationField = new JTextField();
        websiteField = new JTextField();
        hrNameField = new JTextField();
        hrEmailField = new JTextField();
        hrPhoneField = new JTextField();

        form.addField("Company Name", nameField, 0);
        form.addField("Industry", industryField, 1);
        form.addField("Location", locationField, 2);
        form.addField("Website", websiteField, 3);
        form.addField("HR Name", hrNameField, 4);
        form.addField("HR Email", hrEmailField, 5);
        form.addField("HR Phone", hrPhoneField, 6);

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

                Company newCompany = buildCompany();

                boolean success;

                if (company != null) {

                    newCompany.setId(company.getId());

                    success = companyService.updateCompany(newCompany);

                } else {

                    success = companyService.registerCompany(newCompany);

                }

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            company == null
                                    ? "Company added successfully."
                                    : "Company updated successfully."
                    );

                    if (onSaveSuccess != null) {
                        onSaveSuccess.run();
                    }

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to save company."
                    );

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                );

            }

        });

    }

    private void populateFields() {

        nameField.setText(company.getName());
        industryField.setText(company.getIndustry());
        locationField.setText(company.getLocation());
        websiteField.setText(company.getWebsite());
        hrNameField.setText(company.getHrName());
        hrEmailField.setText(company.getHrEmail());
        hrPhoneField.setText(company.getHrPhone());

    }

    private Company buildCompany() {

        Company company = new Company();

        company.setName(nameField.getText().trim());
        company.setIndustry(industryField.getText().trim());
        company.setLocation(locationField.getText().trim());
        company.setWebsite(websiteField.getText().trim());
        company.setHrName(hrNameField.getText().trim());
        company.setHrEmail(hrEmailField.getText().trim());
        company.setHrPhone(hrPhoneField.getText().trim());

        return company;
    }

}