package com.unlink.model;

import java.time.LocalDate;

public class Application {

    private int id;
    private Student student;
    private Job job;
    private LocalDate applicationDate;
    private ApplicationStatus status;

    public Application() {
    }

    public Application(
            int id,
            Student student,
            Job job,
            LocalDate applicationDate,
            ApplicationStatus status
    ) {
        this.id = id;
        this.student = student;
        this.job = job;
        this.applicationDate = applicationDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}