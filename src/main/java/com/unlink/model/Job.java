package com.unlink.model;

public class Job {

    private int id;
    private String title;
    private Company company;
    private double packageAmount;
    private String location;
    private double minimumCgpa;
    private String description;
    private JobStatus status;

    public Job() {
    }

    public Job(
            int id,
            String title,
            Company company,
            double packageAmount,
            String location,
            double minimumCgpa,
            String description,
            JobStatus status
    ) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.packageAmount = packageAmount;
        this.location = location;
        this.minimumCgpa = minimumCgpa;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public double getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(double packageAmount) {
        this.packageAmount = packageAmount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getMinimumCgpa() {
        return minimumCgpa;
    }

    public void setMinimumCgpa(double minimumCgpa) {
        this.minimumCgpa = minimumCgpa;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Job other)) {
            return false;
        }

        return getId() == other.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }
}