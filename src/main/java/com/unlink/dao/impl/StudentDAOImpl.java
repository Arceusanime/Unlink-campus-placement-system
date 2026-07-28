package com.unlink.dao.impl;

import com.unlink.config.DBConnection;
import com.unlink.dao.StudentDAO;
import com.unlink.model.PlacementStatus;
import com.unlink.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO{

    @Override
    public boolean addStudent(Student student) {
        String sql = """
                INSERT INTO student(usn, name, email, phone, department, year, cgpa,
                resume_path, placement_status)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try(
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, student.getUsn());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getDepartment());
            statement.setInt(6, student.getYear());
            statement.setDouble(7, student.getCgpa());
            statement.setString(8, student.getResumePath());
            statement.setString(9, student.getPlacementStatus().name());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Student getStudentById(int id) {

        String sql = "SELECT * FROM student WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return mapStudent(resultSet);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public Student getStudentByUsn(String usn) {

        String sql = "SELECT * FROM student WHERE usn = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, usn);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return mapStudent(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM student";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                students.add(mapStudent(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public boolean updateStudent(Student student) {

        String sql = """
            UPDATE student
            SET usn = ?,
                name = ?,
                email = ?,
                phone = ?,
                department = ?,
                year = ?,
                cgpa = ?,
                resume_path = ?,
                placement_status = ?
            WHERE id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getUsn());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setString(5, student.getDepartment());
            statement.setInt(6, student.getYear());
            statement.setDouble(7, student.getCgpa());
            statement.setString(8, student.getResumePath());
            statement.setString(9, student.getPlacementStatus().name());
            statement.setInt(10, student.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteStudent(int id) {

        String sql = "DELETE FROM student WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Student> searchStudents(String keyword) {

        List<Student> students = new ArrayList<>();

        String sql = """
            SELECT * FROM student
            WHERE usn LIKE ?
               OR name LIKE ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            String search = "%" + keyword + "%";

            statement.setString(1, search);
            statement.setString(2, search);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                students.add(mapStudent(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    private Student mapStudent(ResultSet resultSet) throws SQLException {

        Student student = new Student();

        student.setId(resultSet.getInt("id"));
        student.setUsn(resultSet.getString("usn"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setPhone(resultSet.getString("phone"));
        student.setDepartment(resultSet.getString("department"));
        student.setYear(resultSet.getInt("year"));
        student.setCgpa(resultSet.getDouble("cgpa"));
        student.setResumePath(resultSet.getString("resume_path"));
        student.setPlacementStatus(
                PlacementStatus.valueOf(
                        resultSet.getString("placement_status")
                )
        );

        return student;
    }

    @Override
    public int getStudentCount() {

        String sql = "SELECT COUNT(*) FROM student";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
