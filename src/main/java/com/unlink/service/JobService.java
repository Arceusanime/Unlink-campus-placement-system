package com.unlink.service;

import com.unlink.model.Job;

import java.util.List;

public interface JobService {

    boolean registerJob(Job job);

    Job getJobById(int id);

    List<Job> getAllJobs();

    boolean updateJob(Job job);

    boolean deleteJob(int id);

    List<Job> searchJobs(String keyword);

    int getJobCount();
}