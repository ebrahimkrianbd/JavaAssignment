/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpa.predictor;

import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author rianix
 */
class Student {
    private String studentId;
    private String studentName;
    private int batch;
    private ObservableList<Course> course;
    private ObservableList<CGPA> cgpa;

    public Student() {
    }

    public Student(String studentId, String studentName, ObservableList<Course> course, ObservableList<CGPA> cgpa) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.course = course;
        this.cgpa = cgpa;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getBatch() {
        return batch;
    }

    public ObservableList<Course> getCourse() {
        return course;
    }

    public ObservableList<CGPA> getCgpa() {
        return cgpa;
    }


   
    
    
   
    
}
