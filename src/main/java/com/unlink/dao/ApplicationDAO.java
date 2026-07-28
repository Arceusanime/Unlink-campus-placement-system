package com.unlink.dao;

import com.unlink.model.Application;

import java.util.List;

public interface ApplicationDAO {

    boolean addApplication(Application application);

    Application getApplicationById(int id);

    List<Application> getAllApplications();

    boolean updateApplication(Application application);

    boolean deleteApplication(int id);

    List<Application> searchApplications(String keyword);

    int getApplicationCount();

    int getSelectedCount();
}