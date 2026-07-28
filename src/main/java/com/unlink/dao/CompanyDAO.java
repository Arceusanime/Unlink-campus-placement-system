package com.unlink.dao;

import com.unlink.model.Company;

import java.util.List;

public interface CompanyDAO {

    boolean addCompany(Company company);

    Company getCompanyById(int id);

    List<Company> getAllCompanies();

    boolean updateCompany(Company company);

    boolean deleteCompany(int id);

    List<Company> searchCompanies(String keyword);

    int getCompanyCount();
}