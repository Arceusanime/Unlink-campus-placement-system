package com.unlink.model;

public class Student extends Person {

    private String usn;
    private String department;
    private int year;
    private double cgpa;
    private String resumePath;
    private PlacementStatus placementStatus;

    public Student() {
    }

    public Student(int id, String name, String email, String phone,
                   String usn, String department, int year,
                   double cgpa, String resumePath,
                   PlacementStatus placementStatus) {

        super(id, name, email, phone);

        this.usn = usn;
        this.department = department;
        this.year = year;
        this.cgpa = cgpa;
        this.resumePath = resumePath;
        this.placementStatus = placementStatus;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public PlacementStatus getPlacementStatus() {
        return placementStatus;
    }

    public void setPlacementStatus(PlacementStatus placementStatus) {
        this.placementStatus = placementStatus;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                ", usn='" + usn + '\'' +
                ", department='" + department + '\'' +
                ", year=" + year +
                ", cgpa=" + cgpa +
                ", resumePath='" + resumePath + '\'' +
                ", placementStatus=" + placementStatus +
                '}';
    }
}