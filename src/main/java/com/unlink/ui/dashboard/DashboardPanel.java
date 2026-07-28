package com.unlink.ui.dashboard;

import com.unlink.service.ApplicationService;
import com.unlink.service.CompanyService;
import com.unlink.service.JobService;
import com.unlink.service.StudentService;
import com.unlink.service.impl.ApplicationServiceImpl;
import com.unlink.service.impl.CompanyServiceImpl;
import com.unlink.service.impl.JobServiceImpl;
import com.unlink.service.impl.StudentServiceImpl;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private final StudentService studentService = new StudentServiceImpl();
    private final CompanyService companyService = new CompanyServiceImpl();
    private final JobService jobService = new JobServiceImpl();
    private final ApplicationService applicationService = new ApplicationServiceImpl();

    public DashboardPanel() {

        setLayout(new BorderLayout(20,20));

        JLabel title = new JLabel("Campus Placement Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        add(title, BorderLayout.NORTH);

        JPanel cards = new JPanel(new GridLayout(2,3,20,20));

        int students = studentService.getStudentCount();
        int companies = companyService.getCompanyCount();
        int jobs = jobService.getJobCount();
        int applications = applicationService.getApplicationCount();
        int selected = applicationService.getSelectedCount();

        double placement =
                applications == 0
                        ? 0
                        : (selected * 100.0) / applications;

        cards.add(createCard("Students", String.valueOf(students), new Color(52,152,219)));
        cards.add(createCard("Companies", String.valueOf(companies), new Color(46,204,113)));
        cards.add(createCard("Jobs", String.valueOf(jobs), new Color(241,196,15)));
        cards.add(createCard("Applications", String.valueOf(applications), new Color(155,89,182)));
        cards.add(createCard("Selected", String.valueOf(selected), new Color(231,76,60)));
        cards.add(createCard("Placement %", String.format("%.2f%%", placement), new Color(52,73,94)));

        add(cards, BorderLayout.CENTER);
    }

    private JPanel createCard(String title, String value, Color color){

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(color);

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel titleLabel = new JLabel(title);

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD,18));

        JLabel valueLabel = new JLabel(value,SwingConstants.CENTER);

        valueLabel.setForeground(Color.WHITE);

        valueLabel.setFont(new Font("Segoe UI",Font.BOLD,34));

        panel.add(titleLabel,BorderLayout.NORTH);

        panel.add(valueLabel,BorderLayout.CENTER);

        return panel;
    }

}