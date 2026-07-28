package com.unlink.dao;

import com.unlink.model.Job;

import java.util.List;

public interface JobDAO {

    boolean addJob(Job job);

    Job getJobById(int id);

    List<Job> getAllJobs();

    boolean updateJob(Job job);

    boolean deleteJob(int id);

    List<Job> searchJobs(String keyword);

    int getJobCount();
}