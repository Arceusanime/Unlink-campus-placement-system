package com.unlink.config;

public class TableScripts {

    public static final String[] TABLES = {

            """
            CREATE TABLE IF NOT EXISTS user (
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(50) NOT NULL UNIQUE,
                password VARCHAR(255) NOT NULL,
                role VARCHAR(30) NOT NULL,
                reference_id INT NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    ON UPDATE CURRENT_TIMESTAMP
            )
            """,

            """
            CREATE TABLE IF NOT EXISTS student (
                id INT AUTO_INCREMENT PRIMARY KEY,
                usn VARCHAR(10) NOT NULL UNIQUE,
                name VARCHAR(50) NOT NULL,
                email VARCHAR(30) NOT NULL UNIQUE,
                phone VARCHAR(15),
                department VARCHAR(10),
                year INT,
                cgpa DOUBLE,
                resume_path VARCHAR(255),
                placement_status VARCHAR(50)
            )
            """

    };

}