package com.unlink.service.impl;

import com.unlink.dao.CompanyDAO;
import com.unlink.dao.impl.CompanyDAOImpl;
import com.unlink.model.Company;
import com.unlink.service.CompanyService;
import com.unlink.validation.CompanyValidator;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyDAO companyDAO = new CompanyDAOImpl();

    @Override
    public boolean registerCompany(Company company) {

        CompanyValidator.validate(company);

        return companyDAO.addCompany(company);
    }

    @Override
    public Company getCompanyById(int id) {
        return companyDAO.getCompanyById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyDAO.getAllCompanies();
    }

    @Override
    public boolean updateCompany(Company company) {

        CompanyValidator.validate(company);

        return companyDAO.updateCompany(company);
    }

    @Override
    public boolean deleteCompany(int id) {
        return companyDAO.deleteCompany(id);
    }

    @Override
    public List<Company> searchCompanies(String keyword) {
        return companyDAO.searchCompanies(keyword);
    }

    @Override
    public int getCompanyCount() {
        return companyDAO.getCompanyCount();
    }
}