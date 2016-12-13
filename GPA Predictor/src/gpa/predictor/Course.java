/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpa.predictor;

/**
 *
 * @author rianix
 */
public class Course {
    private int semesterId;
    private String facultyInitials;
    private String courseCode;
    private String courseTitle;
    private double credits;
    private String grade;
    

    public Course() {
    }

    public Course(String courseCode, double credits, String grade) {
        this.courseCode = courseCode;
        this.credits = credits;
        this.grade = grade;
    }

    
    public Course(int semesterId, String facultyInitials, String courseCode, String courseTitle, double credits, String grade) {
        this.semesterId = semesterId;
        this.facultyInitials = facultyInitials;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.credits = credits;
        this.grade = grade;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public String getFacultyInitials() {
        return facultyInitials;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public double getCredits() {
        return credits;
    }

    public String getGrade() {
        return grade;
    }

    
    
}
