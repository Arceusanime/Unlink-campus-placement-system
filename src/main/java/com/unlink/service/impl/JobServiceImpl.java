package com.unlink.service.impl;

import com.unlink.dao.JobDAO;
import com.unlink.dao.impl.JobDAOImpl;
import com.unlink.model.Job;
import com.unlink.service.JobService;
import com.unlink.validation.JobValidator;

import java.util.List;

public class JobServiceImpl implements JobService {

    private final JobDAO jobDAO = new JobDAOImpl();

    @Override
    public boolean registerJob(Job job) {

        JobValidator.validate(job);

        return jobDAO.addJob(job);
    }

    @Override
    public Job getJobById(int id) {
        return jobDAO.getJobById(id);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobDAO.getAllJobs();
    }

    @Override
    public boolean updateJob(Job job) {

        JobValidator.validate(job);

        return jobDAO.updateJob(job);
    }

    @Override
    public boolean deleteJob(int id) {
        return jobDAO.deleteJob(id);
    }

    @Override
    public List<Job> searchJobs(String keyword) {
        return jobDAO.searchJobs(keyword);
    }

    @Override
    public int getJobCount() {
        return jobDAO.getJobCount();
    }
}