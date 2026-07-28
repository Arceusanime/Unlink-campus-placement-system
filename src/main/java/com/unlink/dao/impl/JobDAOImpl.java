package com.unlink.dao.impl;

import com.unlink.config.DBConnection;
import com.unlink.dao.JobDAO;
import com.unlink.model.Company;
import com.unlink.model.Job;
import com.unlink.model.JobStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAOImpl implements JobDAO {

    private Job mapJob(ResultSet rs) throws SQLException {

        Company company = new Company();

        company.setId(rs.getInt("company_id"));
        company.setName(rs.getString("company_name"));

        Job job = new Job();

        job.setId(rs.getInt("id"));
        job.setTitle(rs.getString("title"));
        job.setCompany(company);
        job.setPackageAmount(rs.getDouble("package_amount"));
        job.setLocation(rs.getString("location"));
        job.setMinimumCgpa(rs.getDouble("minimum_cgpa"));
        job.setDescription(rs.getString("description"));
        job.setStatus(
                JobStatus.valueOf(
                        rs.getString("status")
                )
        );

        return job;
    }

    @Override
    public boolean addJob(Job job) {

        String sql = """
            INSERT INTO job
            (
                title,
                company_id,
                package_amount,
                location,
                minimum_cgpa,
                description,
                status
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, job.getTitle());
            statement.setInt(2, job.getCompany().getId());
            statement.setDouble(3, job.getPackageAmount());
            statement.setString(4, job.getLocation());
            statement.setDouble(5, job.getMinimumCgpa());
            statement.setString(6, job.getDescription());
            statement.setString(7, job.getStatus().name());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Job getJobById(int id) {

        String sql = """
            SELECT
                j.*,
                c.name AS company_name
            FROM job j
            JOIN company c
                ON j.company_id = c.id
            WHERE j.id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapJob(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Job> getAllJobs() {

        List<Job> jobs = new ArrayList<>();

        String sql = """
            SELECT
                j.*,
                c.name AS company_name
            FROM job j
            JOIN company c
                ON j.company_id = c.id
            ORDER BY j.id DESC
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                jobs.add(mapJob(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobs;
    }

    @Override
    public boolean updateJob(Job job) {

        String sql = """
            UPDATE job
            SET
                title = ?,
                company_id = ?,
                package_amount = ?,
                location = ?,
                minimum_cgpa = ?,
                description = ?,
                status = ?
            WHERE id = ?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, job.getTitle());
            statement.setInt(2, job.getCompany().getId());
            statement.setDouble(3, job.getPackageAmount());
            statement.setString(4, job.getLocation());
            statement.setDouble(5, job.getMinimumCgpa());
            statement.setString(6, job.getDescription());
            statement.setString(7, job.getStatus().name());
            statement.setInt(8, job.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteJob(int id) {

        String sql = "DELETE FROM job WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Job> searchJobs(String keyword) {

        List<Job> jobs = new ArrayList<>();

        String sql = """
            SELECT
                j.*,
                c.name AS company_name
            FROM job j
            JOIN company c
                ON j.company_id = c.id
            WHERE j.title LIKE ?
               OR c.name LIKE ?
            ORDER BY j.id DESC
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            String search = "%" + keyword + "%";

            statement.setString(1, search);
            statement.setString(2, search);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                jobs.add(mapJob(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jobs;
    }

    @Override
    public int getJobCount() {

        String sql = "SELECT COUNT(*) FROM job";

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