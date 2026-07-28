package com.unlink.validation;

import com.unlink.model.Application;

public class ApplicationValidator {

    public static void validate(Application application) {

        if (application == null) {
            throw new IllegalArgumentException("Application cannot be null.");
        }

        if (application.getStudent() == null) {
            throw new IllegalArgumentException("Please select a student.");
        }

        if (application.getJob() == null) {
            throw new IllegalArgumentException("Please select a job.");
        }

        if (application.getApplicationDate() == null) {
            throw new IllegalArgumentException("Application date is required.");
        }

        if (application.getStatus() == null) {
            throw new IllegalArgumentException("Please select an application status.");
        }
    }

}