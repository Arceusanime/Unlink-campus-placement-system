package com.unlink.dao.impl;

import com.unlink.config.DBConnection;
import com.unlink.dao.CompanyDAO;
import com.unlink.model.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {

    private Company mapCompany(ResultSet rs) throws SQLException {

        Company company = new Company();

        company.setId(rs.getInt("id"));
        company.setName(rs.getString("name"));
        company.setIndustry(rs.getString("industry"));
        company.setLocation(rs.getString("location"));
        company.setWebsite(rs.getString("website"));
        company.setHrName(rs.getString("hr_name"));
        company.setHrEmail(rs.getString("hr_email"));
        company.setHrPhone(rs.getString("hr_phone"));

        return company;
    }

    @Override
    public boolean addCompany(Company company) {

        String sql = """
        INSERT INTO company
        (name, industry, location, website,
         hr_name, hr_email, hr_phone)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, company.getName());
            statement.setString(2, company.getIndustry());
            statement.setString(3, company.getLocation());
            statement.setString(4, company.getWebsite());
            statement.setString(5, company.getHrName());
            statement.setString(6, company.getHrEmail());
            statement.setString(7, company.getHrPhone());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Company getCompanyById(int id) {

        String sql = "SELECT * FROM company WHERE id = ?";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapCompany(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Company> getAllCompanies() {

        List<Company> companies = new ArrayList<>();

        String sql = "SELECT * FROM company ORDER BY name";

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                companies.add(mapCompany(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companies;
    }

    @Override
    public boolean updateCompany(Company company) {

        String sql = """
        UPDATE company
        SET
            name = ?,
            industry = ?,
            location = ?,
            website = ?,
            hr_name = ?,
            hr_email = ?,
            hr_phone = ?
        WHERE id = ?
        """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, company.getName());
            statement.setString(2, company.getIndustry());
            statement.setString(3, company.getLocation());
            statement.setString(4, company.getWebsite());
            statement.setString(5, company.getHrName());
            statement.setString(6, company.getHrEmail());
            statement.setString(7, company.getHrPhone());
            statement.setInt(8, company.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteCompany(int id) {

        String sql = "DELETE FROM company WHERE id = ?";

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
    public List<Company> searchCompanies(String keyword) {

        List<Company> companies = new ArrayList<>();

        String sql = """
        SELECT *
        FROM company
        WHERE name LIKE ?
           OR industry LIKE ?
        ORDER BY name
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
                companies.add(mapCompany(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companies;
    }

    @Override
    public int getCompanyCount() {

        String sql = "SELECT COUNT(*) FROM company";

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


