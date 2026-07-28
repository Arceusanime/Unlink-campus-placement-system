package com.unlink.ui.login;

import com.unlink.model.User;
import com.unlink.model.UserRole;
import com.unlink.service.UserService;
import com.unlink.service.impl.UserServiceImpl;
import com.unlink.config.Session;
import javax.swing.*;
import java.awt.*;
import com.unlink.ui.dashboard.DashboardFrame;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;


public class LoginFrame extends JFrame {

    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel subtitleLabel;
    private JLabel passwordLable;
    private JLabel logoLabel;
    private JPasswordField passwordField;
    private JLabel roleLabel;
    private JRadioButton studentRB;
    private JRadioButton recruiterRB;
    private JRadioButton officerRB;
    private ButtonGroup roleGroup;
    private JButton loginButton;
    private final UserService userService = new UserServiceImpl();


    private ImageIcon loadScaledLogo(int width, int height) {

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/logo.png"));

        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = resized.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(icon.getImage(), 0, 0, width, height, null);

        g2.dispose();

        return new ImageIcon(resized);
    }

    public LoginFrame() {

        initializeFrame();

        initializeComponents();

        setIconImage(loadScaledLogo(256, 256).getImage());

        registerEvents();

        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Campus Placement Management System");

        setSize(700, 500);

        setLayout(null);

        setLocationRelativeTo(null);

        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initializeComponents() {

        logoLabel = new JLabel(loadScaledLogo(90, 90));
        logoLabel.setBounds(305, 10, 90, 90);
        add(logoLabel);

        titleLabel = new JLabel("Campus Placement Management System");
        titleLabel.setBounds(140, 95, 450, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        subtitleLabel = new JLabel("Login");
        subtitleLabel.setBounds(305,130,100,30);
        subtitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(subtitleLabel);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(150,180,100,25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(250,185,250,35);
        add(usernameField);

        passwordLable = new JLabel("Password");
        passwordLable.setBounds(150,230,100,25);
        add(passwordLable);

        passwordField = new JPasswordField();
        passwordField.setBounds(250,235,250,35);
        add(passwordField);

        roleLabel = new JLabel("Role");
        roleLabel.setBounds(150,290,100,25);
        add(roleLabel);

        studentRB = new JRadioButton("Student");
        studentRB.setBounds(250,285,90,30);
        add(studentRB);

        recruiterRB = new JRadioButton("Recruiter");
        recruiterRB.setBounds(340,285,110,30);
        add(recruiterRB);

        officerRB = new JRadioButton("Placement Officer");
        officerRB.setBounds(450,285,150,30);
        officerRB.setSelected(true);
        add(officerRB);

        roleGroup = new ButtonGroup();
        roleGroup.add(studentRB);
        roleGroup.add(recruiterRB);
        roleGroup.add(officerRB);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(280,350,140,40);
        add(loginButton);
    }
    private void registerEvents(){
        loginButton.addActionListener(e -> {
            login();
        });
    }

    private void login() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        User user = userService.login(username, password);

        if (user == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        UserRole selectedRole;

        if (studentRB.isSelected()) {
            selectedRole = UserRole.STUDENT;
        } else if (recruiterRB.isSelected()) {
            selectedRole = UserRole.RECRUITER;
        } else {
            selectedRole = UserRole.PLACEMENT_OFFICER;
        }

        if (user.getRole() != selectedRole) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selected role does not match the account.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        Session.setCurrentUser(user);

        dispose();

        new DashboardFrame();

        // Dashboard will open here next
    }
}