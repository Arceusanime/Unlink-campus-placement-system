package com.unlink.service;

import com.unlink.model.Application;

import java.util.List;

public interface ApplicationService {

    boolean registerApplication(Application application);

    Application getApplicationById(int id);

    List<Application> getAllApplications();

    boolean updateApplication(Application application);

    boolean deleteApplication(int id);

    List<Application> searchApplications(String keyword);

    int getApplicationCount();

    int getSelectedCount();
}