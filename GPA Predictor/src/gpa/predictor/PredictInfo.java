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
public class PredictInfo {
    private String courseCode;
    private String courseTitle;
    private double credits;
    private String realGrade;
    private String predictGrade;
    

    public PredictInfo() {
    }

    public PredictInfo(String courseCode, String courseTitle, double credits, String realGrade, String predictGrade) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.credits = credits;
        this.realGrade = realGrade;
        this.predictGrade = predictGrade;
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

    public String getRealGrade() {
        return realGrade;
    }

    public String getPredictGrade() {
        return predictGrade;
    }
    

    
    
}
