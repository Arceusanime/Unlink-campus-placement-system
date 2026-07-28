package com.unlink.ui.job;

import com.unlink.model.Job;
import com.unlink.service.JobService;
import com.unlink.service.impl.JobServiceImpl;
import com.unlink.ui.components.ActionToolbar;
import com.unlink.ui.components.DataTable;
import com.unlink.ui.components.SearchPanel;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class JobPanel extends JPanel {

    private final ActionToolbar toolbar;
    private final SearchPanel searchPanel;
    private final DataTable table;

    private final JobService jobService = new JobServiceImpl();

    private static final String[] COLUMNS = {
            "ID",
            "Title",
            "Company",
            "Package",
            "Location",
            "Minimum CGPA",
            "Status"
    };

    public JobPanel() {

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

        loadJobs();
    }

    private void registerEvents() {

        toolbar.getAddButton().addActionListener(e ->

                new JobDialog(
                        (Frame) SwingUtilities.getWindowAncestor(this),
                        null,
                        this::loadJobs
                )

        );

        toolbar.getEditButton().addActionListener(
                e -> editSelectedJob()
        );

        table.getTable().addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {

                if (e.getClickCount() == 2) {
                    editSelectedJob();
                }

            }

        });

        toolbar.getDeleteButton().addActionListener(e -> {

            Job job = getSelectedJob();

            if (job == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a job."
                );

                return;
            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Delete " + job.getTitle() + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {

                boolean success =
                        jobService.deleteJob(job.getId());

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Job deleted successfully."
                    );

                    loadJobs();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Unable to delete job."
                    );

                }

            }

        });

        searchPanel.getSearchButton().addActionListener(e -> {

            String keyword = searchPanel.getSearchField()
                    .getText()
                    .trim();

            if (keyword.isEmpty()) {

                loadJobs();

            } else {

                loadJobs(
                        jobService.searchJobs(keyword)
                );

            }

        });

        searchPanel.getSearchField().addActionListener(e ->
                searchPanel.getSearchButton().doClick()
        );

        toolbar.getRefreshButton().addActionListener(e -> {

            searchPanel.getSearchField().setText("");

            loadJobs();

        });

    }

    private void loadJobs(List<Job> jobs) {

        DefaultTableModel model = table.getModel();

        model.setRowCount(0);

        for (Job job : jobs) {

            model.addRow(new Object[]{
                    job.getId(),
                    job.getTitle(),
                    job.getCompany().getName(),
                    job.getPackageAmount(),
                    job.getLocation(),
                    job.getMinimumCgpa(),
                    job.getStatus()
            });

        }

    }

    private void loadJobs() {

        loadJobs(jobService.getAllJobs());

    }

    private int getSelectedJobId() {

        int selectedRow = table.getTable().getSelectedRow();

        if (selectedRow == -1) {
            return -1;
        }

        return (int) table.getModel().getValueAt(selectedRow, 0);
    }

    private Job getSelectedJob() {

        int id = getSelectedJobId();

        if (id == -1) {
            return null;
        }

        return jobService.getJobById(id);
    }

    private void editSelectedJob() {

        Job job = getSelectedJob();

        if (job == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a job."
            );

            return;
        }

        new JobDialog(
                (Frame) SwingUtilities.getWindowAncestor(this),
                job,
                this::loadJobs
        );

    }

}


