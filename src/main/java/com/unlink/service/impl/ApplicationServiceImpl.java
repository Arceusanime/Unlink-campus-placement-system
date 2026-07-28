package com.unlink.service.impl;

import com.unlink.dao.ApplicationDAO;
import com.unlink.dao.impl.ApplicationDAOImpl;
import com.unlink.model.Application;
import com.unlink.service.ApplicationService;
import com.unlink.validation.ApplicationValidator;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationDAO applicationDAO = new ApplicationDAOImpl();

    @Override
    public boolean registerApplication(Application application) {

        ApplicationValidator.validate(application);

        return applicationDAO.addApplication(application);
    }

    @Override
    public Application getApplicationById(int id) {
        return applicationDAO.getApplicationById(id);
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationDAO.getAllApplications();
    }

    @Override
    public boolean updateApplication(Application application) {

        ApplicationValidator.validate(application);

        return applicationDAO.updateApplication(application);
    }

    @Override
    public boolean deleteApplication(int id) {
        return applicationDAO.deleteApplication(id);
    }

    @Override
    public List<Application> searchApplications(String keyword) {
        return applicationDAO.searchApplications(keyword);
    }

    @Override
    public int getApplicationCount() {
        return applicationDAO.getApplicationCount();
    }

    @Override
    public int getSelectedCount() {
        return applicationDAO.getSelectedCount();
    }
}