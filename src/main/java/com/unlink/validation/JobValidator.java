package com.unlink.validation;

import com.unlink.model.Job;

public class JobValidator {

    public static void validate(Job job) {

        if (job == null) {
            throw new IllegalArgumentException("Job cannot be null.");
        }

        if (job.getTitle() == null || job.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Job title is required.");
        }

        if (job.getCompany() == null) {
            throw new IllegalArgumentException("Please select a company.");
        }

        if (job.getPackageAmount() < 0) {
            throw new IllegalArgumentException("Package cannot be negative.");
        }

        if (job.getMinimumCgpa() < 0 || job.getMinimumCgpa() > 10) {
            throw new IllegalArgumentException("CGPA must be between 0 and 10.");
        }

        if (job.getStatus() == null) {
            throw new IllegalArgumentException("Please select a job status.");
        }
    }
}