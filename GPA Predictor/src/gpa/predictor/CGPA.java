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
class CGPA {
    private int semesterId;
    private double semesterGpa;
    private double semesterCgpa;
    private double semesterCreitsH;
    

    public CGPA() {
    }

    public CGPA(int semesterId, double semesterGpa, double semesterCgpa, double semesterCreitsH) {
        this.semesterId = semesterId;
        this.semesterGpa = semesterGpa;
        this.semesterCgpa = semesterCgpa;
        this.semesterCreitsH = semesterCreitsH;
    }

    
    public CGPA(int semesterId, double semesterGpa, double semesterCgpa) {
        this.semesterId = semesterId;
        this.semesterGpa = semesterGpa;
        this.semesterCgpa = semesterCgpa;
    }
    
    

    public double getSemesterCreitsH() {
        return semesterCreitsH;
    }

    

    public int getSemesterId() {
        return semesterId;
    }

    public double getSemesterGpa() {
        return semesterGpa;
    }

    public double getSemesterCgpa() {
        return semesterCgpa;
    }

    @Override
    public String toString() {
        return "CGPA{" + "semesterId=" + semesterId + ", semesterGpa=" + semesterGpa + ", semesterCgpa=" + semesterCgpa + ", semesterCreitsH=" + semesterCreitsH + '}';
    }



    
    
    
    
}
