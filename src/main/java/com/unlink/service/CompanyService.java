package com.unlink.service;

import com.unlink.model.Company;

import java.util.List;

public interface CompanyService {

    boolean registerCompany(Company company);

    Company getCompanyById(int id);

    List<Company> getAllCompanies();

    boolean updateCompany(Company company);

    boolean deleteCompany(int id);

    List<Company> searchCompanies(String keyword);

    int getCompanyCount();
}