# 🎓 Campus Placement Management System

A desktop-based **Campus Placement Management System** developed using **Java Swing, JDBC, and MySQL** following a layered architecture with the **DAO Design Pattern**. The application streamlines the campus recruitment process by managing students, companies, job postings, applications, placements, and administrative operations.

---

## 📌 Project Overview

The Campus Placement Management System is designed to simplify and automate placement activities within an educational institution.

The system enables administrators to:

- Manage student records
- Register companies
- Create and manage job postings
- Track student applications
- Record placement results
- Search and filter data efficiently
- Maintain a centralized placement database

---

## 🚀 Features

### 👨‍🎓 Student Management
- Add Student
- Update Student
- Delete Student
- Search Students
- View All Students

### 🏢 Company Management
- Register Company
- Update Company Details
- Delete Company
- Search Companies
- View Company Information

### 💼 Job Management
- Post New Jobs
- Edit Job Details
- Delete Jobs
- View Available Jobs
- Search Jobs

### 📄 Application Management
- Student Job Applications
- Track Application Status
- View Applied Students

### 🎯 Placement Management
- Record Final Placements
- View Placement History
- Placement Reports

### 🔍 Search System
- Search Students
- Search Companies
- Search Jobs
- Fast keyword-based filtering

### 📊 Dashboard
- Overview of Students
- Companies
- Jobs
- Applications
- Placements

---

# 🏗️ Project Architecture

The project follows a **3-Layer Architecture**.

```
Presentation Layer (Swing UI)
        │
        ▼
Business Layer (Service)
        │
        ▼
Data Access Layer (DAO)
        │
        ▼
MySQL Database
```

---

# 📂 Project Structure

```
unlink/
│
├── src/
│   └── main/
│       ├── java/
│       │
│       ├── com.unlink
│       │   ├── config
│       │   ├── dao
│       │   ├── dao.impl
│       │   ├── model
│       │   ├── service
│       │   ├── service.impl
│       │   ├── ui
│       │   ├── ui.components
│       │   ├── ui.student
│       │   ├── ui.company
│       │   ├── ui.job
│       │   ├── ui.application
│       │   ├── ui.placement
│       │   ├── util
│       │   └── Main.java
│       │
│       └── resources/
│
├── pom.xml
└── README.md
```

---

# 🛠️ Technologies Used

| Technology | Purpose |
|------------|----------|
| Java 25 | Programming Language |
| Java Swing | Desktop GUI |
| JDBC | Database Connectivity |
| MySQL | Database |
| Maven | Dependency Management |
| IntelliJ IDEA | IDE |
| Git | Version Control |
| GitHub | Repository Hosting |

---

# 💡 Concepts Implemented

This project demonstrates the implementation of several Object-Oriented Programming and Software Engineering concepts.

- Object-Oriented Programming (OOP)
- Inheritance
- Polymorphism
- Abstraction
- Encapsulation
- Interfaces
- Exception Handling
- Collections Framework
- JDBC Connectivity
- Prepared Statements
- DAO Design Pattern
- Service Layer Pattern
- Layered Architecture
- MVC-inspired Design
- Reusable Swing Components

---

# 🗄️ Database

Database: **campus_placement**

Main Tables

- students
- companies
- jobs
- applications
- placements
- users (optional for authentication)

---

# ⚙️ Installation

### Clone the repository

```bash
git clone https://github.com/yourusername/campus-placement-management-system.git
```

---

### Open in IntelliJ

Open the project as a Maven project.

---

### Configure MySQL

Create the database.

```sql
CREATE DATABASE campus_placement;
```

Import the SQL schema.

Update the database configuration inside:

```
DBConnection.java
```

Example:

```java
private static final String URL = "jdbc:mysql://localhost:3306/campus_placement";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

### Run

Run

```
Main.java
```

---

# 📸 Screenshots

You can add screenshots here after completing the UI.

```
Dashboard

Student Management

Company Management

Job Management

Application Management

Placement Management
```

---

# 📈 Future Enhancements

- User Authentication
- Role-Based Access Control
- Resume Upload
- PDF Report Generation
- Email Notifications
- Company Login Portal
- Student Login Portal
- Placement Statistics
- Export to Excel
- Cloud Database Support

---

# 👨‍💻 Team

Developed as an MCA Academic Project.

**Project Name:** Unlink - Campus Placement Management System

---

# 📚 Learning Outcomes

Through this project we learned:

- Java Swing Development
- JDBC Programming
- SQL Queries
- DAO Pattern
- Maven Project Management
- Layered Software Architecture
- Database Design
- CRUD Operations
- GUI Design
- Object-Oriented Programming Principles

---

# 📄 License

This project is developed for educational purposes.

---

## ⭐ Support

If you found this project helpful, consider giving it a **⭐ Star** on GitHub.
