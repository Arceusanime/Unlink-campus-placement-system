package com.unlink.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.unlink.model.User;
import com.unlink.model.UserRole;
import com.unlink.service.UserService;
import com.unlink.service.impl.UserServiceImpl;

public class DatabaseInitializer {

    private static final UserService userService = new UserServiceImpl();

    public static void initialize() {

        try (
                Connection connection = DBConnection.getConnection();
                Statement statement = connection.createStatement()
        ) {

            for (String sql : TableScripts.TABLES) {
                statement.execute(sql);
            }

            createDefaultAdmin();

            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    private static void createDefaultAdmin() {

        User admin = userService.getUserByUsername("admin");

        if (admin != null) {
            return;
        }

        User defaultAdmin = new User(
                "admin",
                "admin123",
                UserRole.PLACEMENT_OFFICER,
                0
        );

        userService.registerUser(defaultAdmin);

        System.out.println("Default admin created.");

    }

}