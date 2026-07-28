package com.unlink.model;

public class Company {

    private int id;
    private String name;
    private String industry;
    private String location;
    private String website;
    private String hrName;
    private String hrEmail;
    private String hrPhone;

    public Company() {
    }

    public Company(
            int id,
            String name,
            String industry,
            String location,
            String website,
            String hrName,
            String hrEmail,
            String hrPhone
    ) {
        this.id = id;
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.website = website;
        this.hrName = hrName;
        this.hrEmail = hrEmail;
        this.hrPhone = hrPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public String getHrEmail() {
        return hrEmail;
    }

    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
    }

    public String getHrPhone() {
        return hrPhone;
    }

    public void setHrPhone(String hrPhone) {
        this.hrPhone = hrPhone;
    }
    @Override
    public String toString() {
        return name;
    }
}