package com.unlink.ui.company;

import com.unlink.model.Company;
import com.unlink.service.CompanyService;
import com.unlink.service.impl.CompanyServiceImpl;
import com.unlink.ui.components.ActionToolbar;
import com.unlink.ui.components.DataTable;
import com.unlink.ui.components.SearchPanel;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class CompanyPanel extends JPanel {

    private final ActionToolbar toolbar;
    private final SearchPanel searchPanel;
    private final DataTable table;

    private final CompanyService companyService = new CompanyServiceImpl();

    private static final String[] COLUMNS = {
            "ID",
            "Company",
            "Industry",
            "Location",
            "HR Name",
            "HR Email"
    };

    public CompanyPanel() {

        setLayout(new BorderLayout(10, 10));

        toolbar = new ActionToolbar();

        searchPanel = new SearchPanel();

        JPanel northPanel = new JPanel(new BorderLayout());

        northPanel.add(toolbar, BorderLayout.WEST);
        northPanel.add(searchPanel, BorderLayout.EAST);

        add(northPanel, BorderLayout.NORTH);

        table = new DataTable(COLUMNS);

        table.getTable().getColumnModel().getColumn(0).setMinWidth(0);
        table.getTable().getColumnModel().getColumn(0).setMaxWidth(0);
        table.getTable().getColumnModel().getColumn(0).setWidth(0);

        add(table, BorderLayout.CENTER);

        registerEvents();

        loadCompanies();
    }

    private void registerEvents() {

        toolbar.getAddButton().addActionListener(e ->

                new CompanyDialog(
                        (Frame) SwingUtilities.getWindowAncestor(this),
                        null,
                        this::loadCompanies
                )

        );

        toolbar.getEditButton().addActionListener(
                e -> editSelectedCompany()
        );

        table.getTable().addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                if (e.getClickCount() == 2) {
                    editSelectedCompany();
                }

            }

        });

        toolbar.getDeleteButton().addActionListener(e -> {

            Company company = getSelectedCompany();

            if (company == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a company."
                );

                return;
            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Delete " + company.getName() + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {

                boolean success =
                        companyService.deleteCompany(company.getId());

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Company deleted successfully."
                    );

                    loadCompanies();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to delete company."
                    );

                }

            }

        });

        searchPanel.getSearchButton().addActionListener(e -> {

            String keyword = searchPanel.getSearchField()
                    .getText()
                    .trim();

            if (keyword.isEmpty()) {

                loadCompanies();

            } else {

                loadCompanies(
                        companyService.searchCompanies(keyword)
                );

            }

        });

        searchPanel.getSearchField().addActionListener(e ->
                searchPanel.getSearchButton().doClick()
        );

        toolbar.getRefreshButton().addActionListener(e -> {

            searchPanel.getSearchField().setText("");

            loadCompanies();

        });

    }

    private void loadCompanies(List<Company> companies) {

        DefaultTableModel model = table.getModel();

        model.setRowCount(0);

        for (Company company : companies) {

            model.addRow(new Object[]{
                    company.getId(),
                    company.getName(),
                    company.getIndustry(),
                    company.getLocation(),
                    company.getHrName(),
                    company.getHrEmail()
            });

        }

    }

    private void loadCompanies() {

        loadCompanies(companyService.getAllCompanies());

    }
    private int getSelectedCompanyId() {

        int selectedRow = table.getTable().getSelectedRow();

        if (selectedRow == -1) {
            return -1;
        }

        return (int) table.getModel().getValueAt(selectedRow, 0);
    }

    private Company getSelectedCompany() {

        int id = getSelectedCompanyId();

        if (id == -1) {
            return null;
        }

        return companyService.getCompanyById(id);
    }

    private void editSelectedCompany() {

        Company company = getSelectedCompany();

        if (company == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a company."
            );

            return;
        }

        new CompanyDialog(
                (Frame) SwingUtilities.getWindowAncestor(this),
                company,
                this::loadCompanies
        );

    }

}