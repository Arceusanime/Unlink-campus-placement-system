package com.unlink.ui.application;

import com.unlink.model.Application;
import com.unlink.service.ApplicationService;
import com.unlink.service.impl.ApplicationServiceImpl;
import com.unlink.ui.components.ActionToolbar;
import com.unlink.ui.components.DataTable;
import com.unlink.ui.components.SearchPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ApplicationPanel extends JPanel {

    private final ActionToolbar toolbar;
    private final SearchPanel searchPanel;
    private final DataTable table;

    private final ApplicationService applicationService =
            new ApplicationServiceImpl();

    private static final String[] COLUMNS = {
            "ID",
            "Student",
            "Job",
            "Application Date",
            "Status"
    };

    public ApplicationPanel() {

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

        loadApplications();

    }

    private void loadApplications(List<Application> applications) {

        DefaultTableModel model = table.getModel();

        model.setRowCount(0);

        for (Application application : applications) {

            model.addRow(new Object[]{

                    application.getId(),

                    application.getStudent().getName(),

                    application.getJob().getTitle(),

                    application.getApplicationDate(),

                    application.getStatus()

            });

        }

    }

    private void loadApplications() {

        loadApplications(
                applicationService.getAllApplications()
        );

    }

    private int getSelectedApplicationId() {

        int row = table.getTable().getSelectedRow();

        if (row == -1) {
            return -1;
        }

        return (int) table.getModel().getValueAt(row, 0);

    }

    private Application getSelectedApplication() {

        int id = getSelectedApplicationId();

        if (id == -1) {
            return null;
        }

        return applicationService.getApplicationById(id);

    }

    private void editSelectedApplication() {

        Application application = getSelectedApplication();

        if (application == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an application."
            );

            return;
        }

        new ApplicationDialog(

                (Frame) SwingUtilities.getWindowAncestor(this),

                application,

                this::loadApplications

        );

    }
    private void registerEvents() {

        toolbar.getAddButton().addActionListener(e ->

                new ApplicationDialog(
                        (Frame) SwingUtilities.getWindowAncestor(this),
                        null,
                        this::loadApplications
                )

        );

        toolbar.getEditButton().addActionListener(
                e -> editSelectedApplication()
        );

        table.getTable().addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                if (e.getClickCount() == 2) {
                    editSelectedApplication();
                }

            }

        });

        toolbar.getDeleteButton().addActionListener(e -> {

            Application application = getSelectedApplication();

            if (application == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select an application."
                );

                return;
            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Delete this application?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {

                boolean success =
                        applicationService.deleteApplication(application.getId());

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Application deleted successfully."
                    );

                    loadApplications();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to delete application."
                    );

                }

            }

        });

        searchPanel.getSearchButton().addActionListener(e -> {

            String keyword = searchPanel.getSearchField()
                    .getText()
                    .trim();

            if (keyword.isEmpty()) {

                loadApplications();

            } else {

                loadApplications(
                        applicationService.searchApplications(keyword)
                );

            }

        });

        searchPanel.getSearchField().addActionListener(e ->
                searchPanel.getSearchButton().doClick()
        );

        toolbar.getRefreshButton().addActionListener(e -> {

            searchPanel.getSearchField().setText("");

            loadApplications();

        });

    }
}