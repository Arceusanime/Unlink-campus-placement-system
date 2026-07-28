package com.unlink.dao.impl;

import com.unlink.config.DBConnection;
import com.unlink.dao.ApplicationDAO;
import com.unlink.model.Application;
import com.unlink.model.ApplicationStatus;
import com.unlink.model.Job;
import com.unlink.model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAOImpl implements ApplicationDAO {

    private Application mapApplication(ResultSet rs) throws SQLException {

        Student student = new Student();
        student.setId(rs.getInt("student_id"));
        student.setName(rs.getString("student_name"));

        Job job = new Job();
        job.setId(rs.getInt("job_id"));
        job.setTitle(rs.getString("job_title"));

        Application application = new Application();

        application.setId(rs.getInt("id"));
        application.setStudent(student);
        application.setJob(job);
        application.setApplicationDate(
                rs.getDate("application_date").toLocalDate()
        );
        application.setStatus(
                ApplicationStatus.valueOf(
                        rs.getString("status")
                )
        );

        return application;
    }

    @Override
    public boolean addApplication(Application application) {

        String sql = """
            INSERT INTO application
            (
                student_id,
                job_id,
                application_date,
                status
            )
            VALUES (?, ?, ?, ?)
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, application.getStudent().getId());
            statement.setInt(2, application.getJob().getId());
            statement.setDate(
                    3,
                    Date.valueOf(application.getApplicationDate())
            );
            statement.setString(4, application.getStatus().name());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Application getApplicationById(int id) {

        String sql = """
            SELECT
                a.*,
                s.name AS student_name,
                j.title AS job_title
            FROM application a
            JOIN student s
                ON a.student_id = s.id
            JOIN job j
                ON a.job_id = j.id
            WHERE a.id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapApplication(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Application> getAllApplications() {

        List<Application> applications = new ArrayList<>();

        String sql = """
            SELECT
                a.*,
                s.name AS student_name,
                j.title AS job_title
            FROM application a
            JOIN student s
                ON a.student_id = s.id
            JOIN job j
                ON a.job_id = j.id
            ORDER BY a.id DESC
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                applications.add(mapApplication(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }

    @Override
    public boolean updateApplication(Application application) {

        String sql = """
            UPDATE application
            SET
                student_id = ?,
                job_id = ?,
                application_date = ?,
                status = ?
            WHERE id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, application.getStudent().getId());
            statement.setInt(2, application.getJob().getId());
            statement.setDate(
                    3,
                    Date.valueOf(application.getApplicationDate())
            );
            statement.setString(4, application.getStatus().name());
            statement.setInt(5, application.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteApplication(int id) {

        String sql = "DELETE FROM application WHERE id = ?";

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
    public List<Application> searchApplications(String keyword) {

        List<Application> applications = new ArrayList<>();

        String sql = """
            SELECT
                a.*,
                s.name AS student_name,
                j.title AS job_title
            FROM application a
            JOIN student s
                ON a.student_id = s.id
            JOIN job j
                ON a.job_id = j.id
            WHERE s.name LIKE ?
               OR j.title LIKE ?
            ORDER BY a.id DESC
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            String search = "%" + keyword + "%";

            statement.setString(1, search);
            statement.setString(2, search);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                applications.add(mapApplication(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return applications;
    }

    @Override
    public int getApplicationCount() {

        String sql = "SELECT COUNT(*) FROM application";

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

    @Override
    public int getSelectedCount() {

        String sql = """
            SELECT COUNT(*)
            FROM application
            WHERE status = 'SELECTED'
            """;

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