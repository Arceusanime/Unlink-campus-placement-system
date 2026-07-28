package com.unlink.ui.dashboard;

import com.unlink.config.Session;
import com.unlink.ui.login.LoginFrame;
import com.unlink.ui.student.StudentPanel;
import com.unlink.ui.company.CompanyPanel;
import com.unlink.ui.application.ApplicationPanel;
import com.unlink.ui.job.JobPanel;
import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private JPanel sideBar;
    private JPanel contentPanel;

    private JButton dashboardButton;
    private JButton studentsButton;
    private JButton recruitersButton;
    private JButton companiesButton;
    private JButton jobsButton;
    private JButton applicationsButton;
    private JButton reportsButton;
    private JButton logoutButton;

    public DashboardFrame() {

        initializeFrame();

        initializeComponents();

        registerEvents();

        setVisible(true);
    }

    private void initializeFrame() {

        setTitle("Campus Placement Management System");

        setSize(1200, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
    }

    private void initializeComponents() {

        sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(220, 0));
        sideBar.setLayout(new GridLayout(8, 1, 5, 5));

        dashboardButton = new JButton("Dashboard");
        studentsButton = new JButton("Students");
        recruitersButton = new JButton("Recruiters");
        companiesButton = new JButton("Companies");
        jobsButton = new JButton("Jobs");
        applicationsButton = new JButton("Applications");
        reportsButton = new JButton("Reports");
        logoutButton = new JButton("Logout");

        sideBar.add(dashboardButton);
        sideBar.add(studentsButton);
        sideBar.add(recruitersButton);
        sideBar.add(companiesButton);
        sideBar.add(jobsButton);
        sideBar.add(applicationsButton);
        sideBar.add(reportsButton);
        sideBar.add(logoutButton);

        add(sideBar, BorderLayout.WEST);

        contentPanel = new JPanel(new BorderLayout());

        contentPanel.add(new DashboardPanel(), BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void registerEvents() {

        dashboardButton.addActionListener(e -> showPanel(new DashboardPanel()));

        logoutButton.addActionListener(e -> logout());

        studentsButton.addActionListener(
                e -> showPanel(new StudentPanel())
        );

        companiesButton.addActionListener(
                e -> showPanel(new CompanyPanel())
        );
        jobsButton.addActionListener(
                e -> showPanel(new JobPanel())
        );
        applicationsButton.addActionListener(
                e -> showPanel(new ApplicationPanel())
        );
    }

    private void showPanel(JPanel panel) {

        contentPanel.removeAll();

        contentPanel.add(panel, BorderLayout.CENTER);

        contentPanel.revalidate();

        contentPanel.repaint();
    }

    private void logout() {

        Session.clear();

        dispose();

        new LoginFrame();
    }
}