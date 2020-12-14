package com.college.courseselection;

public class Course {

    private String coursename;
    private double coursefee;
    private double coursehour;
    private String stdcourse;


    public Course(String coursename, double coursefee, double coursehour , String stdcourse) {
        this.coursename = coursename;
        this.coursefee = coursefee;
        this.coursehour = coursehour;
        this.stdcourse = stdcourse;

    }

    public String getCoursename() {
        return coursename;
    }

    public double getCoursefee() {
        return coursefee;
    }

    public double getCoursehour() {
        return coursehour;
    }

    public String getStdcourse() {
        return stdcourse;
    }
}
