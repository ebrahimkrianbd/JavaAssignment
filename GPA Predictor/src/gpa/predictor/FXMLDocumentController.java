/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gpa.predictor;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author rianix
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private Label nameLabel;
    @FXML
    private Label idLabel;

    @FXML
    private TableView<Course> currentPositionTableView;
    @FXML
    private TableColumn<Course, Number> currentPositionSemesterColumn;
    @FXML
    private TableColumn<Course, String> currentPositionCourseCodeColumn;
    @FXML
    private TableColumn<Course, String> currentPositionCourseTitleColumn;
    @FXML
    private TableColumn<Course, Number> currentPositionCreditsColumn;
    @FXML
    private TableColumn<Course, String> currentPositionGradeColumn;

    @FXML
    private Label creditsAttemptedLabel;
    @FXML
    private Label cgpaLabel;

    @FXML
    private TableView<PredictInfo> predictorTableView;
    @FXML
    private TableColumn<PredictInfo, String> predictorCourseCodeTableColumn;
    @FXML
    private TableColumn<PredictInfo, String> predictorCourseTitleTableColumn;
    @FXML
    private TableColumn<PredictInfo, Number> predictorCreditsTableColumn;
    @FXML
    private TableColumn<PredictInfo, String> predictorRealGradeTableColumn;
    @FXML
    private TableColumn<PredictInfo, String> predictorPredictGradeTableColumn;
    

    @FXML
    private Label assumptionGPALabel;
    @FXML
    private Label actualGPALabel;
    @FXML
    private Label trueErrorLabel;

    private ObservableList<Student> studentList;
    private ObservableList<PredictInfo> predictInfoList;
    private ObservableList<Course> courseList;
    private ObservableList<CGPA> cgpaList;
    private ObservableList<Integer> semesterIdList;
    private ObservableList<String> idList;
    private boolean searchOk;
    private String studentId;
    private int studentIndex;
    private boolean isStudent;
    private Connection connection;
    @FXML
    private ComboBox<Integer> semesterIdChooser;
    @FXML
    private Label massegeLabel;
    @FXML
    private ListView<String> idListView;

    private int currentSemester() {
        int data = 0;
        try {
            Statement statement6 = connection.createStatement();
            String query6 = "select * from registration";
            ResultSet resultSet6 = statement6.executeQuery(query6);
            while (resultSet6.next()) {
                data = resultSet6.getInt("semesterId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        studentList = FXCollections.observableArrayList();
        courseList = FXCollections.observableArrayList();
        cgpaList = FXCollections.observableArrayList();
        semesterIdList = FXCollections.observableArrayList();
        idList = FXCollections.observableArrayList();
        predictInfoList = FXCollections.observableArrayList();

        String HostName = "172.17.0.134";
        String DBName = "predictdb";
        String Password = "java";
        String DBUser = "cse2015fall2016";
        String DBURL = "jdbc:mysql://" + HostName + "/" + DBName;
        int count = 0;

        try {
            connection = DriverManager.getConnection(DBURL, DBUser, Password);
            System.out.println("Connected");

            Statement statement = connection.createStatement();
            String query = "select * from student;";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                idList.add(resultSet.getString("studentId"));
            }
            idListView.setItems(idList);

//            Statement statement = connection.createStatement();
//            Statement statement2 = connection.createStatement();
//            Statement statement3 = connection.createStatement();
//            Statement statement4 = connection.createStatement();
//            Statement statement5 = connection.createStatement();
//            int currentSemesterId = currentSemester();
//
//            String query = "select * from student;";
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//
//                String studentName = "";
//                String studentId = "";
//                int batch = -1;
//                ObservableList<Course> course = FXCollections.observableArrayList();
//                ObservableList<CGPA> cgpa = FXCollections.observableArrayList();
//
//                studentName = resultSet.getString("studentName");
//                studentId = resultSet.getString("studentId");
//
//                String query3 = "select * from registration where studentId = '" + studentId + "';";
//                ResultSet resultSet3 = statement3.executeQuery(query3);
//                while (resultSet3.next()) {
//                    int semesterId = 0;
//                    String facultyInitials = "";
//                    String courseCode = "";
//                    String courseTitle = "";
//                    double credits = 0;
//                    String grade = "";
//
//                    semesterId = resultSet3.getInt("semesterId");
//                    facultyInitials = resultSet3.getString("facultyInitials");
//                    courseCode = resultSet3.getString("courseCode");
//
//                    String query4 = "select * from course where courseCode = '" + courseCode + "';";
//                    ResultSet resultSet4 = statement4.executeQuery(query4);
//                    while (resultSet4.next()) {
//                        courseTitle = resultSet4.getString("courseTitle");
//                        credits = resultSet4.getDouble("credits");
//                    }
//
//                    String query5 = "select * from grades where studentId = '" + studentId
//                            + "' and courseCode = '" + courseCode
//                            + "' and facultyInitials = '" + facultyInitials
//                            + "' and semesterId = '" + semesterId + "';";
//                    ResultSet resultSet5 = statement5.executeQuery(query5);
//                    while (resultSet5.next()) {
//                        grade = resultSet5.getString("grade");
//
//                    }
//
//                    course.add(new Course(semesterId, facultyInitials, courseCode, courseTitle, credits, grade));
//                }
//
//                int len = course.size();
//                System.out.println(studentId);
//                double creditCounter = 0;
//                double gpa = 0;
//                double semesterGpa = 0;
//                double semesterCgpa = 0;
//
//                double creditCounterforSemester = 0;
//                if (len != 0) {
//                    int semesterId = course.get(0).getSemesterId();
//
//                    for (int i = 0; i < len; i++) {
//                        String courseGrade = course.get(i).getGrade();
//                        double courseCredits = course.get(i).getCredits();
//                        int sId = course.get(i).getSemesterId();
//                        if (semesterId != sId || len - 1 == i) {
//                            semesterGpa = (gpa - (semesterCgpa * (creditCounter - creditCounterforSemester))) / creditCounterforSemester;
//                            semesterCgpa = gpa / creditCounter;
//
//                            cgpa.add(new CGPA(semesterId, semesterGpa, semesterCgpa, creditCounter));
//                            semesterId = course.get(i).getSemesterId();
//                            creditCounterforSemester = 0;
//                        }
//                        if (course.get(i).getSemesterId() != currentSemesterId) {
//                            for (int j = i - 1; j >= 0; j--) {
//                                if (course.get(i).getCourseCode().equals(course.get(j).getCourseCode())) {
//                                    if (course.get(j).getGrade().equals("A+")) {
//                                        gpa -= 4.00 * course.get(j).getCredits();
//                                        creditCounter -= course.get(j).getCredits();
//                                    } else if (course.get(j).getGrade().equals("A")) {
//                                        gpa -= 3.75 * course.get(j).getCredits();
//                                        creditCounter -= course.get(j).getCredits();
//                                    } else if (course.get(j).getGrade().equals("A-")) {
//                                        gpa -= 3.50 * course.get(j).getCredits();
//                                        creditCounter -= course.get(j).getCredits();
//                                    } else if (course.get(j).getGrade().equals("B+")) {
//                                        gpa -= 3.25 * course.get(j).getCredits();
//                                        creditCounter -= course.get(j).getCredits();
//                                    } else if (course.get(j).getGrade().equals("B")) {
//                                        gpa -= 3.00 * course.get(j).getCredits();
//                                        creditCounter -= course.get(j).getCredits();
//                                    } else if (course.get(j).getGrade().equals("B-")) {
//                                        gpa -= 2.75 * course.get(j).getCredits();
//                                        creditCounter -= course.get(j).getCredits();
//                                    } else if (course.get(j).getGrade().equals("C+")) {
//                                        gpa -= 2.50 * course.get(j).getCredits();
//                                        creditCounter -= course.get(j).getCredits();
//                                    } else if (course.get(j).getGrade().equals("C")) {
//                                        gpa -= 2.25 * course.get(j).getCredits();
//                                        creditCounter -= course.get(j).getCredits();
//                                    } else if (course.get(j).getGrade().equals("D")) {
//                                        gpa -= 2.00 * course.get(j).getCredits();
//                                        creditCounter -= course.get(j).getCredits();
//                                    } else if (course.get(j).getGrade().equals("F")) {
//                                        gpa -= 0.00 * course.get(j).getCredits();
//                                        creditCounter -= course.get(j).getCredits();
//                                    }
//                                    break;
//                                }
//                            }
//                        }
//
//                        if (courseGrade.equals("A+")) {
//                            gpa += 4.00 * courseCredits;
//                            creditCounterforSemester += courseCredits;
//                            creditCounter += courseCredits;
//                        } else if (courseGrade.equals("A")) {
//                            creditCounterforSemester += courseCredits;
//                            gpa += 3.75 * courseCredits;
//                            creditCounter += courseCredits;
//                        } else if (courseGrade.equals("A-")) {
//                            creditCounterforSemester += courseCredits;
//                            gpa += 3.50 * courseCredits;
//                            creditCounter += courseCredits;
//                        } else if (courseGrade.equals("B+")) {
//                            creditCounterforSemester += courseCredits;
//                            gpa += 3.25 * courseCredits;
//                            creditCounter += courseCredits;
//                        } else if (courseGrade.equals("B")) {
//                            creditCounterforSemester += courseCredits;
//                            gpa += 3.00 * courseCredits;
//                            creditCounter += courseCredits;
//                        } else if (courseGrade.equals("B-")) {
//                            creditCounterforSemester += courseCredits;
//                            gpa += 2.75 * courseCredits;
//                            creditCounter += courseCredits;
//                        } else if (courseGrade.equals("C+")) {
//                            creditCounterforSemester += courseCredits;
//                            gpa += 2.50 * courseCredits;
//                            creditCounter += courseCredits;
//                        } else if (courseGrade.equals("C")) {
//                            creditCounterforSemester += courseCredits;
//                            gpa += 2.25 * courseCredits;
//                            creditCounter += courseCredits;
//                        } else if (courseGrade.equals("D")) {
//                            creditCounterforSemester += courseCredits;
//                            gpa += 2.00 * courseCredits;
//                            creditCounter += courseCredits;
//                        } else if (courseGrade.equals("F")) {
//                            creditCounterforSemester += courseCredits;
//                            gpa += 0.00 * courseCredits;
//                            creditCounter += courseCredits;
//                        }
//
//                    }
//                }
//                int length = cgpa.size();
//                System.out.println(studentName + "      total complete credit = " + creditCounter);
//                for (int i = 0; i < length; i++) {
//                    System.out.println("Semester ID: " + cgpa.get(i).getSemesterId()
//                            + " CGPA = " + cgpa.get(i).getSemesterCgpa()
//                            + "  GPA = " + cgpa.get(i).getSemesterGpa());
//                }
//
//                studentList.add(new Student(studentId, studentName, course, cgpa));
//            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleSearchStudentIdAction(ActionEvent event) {
//        semesterIdChooser.setSelectionModel(null);
        studentId = "";
        boolean tableViewOk = false;
        massegeLabel.setText("");
        cgpaLabel.setText("");
        creditsAttemptedLabel.setText("");
        predictInfoList.clear();
        courseList.clear();
        cgpaList.clear();
        semesterIdList.clear();
        nameLabel.setText("");
        idLabel.setText("");
        String search = searchField.getText();
        int len = search.length();
        int count = 0;
        searchOk = true;
        isStudent = true;
        for (int i = 0; i < len; i++) {
            if (search.charAt(i) != '1' && search.charAt(i) != '2' && search.charAt(i) != '3' && search.charAt(i) != '4'
                    && search.charAt(i) != '5' && search.charAt(i) != '6' && search.charAt(i) != '7' && search.charAt(i) != '8'
                    && search.charAt(i) != '9' && search.charAt(i) != '0') {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Something Wrong");
                alert.setHeaderText("Something Wrong");
                alert.setContentText("You used wrong key.");
                searchOk = false;
                alert.showAndWait();
                break;
            }
            count++;
        }

        if (count != 5 && searchOk) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Something Wrong");
            alert.setHeaderText("Something Wrong");
            alert.setContentText("You used less than or greater than 5digits ID.");
            searchOk = false;
            alert.showAndWait();
        }

        if (searchOk) {

            try {
                Statement statement1 = connection.createStatement();
                String query1 = "select * from student where studentId = '" + search + "';";
                ResultSet resultSet = statement1.executeQuery(query1);

                if (resultSet.next()) {
                    String name = resultSet.getString("studentName");
                    nameLabel.setText(name);
                    idLabel.setText(search);

                    Statement statement2 = connection.createStatement();
                    String query2 = "select * from grades where studentId = '" + search + "';";
                    ResultSet resultSet2 = statement2.executeQuery(query2);
                    while (resultSet2.next()) {
                        tableViewOk = true;
                        studentId = search;
                        int semesterId = resultSet2.getInt("semesterId");
                        String facultyInitials = resultSet2.getString("facultyInitials");
                        String courseCode = resultSet2.getString("courseCode");
                        String courseTitle = "";
                        double credits = 0;
                        Statement statement3 = connection.createStatement();
                        String query3 = "select * from course where courseCode = '" + courseCode + "';";
                        ResultSet resultSet3 = statement3.executeQuery(query3);
                        if (resultSet3.next()) {
                            courseTitle = resultSet3.getString("courseTitle");
                            credits = resultSet3.getDouble("credits");;
                        }
                        String grade = resultSet2.getString("grade");
                        courseList.add(new Course(semesterId, facultyInitials, courseCode, courseTitle, credits, grade));
//                        while (resultSet2.next()) {
//                            semesterId = resultSet2.getInt("semesterId");
//                            facultyInitials = resultSet2.getString("facultyInitials");
//                            courseCode = resultSet2.getString("courseCode");
//                            courseTitle = "";
//                            Statement statement4 = connection.createStatement();
//                            String query4 = "select * from course where courseCode = '" + courseCode + "';";
//                            ResultSet resultSet4 = statement4.executeQuery(query4);
//                            if (resultSet4.next()) {
//                                courseTitle = resultSet3.getString("courseTitle");
//                            }
//                            credits = resultSet2.getInt("courseCode");;
//                            grade = resultSet2.getString("grade");
//                            courseList.add(new Course(semesterId, facultyInitials, courseCode, courseTitle, credits, grade));
//
//                        }

                    }
                    if (!tableViewOk) {
                        massegeLabel.setText("Data not available");
                    }

                    if (tableViewOk) {
                        Statement statement5 = connection.createStatement();
                        String query5 = "select * from registration where studentId = '" + search + "';";
                        ResultSet resultSet5 = statement5.executeQuery(query5);
                        int semester1 = 0;
                        int semester2 = 0;
                        while (resultSet5.next()) {
                            semester1 = resultSet5.getInt("semesterId");
                            if (semester1 != semester2) {
                                semesterIdList.add(semester1);
//                            System.out.println(semester1);
                                semester2 = semester1;
                            }
                        }

//                        int semesterId;
//                        double semesterGpa;
//                        double semesterCgpa;
//                        double semesterCreitsH;
//                        double countCredits = 0;
//                        double countCredi = 0;
//                        
//                        int courseListLen = courseList.size();
//                        for(int i=0; i<courseListLen; i++){
                        int courseListLen = courseList.size();
                        double creditCounter = 0;
                        double gpa = 0;
                        double semesterGpa = 0;
                        double semesterCgpa = 0;
                        double creditCounterforSemester = 0;
                        int semesterId = courseList.get(0).getSemesterId();

                        for (int i = 0; i < courseListLen; i++) {
                            String courseGrade = courseList.get(i).getGrade();
                            double courseCredits = courseList.get(i).getCredits();
                            int sId = courseList.get(i).getSemesterId();

                            for (int j = i - 1; j >= 0; j--) {
                                if (courseList.get(i).getCourseCode().equals(courseList.get(j).getCourseCode())) {
                                    if (courseList.get(j).getGrade().equals("A+")) {
                                        gpa -= 4.00 * courseList.get(j).getCredits();
                                        creditCounter -= courseList.get(j).getCredits();
                                    } else if (courseList.get(j).getGrade().equals("A")) {
                                        gpa -= 3.75 * courseList.get(j).getCredits();
                                        creditCounter -= courseList.get(j).getCredits();
                                    } else if (courseList.get(j).getGrade().equals("A-")) {
                                        gpa -= 3.50 * courseList.get(j).getCredits();
                                        creditCounter -= courseList.get(j).getCredits();
                                    } else if (courseList.get(j).getGrade().equals("B+")) {
                                        gpa -= 3.25 * courseList.get(j).getCredits();
                                        creditCounter -= courseList.get(j).getCredits();
                                    } else if (courseList.get(j).getGrade().equals("B")) {
                                        gpa -= 3.00 * courseList.get(j).getCredits();
                                        creditCounter -= courseList.get(j).getCredits();
                                    } else if (courseList.get(j).getGrade().equals("B-")) {
                                        gpa -= 2.75 * courseList.get(j).getCredits();
                                        creditCounter -= courseList.get(j).getCredits();
                                    } else if (courseList.get(j).getGrade().equals("C+")) {
                                        gpa -= 2.50 * courseList.get(j).getCredits();
                                        creditCounter -= courseList.get(j).getCredits();
                                    } else if (courseList.get(j).getGrade().equals("C")) {
                                        gpa -= 2.25 * courseList.get(j).getCredits();
                                        creditCounter -= courseList.get(j).getCredits();
                                    } else if (courseList.get(j).getGrade().equals("D")) {
                                        gpa -= 2.00 * courseList.get(j).getCredits();
                                        creditCounter -= courseList.get(j).getCredits();
                                    } else if (courseList.get(j).getGrade().equals("F")) {
                                        gpa -= 0.00 * courseList.get(j).getCredits();
                                        creditCounter -= courseList.get(j).getCredits();
                                    }
                                    break;
                                }
                            }

                            if (courseGrade.equals("A+")) {
                                gpa += 4.00 * courseCredits;
                                creditCounterforSemester += courseCredits;
                                creditCounter += courseCredits;
                            } else if (courseGrade.equals("A")) {
                                creditCounterforSemester += courseCredits;
                                gpa += 3.75 * courseCredits;
                                creditCounter += courseCredits;
                            } else if (courseGrade.equals("A-")) {
                                creditCounterforSemester += courseCredits;
                                gpa += 3.50 * courseCredits;
                                creditCounter += courseCredits;
                            } else if (courseGrade.equals("B+")) {
                                creditCounterforSemester += courseCredits;
                                gpa += 3.25 * courseCredits;
                                creditCounter += courseCredits;
                            } else if (courseGrade.equals("B")) {
                                creditCounterforSemester += courseCredits;
                                gpa += 3.00 * courseCredits;
                                creditCounter += courseCredits;
                            } else if (courseGrade.equals("B-")) {
                                creditCounterforSemester += courseCredits;
                                gpa += 2.75 * courseCredits;
                                creditCounter += courseCredits;
                            } else if (courseGrade.equals("C+")) {
                                creditCounterforSemester += courseCredits;
                                gpa += 2.50 * courseCredits;
                                creditCounter += courseCredits;
                            } else if (courseGrade.equals("C")) {
                                creditCounterforSemester += courseCredits;
                                gpa += 2.25 * courseCredits;
                                creditCounter += courseCredits;
                            } else if (courseGrade.equals("D")) {
                                creditCounterforSemester += courseCredits;
                                gpa += 2.00 * courseCredits;
                                creditCounter += courseCredits;
                            } else if (courseGrade.equals("F")) {
                                creditCounterforSemester += courseCredits;
                                gpa += 0.00 * courseCredits;
                                creditCounter += courseCredits;
                            }

                            if (semesterId != sId || courseListLen - 1 == i) {
                                semesterGpa = (gpa - (semesterCgpa * (creditCounter - creditCounterforSemester))) / creditCounterforSemester;
                                semesterCgpa = gpa / creditCounter;

                                cgpaList.add(new CGPA(semesterId, semesterGpa, semesterCgpa, creditCounter));
                                semesterId = courseList.get(i).getSemesterId();
                                creditCounterforSemester = 0;
                            }

                        }

                    }

                } else {
                    massegeLabel.setText("This ID not a student of SEU");

                }

//            int idLen = studentList.size();
//            int i = 0;
//            for (i = 0; i < idLen; i++) {
//                String studentId = studentList.get(i).getStudentId();
//                if (studentId.equals(search)) {
//                    studentIndex = i;
//                    break;
//                }
//            }
//
//            if (i < idLen) {
//                int lenght = studentList.get(i).getCourse().size();
//                int r;
//                for (r = 0; r < lenght; r++) {
//                    String G = studentList.get(i).getCourse().get(r).getGrade();
//                    if (G.equals("A+") || G.equals("A") || G.equals("A-")
//                            || G.equals("B+") || G.equals("B") || G.equals("B-")
//                            || G.equals("C+") || G.equals("C") || G.equals("D") || G.equals("F")) {
//                        tempCourse.add(studentList.get(i).getCourse().get(r));
//                    }
//                }
//                nameLabel.setText(studentList.get(i).getStudentName());
//                idLabel.setText(studentList.get(i).getStudentId());
//                if (r > 0) {
//                    currentPositionTableView.setItems(tempCourse);
//                    currentPositionSemesterColumn.setCellValueFactory(Data -> new SimpleIntegerProperty(Data.getValue().getSemesterId()));
//                    currentPositionCourseCodeColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getCourseCode()));
//                    currentPositionCourseTitleColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getCourseTitle()));
//                    currentPositionCreditsColumn.setCellValueFactory(Data -> new SimpleDoubleProperty(Data.getValue().getCredits()));
//                    currentPositionGradeColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getGrade()));
//                    int lenCgpa = studentList.get(i).getCgpa().size();
//                    int lenTemp = tempCourse.size();
//                    int semesterNumber = tempCourse.get(lenTemp - 1).getSemesterId();
//                    int a;
//                    for (a = lenCgpa - 1; a >= 0; a--) {
//                        if (semesterNumber == studentList.get(i).getCgpa().get(a).getSemesterId()) {
//                            break;
//                        }
//                    }
//                    String output = String.format("%.3f", studentList.get(i).getCgpa().get(a).getSemesterCgpa());
//                    cgpaLabel.setText(output);
//                    creditsAttemptedLabel.setText("" + studentList.get(i).getCgpa().get(a).getSemesterCreitsH());
//                    semesterIdChooser.setItems(studentList.get(i).getCgpa());
//                } else {
//                    massegeLabel.setText("Data not available");
//                }
//
//            } else {
//                massegeLabel.setText("Data not available");
//            }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            if (tableViewOk) {
                currentPositionTableView.setItems(courseList);
                currentPositionSemesterColumn.setCellValueFactory(Data -> new SimpleIntegerProperty(Data.getValue().getSemesterId()));
                currentPositionCourseCodeColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getCourseCode()));
                currentPositionCourseTitleColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getCourseTitle()));
                currentPositionCreditsColumn.setCellValueFactory(Data -> new SimpleDoubleProperty(Data.getValue().getCredits()));
                currentPositionGradeColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getGrade()));
                semesterIdChooser.setItems(semesterIdList);
                String output = String.format("%.3f", cgpaList.get(cgpaList.size() - 1).getSemesterCgpa());
                cgpaLabel.setText(output);
                creditsAttemptedLabel.setText("" + cgpaList.get(cgpaList.size() - 1).getSemesterCreitsH());
            }

//            int cgpaLen = cgpaList.size();
//            for (int i = 0; i < cgpaLen; i++) {
//                System.out.println(cgpaList.get(i));
//            }
        }
    }

    @FXML
    private void handleSearchListViewKeyStudentIdAction(KeyEvent event) {
        String value = searchField.getText();
        ObservableList<String> newIdList = FXCollections.observableArrayList();
        int len = idList.size();
        for (int i = 0; i < len; i++) {
            String getIdList = idList.get(i);
            if (getIdList.contains(value)) {
                newIdList.add(getIdList);
            }
        }
        idListView.setItems(newIdList);

    }

    @FXML
    private void handleListViewSelectAction(MouseEvent event) {
        studentId = "";
        String search = idListView.getSelectionModel().getSelectedItem();
        boolean tableViewOk = false;
        massegeLabel.setText("");
        cgpaLabel.setText("");
        creditsAttemptedLabel.setText("");
        predictInfoList.clear();
        courseList.clear();
        cgpaList.clear();
        semesterIdList.clear();
//        semesterIdChooser.setItems(semesterIdList);
        nameLabel.setText("");
        idLabel.setText("");
        try {
            Statement statement1 = connection.createStatement();
            String query1 = "select * from student where studentId = '" + search + "';";
            ResultSet resultSet = statement1.executeQuery(query1);

            if (resultSet.next()) {
                String name = resultSet.getString("studentName");
                nameLabel.setText(name);
                idLabel.setText(search);

                Statement statement2 = connection.createStatement();
                String query2 = "select * from grades where studentId = '" + search + "';";
                ResultSet resultSet2 = statement2.executeQuery(query2);
                while (resultSet2.next()) {
                    tableViewOk = true;
                    studentId = search;
                    int semesterId = resultSet2.getInt("semesterId");
                    String facultyInitials = resultSet2.getString("facultyInitials");
                    String courseCode = resultSet2.getString("courseCode");
                    String courseTitle = "";
                    double credits = 0;
                    Statement statement3 = connection.createStatement();
                    String query3 = "select * from course where courseCode = '" + courseCode + "';";
                    ResultSet resultSet3 = statement3.executeQuery(query3);
                    if (resultSet3.next()) {
                        courseTitle = resultSet3.getString("courseTitle");
                        credits = resultSet3.getDouble("credits");;
                    }
                    String grade = resultSet2.getString("grade");
                    courseList.add(new Course(semesterId, facultyInitials, courseCode, courseTitle, credits, grade));

                }
                if (!tableViewOk) {
                    massegeLabel.setText("Data not available");
                }

                if (tableViewOk) {
                    Statement statement5 = connection.createStatement();
                    String query5 = "select * from registration where studentId = '" + search + "';";
                    ResultSet resultSet5 = statement5.executeQuery(query5);
                    int semester1 = 0;
                    int semester2 = 0;
                    while (resultSet5.next()) {
                        semester1 = resultSet5.getInt("semesterId");
                        if (semester1 != semester2) {
                            semesterIdList.add(semester1);
                            semester2 = semester1;
                        }
                    }

                    int courseListLen = courseList.size();
                    double creditCounter = 0;
                    double gpa = 0;
                    double semesterGpa = 0;
                    double semesterCgpa = 0;
                    double creditCounterforSemester = 0;
                    int semesterId = courseList.get(0).getSemesterId();

                    for (int i = 0; i < courseListLen; i++) {
                        String courseGrade = courseList.get(i).getGrade();
                        double courseCredits = courseList.get(i).getCredits();
                        int sId = courseList.get(i).getSemesterId();

                        for (int j = i - 1; j >= 0; j--) {
                            if (courseList.get(i).getCourseCode().equals(courseList.get(j).getCourseCode())) {
                                if (courseList.get(j).getGrade().equals("A+")) {
                                    gpa -= 4.00 * courseList.get(j).getCredits();
                                    creditCounter -= courseList.get(j).getCredits();
                                } else if (courseList.get(j).getGrade().equals("A")) {
                                    gpa -= 3.75 * courseList.get(j).getCredits();
                                    creditCounter -= courseList.get(j).getCredits();
                                } else if (courseList.get(j).getGrade().equals("A-")) {
                                    gpa -= 3.50 * courseList.get(j).getCredits();
                                    creditCounter -= courseList.get(j).getCredits();
                                } else if (courseList.get(j).getGrade().equals("B+")) {
                                    gpa -= 3.25 * courseList.get(j).getCredits();
                                    creditCounter -= courseList.get(j).getCredits();
                                } else if (courseList.get(j).getGrade().equals("B")) {
                                    gpa -= 3.00 * courseList.get(j).getCredits();
                                    creditCounter -= courseList.get(j).getCredits();
                                } else if (courseList.get(j).getGrade().equals("B-")) {
                                    gpa -= 2.75 * courseList.get(j).getCredits();
                                    creditCounter -= courseList.get(j).getCredits();
                                } else if (courseList.get(j).getGrade().equals("C+")) {
                                    gpa -= 2.50 * courseList.get(j).getCredits();
                                    creditCounter -= courseList.get(j).getCredits();
                                } else if (courseList.get(j).getGrade().equals("C")) {
                                    gpa -= 2.25 * courseList.get(j).getCredits();
                                    creditCounter -= courseList.get(j).getCredits();
                                } else if (courseList.get(j).getGrade().equals("D")) {
                                    gpa -= 2.00 * courseList.get(j).getCredits();
                                    creditCounter -= courseList.get(j).getCredits();
                                } else if (courseList.get(j).getGrade().equals("F")) {
                                    gpa -= 0.00 * courseList.get(j).getCredits();
                                    creditCounter -= courseList.get(j).getCredits();
                                }
                                break;
                            }
                        }

                        if (courseGrade.equals("A+")) {
                            gpa += 4.00 * courseCredits;
                            creditCounterforSemester += courseCredits;
                            creditCounter += courseCredits;
                        } else if (courseGrade.equals("A")) {
                            creditCounterforSemester += courseCredits;
                            gpa += 3.75 * courseCredits;
                            creditCounter += courseCredits;
                        } else if (courseGrade.equals("A-")) {
                            creditCounterforSemester += courseCredits;
                            gpa += 3.50 * courseCredits;
                            creditCounter += courseCredits;
                        } else if (courseGrade.equals("B+")) {
                            creditCounterforSemester += courseCredits;
                            gpa += 3.25 * courseCredits;
                            creditCounter += courseCredits;
                        } else if (courseGrade.equals("B")) {
                            creditCounterforSemester += courseCredits;
                            gpa += 3.00 * courseCredits;
                            creditCounter += courseCredits;
                        } else if (courseGrade.equals("B-")) {
                            creditCounterforSemester += courseCredits;
                            gpa += 2.75 * courseCredits;
                            creditCounter += courseCredits;
                        } else if (courseGrade.equals("C+")) {
                            creditCounterforSemester += courseCredits;
                            gpa += 2.50 * courseCredits;
                            creditCounter += courseCredits;
                        } else if (courseGrade.equals("C")) {
                            creditCounterforSemester += courseCredits;
                            gpa += 2.25 * courseCredits;
                            creditCounter += courseCredits;
                        } else if (courseGrade.equals("D")) {
                            creditCounterforSemester += courseCredits;
                            gpa += 2.00 * courseCredits;
                            creditCounter += courseCredits;
                        } else if (courseGrade.equals("F")) {
                            creditCounterforSemester += courseCredits;
                            gpa += 0.00 * courseCredits;
                            creditCounter += courseCredits;
                        }

                        if (semesterId != sId || courseListLen - 1 == i) {
                            semesterGpa = (gpa - (semesterCgpa * (creditCounter - creditCounterforSemester))) / creditCounterforSemester;
                            semesterCgpa = gpa / creditCounter;

                            cgpaList.add(new CGPA(semesterId, semesterGpa, semesterCgpa, creditCounter));
                            semesterId = courseList.get(i).getSemesterId();
                            creditCounterforSemester = 0;
                        }

                    }

                }

            } else {
                massegeLabel.setText("This ID not a student of SEU");

            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        if (tableViewOk) {
            currentPositionTableView.setItems(courseList);
            currentPositionSemesterColumn.setCellValueFactory(Data -> new SimpleIntegerProperty(Data.getValue().getSemesterId()));
            currentPositionCourseCodeColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getCourseCode()));
            currentPositionCourseTitleColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getCourseTitle()));
            currentPositionCreditsColumn.setCellValueFactory(Data -> new SimpleDoubleProperty(Data.getValue().getCredits()));
            currentPositionGradeColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getGrade()));
            semesterIdChooser.setItems(semesterIdList);
            String output = String.format("%.3f", cgpaList.get(cgpaList.size() - 1).getSemesterCgpa());
            cgpaLabel.setText(output);
            creditsAttemptedLabel.setText("" + cgpaList.get(cgpaList.size() - 1).getSemesterCreitsH());
        }

    }

    @FXML
    private void handlePredictionGPAbySemesterAction(ActionEvent event) {
        int semesterIdForPrediction = semesterIdChooser.getSelectionModel().getSelectedItem();
        System.out.println(semesterIdForPrediction);
        try {
            Statement statement = connection.createStatement();
            String query = "select * from registration where studentId = '" + studentId + "' and semesterId = '" + semesterIdForPrediction + "';";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String courseCode;
                String courseTitle = "";
                String facultyInitials;
                double credits = 0;
                String realGrade = "";
                String predictGrade = "";
                ArrayList<Regression> regression = new ArrayList<>();
                courseCode = resultSet.getString("courseCode");
                facultyInitials = resultSet.getString("facultyInitials");
                System.out.println(courseCode + "   " + facultyInitials);
                Statement statement1 = connection.createStatement();
                String query1 = "select * from course where courseCode = '" + courseCode + "';";
                ResultSet resultSet1 = statement1.executeQuery(query1);
                if (resultSet1.next()) {
                    courseTitle = resultSet1.getString("courseTitle");
                    credits = resultSet1.getDouble("credits");
                }
                System.out.println(courseTitle + "   " + credits);

                Statement statement2 = connection.createStatement();
                String query2 = "select * from grades where courseCode = '" + courseCode + "' and studentId = '"
                        + studentId + "' and semesterId = '" + semesterIdForPrediction + "';";
                ResultSet resultSet2 = statement2.executeQuery(query2);
                if (resultSet2.next()) {
                    realGrade = resultSet2.getString("grade");
                    if (!realGrade.equals("A+") && !realGrade.equals("A") && !realGrade.equals("A-")
                            && !realGrade.equals("B+") && !realGrade.equals("B") && !realGrade.equals("B-")
                            && !realGrade.equals("C+") && !realGrade.equals("C") && !realGrade.equals("D") && !realGrade.equals("F")) {

                        realGrade = "";
                    }

                }

                System.out.println(realGrade);

                Statement statement3 = connection.createStatement();

                String query3 = "select * from grades where semesterId = " + (semesterIdForPrediction - 1)
                        + " and facultyInitials = '" + facultyInitials + "' and courseCode = '" + courseCode + "';";
                ResultSet resultSet3 = statement3.executeQuery(query3);
                boolean Fcheck = false;
                boolean F1check = false;
                while (resultSet3.next()) {
                    Fcheck = true;
                    F1check = true;
                    String studentIdFR;
                    String gradeFR;
                    double gpaFR = 0;
                    double cgpaFR = 0;
                    double totalCreditsFR = 0;
                    studentIdFR = resultSet3.getString("studentId");
                    gradeFR = resultSet3.getString("grade");
                    if (!gradeFR.equals("A+") && !gradeFR.equals("A") && !gradeFR.equals("A-")
                            && !gradeFR.equals("B+") && !gradeFR.equals("B") && !gradeFR.equals("B-")
                            && !gradeFR.equals("C+") && !gradeFR.equals("C") && !gradeFR.equals("D") && !gradeFR.equals("F")) {
                        continue;
                    } else {
                        Statement statement4 = connection.createStatement();
                        String query4 = "select * from grades where studentId = '" + studentIdFR + "';";
                        ResultSet resultSet4 = statement4.executeQuery(query4);
                        ArrayList<Course> tempCourse = new ArrayList<>();
//                        ArrayList<CGPA> tempCgpa = new ArrayList<>();
                        while (resultSet4.next()) {
                            int semesterIdCheck = resultSet4.getInt("semesterId");
                            if (semesterIdCheck == semesterIdForPrediction) {
                                break;
                            }
                            String courseCodePS = resultSet4.getString("courseCode");
                            String gradePS = resultSet4.getString("grade");
                            double creditsPS = 0;
                            Statement statement5 = connection.createStatement();
                            String query5 = "select * from course where courseCode = '" + courseCode + "';";
                            ResultSet resultSet5 = statement5.executeQuery(query5);
                            while (resultSet5.next()) {
                                creditsPS = resultSet5.getDouble("credits");
                            }
                            tempCourse.add(new Course(courseCodePS, creditsPS, gradePS));
                        }
                        int tempSize = tempCourse.size();
                        for (int i = 0; i < tempSize; i++) {
                            String courseCodePS = tempCourse.get(i).getCourseCode();
                            String gradePS = tempCourse.get(i).getGrade();
                            double creditsPS = tempCourse.get(i).getCredits();

                            for (int j = i - 1; j >= 0; j--) {
                                String courseCodePF = tempCourse.get(j).getCourseCode();
                                String gradePF = tempCourse.get(j).getGrade();
                                double creditsPF = tempCourse.get(j).getCredits();
                                if (courseCodePS.equals(courseCodePF)) {
                                    if (gradePF.equals("A+")) {
                                        cgpaFR -= 4.00 * creditsPF;
                                        totalCreditsFR -= creditsPF;
                                    } else if (gradePF.equals("A")) {
                                        cgpaFR -= 3.75 * creditsPF;
                                        totalCreditsFR -= creditsPF;
                                    } else if (gradePF.equals("A-")) {
                                        cgpaFR -= 3.50 * creditsPF;
                                        totalCreditsFR -= creditsPF;
                                    } else if (gradePF.equals("B+")) {
                                        cgpaFR -= 3.25 * creditsPF;
                                        totalCreditsFR -= creditsPF;
                                    } else if (gradePF.equals("B")) {
                                        cgpaFR -= 3.0 * creditsPF;
                                        totalCreditsFR -= creditsPF;
                                    } else if (gradePF.equals("B-")) {
                                        cgpaFR -= 2.75 * creditsPF;
                                        totalCreditsFR -= creditsPF;
                                    } else if (gradePF.equals("C+")) {
                                        cgpaFR -= 2.50 * creditsPF;
                                        totalCreditsFR -= creditsPF;
                                    } else if (gradePF.equals("C")) {
                                        cgpaFR -= 2.25 * creditsPF;
                                        totalCreditsFR -= creditsPF;
                                    } else if (gradePF.equals("D")) {
                                        cgpaFR -= 2.0 * creditsPF;
                                        totalCreditsFR -= creditsPF;
                                    } else if (gradePF.equals("F")) {
                                        cgpaFR -= 0 * creditsPF;
                                        totalCreditsFR -= creditsPF;
                                    }
                                    break;
                                }
                            }

                            if (gradePS.equals("A+")) {
                                cgpaFR += 4.00 * creditsPS;
                                totalCreditsFR += creditsPS;
                            } else if (gradePS.equals("A")) {
                                cgpaFR += 3.75 * creditsPS;
                                totalCreditsFR += creditsPS;
                            } else if (gradePS.equals("A-")) {
                                cgpaFR += 3.50 * creditsPS;
                                totalCreditsFR += creditsPS;
                            } else if (gradePS.equals("B+")) {
                                cgpaFR += 3.25 * creditsPS;
                                totalCreditsFR += creditsPS;
                            } else if (gradePS.equals("B")) {
                                cgpaFR += 3.0 * creditsPS;
                                totalCreditsFR += creditsPS;
                            } else if (gradePS.equals("B-")) {
                                cgpaFR += 2.75 * creditsPS;
                                totalCreditsFR += creditsPS;
                            } else if (gradePS.equals("C+")) {
                                cgpaFR += 2.50 * creditsPS;
                                totalCreditsFR += creditsPS;
                            } else if (gradePS.equals("C")) {
                                cgpaFR += 2.25 * creditsPS;
                                totalCreditsFR += creditsPS;
                            } else if (gradePS.equals("D")) {
                                cgpaFR += 2.0 * creditsPS;
                                totalCreditsFR += creditsPS;
                            } else if (gradePS.equals("F")) {
                                cgpaFR += 0 * creditsPS;
                                totalCreditsFR += creditsPS;
                            }

                        }

                    }
                    if (gradeFR.equals("A+")) {
                        gpaFR = 4.00;
                    } else if (gradeFR.equals("A")) {
                        gpaFR = 3.75;
                    } else if (gradeFR.equals("A-")) {
                        gpaFR = 3.50;
                    } else if (gradeFR.equals("B+")) {
                        gpaFR = 3.25;
                    } else if (gradeFR.equals("B")) {
                        gpaFR = 3.0;
                    } else if (gradeFR.equals("B-")) {
                        gpaFR = 2.75;
                    } else if (gradeFR.equals("C+")) {
                        gpaFR = 2.50;
                    } else if (gradeFR.equals("C")) {
                        gpaFR = 2.25;
                    } else if (gradeFR.equals("D")) {
                        gpaFR = 2.0;
                    } else if (gradeFR.equals("F")) {
                        gpaFR = 0;
                    }
                    cgpaFR = cgpaFR / totalCreditsFR;
//                    System.out.println(cgpaFR);
//                    
//                    System.out.println(gpaFR);
                    if(!Double.isNaN(gpaFR) && !Double.isNaN(cgpaFR))
                    regression.add(new Regression(cgpaFR, gpaFR, cgpaFR * gpaFR, cgpaFR * cgpaFR));
                }
                boolean F2check = false;
                if (!F1check) {
                    Statement statement7 = connection.createStatement();

                    String query7 = "select * from grades where facultyInitials = '" + facultyInitials + "' and courseCode = '" + courseCode + "';";
                    ResultSet resultSet7 = statement7.executeQuery(query7);
                    while (resultSet7.next()) {
                        int semesterIdF2 = resultSet7.getInt("semesterId");
                        if (semesterIdF2 == (semesterIdForPrediction - 1)) {
                            break;
                        }
                        F2check = true;
                        Fcheck = true;
                        String studentIdFR;
                        String gradeFR;
                        double gpaFR = 0;
                        double cgpaFR = 0;
                        double totalCreditsFR = 0;
                        studentIdFR = resultSet7.getString("studentId");
                        gradeFR = resultSet7.getString("grade");
                        if (!gradeFR.equals("A+") && !gradeFR.equals("A") && !gradeFR.equals("A-")
                                && !gradeFR.equals("B+") && !gradeFR.equals("B") && !gradeFR.equals("B-")
                                && !gradeFR.equals("C+") && !gradeFR.equals("C") && !gradeFR.equals("D") && !gradeFR.equals("F")) {
                            continue;
                        } else {
                            Statement statement4 = connection.createStatement();
                            String query4 = "select * from grades where studentId = '" + studentIdFR + "';";
                            ResultSet resultSet4 = statement4.executeQuery(query4);
                            ArrayList<Course> tempCourse = new ArrayList<>();
//                        ArrayList<CGPA> tempCgpa = new ArrayList<>();
                            while (resultSet4.next()) {
                                int semesterIdCheck = resultSet4.getInt("semesterId");
                                if (semesterIdCheck == semesterIdForPrediction) {
                                    break;
                                }
                                String courseCodePS = resultSet4.getString("courseCode");
                                String gradePS = resultSet4.getString("grade");
                                double creditsPS = 0;
                                Statement statement5 = connection.createStatement();
                                String query5 = "select * from course where courseCode = '" + courseCode + "';";
                                ResultSet resultSet5 = statement5.executeQuery(query5);
                                while (resultSet5.next()) {
                                    creditsPS = resultSet5.getDouble("credits");
                                }
                                tempCourse.add(new Course(courseCodePS, creditsPS, gradePS));
                            }
                            int tempSize = tempCourse.size();
                            for (int i = 0; i < tempSize; i++) {
                                String courseCodePS = tempCourse.get(i).getCourseCode();
                                String gradePS = tempCourse.get(i).getGrade();
                                double creditsPS = tempCourse.get(i).getCredits();

                                for (int j = i - 1; j >= 0; j--) {
                                    String courseCodePF = tempCourse.get(j).getCourseCode();
                                    String gradePF = tempCourse.get(j).getGrade();
                                    double creditsPF = tempCourse.get(j).getCredits();
                                    if (courseCodePS.equals(courseCodePF)) {
                                        if (gradePF.equals("A+")) {
                                            cgpaFR -= 4.00 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("A")) {
                                            cgpaFR -= 3.75 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("A-")) {
                                            cgpaFR -= 3.50 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("B+")) {
                                            cgpaFR -= 3.25 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("B")) {
                                            cgpaFR -= 3.0 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("B-")) {
                                            cgpaFR -= 2.75 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("C+")) {
                                            cgpaFR -= 2.50 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("C")) {
                                            cgpaFR -= 2.25 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("D")) {
                                            cgpaFR -= 2.0 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("F")) {
                                            cgpaFR -= 0 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        }
                                        break;
                                    }
                                }

                                if (gradePS.equals("A+")) {
                                    cgpaFR += 4.00 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("A")) {
                                    cgpaFR += 3.75 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("A-")) {
                                    cgpaFR += 3.50 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("B+")) {
                                    cgpaFR += 3.25 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("B")) {
                                    cgpaFR += 3.0 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("B-")) {
                                    cgpaFR += 2.75 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("C+")) {
                                    cgpaFR += 2.50 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("C")) {
                                    cgpaFR += 2.25 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("D")) {
                                    cgpaFR += 2.0 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("F")) {
                                    cgpaFR += 0 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                }

                            }

                        }
                        if (gradeFR.equals("A+")) {
                            gpaFR = 4.00;
                        } else if (gradeFR.equals("A")) {
                            gpaFR = 3.75;
                        } else if (gradeFR.equals("A-")) {
                            gpaFR = 3.50;
                        } else if (gradeFR.equals("B+")) {
                            gpaFR = 3.25;
                        } else if (gradeFR.equals("B")) {
                            gpaFR = 3.0;
                        } else if (gradeFR.equals("B-")) {
                            gpaFR = 2.75;
                        } else if (gradeFR.equals("C+")) {
                            gpaFR = 2.50;
                        } else if (gradeFR.equals("C")) {
                            gpaFR = 2.25;
                        } else if (gradeFR.equals("D")) {
                            gpaFR = 2.0;
                        } else if (gradeFR.equals("F")) {
                            gpaFR = 0;
                        }
                        cgpaFR = cgpaFR / totalCreditsFR;

                    if(!Double.isNaN(gpaFR) && !Double.isNaN(cgpaFR))
                        regression.add(new Regression(cgpaFR, gpaFR, cgpaFR * gpaFR, cgpaFR * cgpaFR));
                    }
                }
                boolean F3check = false;
                if (!F2check) {
                    Statement statement7 = connection.createStatement();

                    String query7 = "select * from grades where facultyInitials = '" + facultyInitials + "' and courseCode = '" + courseCode + "';";
                    ResultSet resultSet7 = statement7.executeQuery(query7);
                    while (resultSet7.next()) {
                        int semesterIdF2 = resultSet7.getInt("semesterId");
                        if (semesterIdF2 == (semesterIdForPrediction - 1)) {
                            break;
                        }
                        F3check = true;
                        Fcheck = true;
                        String studentIdFR;
                        String gradeFR;
                        double gpaFR = 0;
                        double cgpaFR = 0;
                        double totalCreditsFR = 0;
                        studentIdFR = resultSet7.getString("studentId");
                        gradeFR = resultSet7.getString("grade");
                        if (!gradeFR.equals("A+") && !gradeFR.equals("A") && !gradeFR.equals("A-")
                                && !gradeFR.equals("B+") && !gradeFR.equals("B") && !gradeFR.equals("B-")
                                && !gradeFR.equals("C+") && !gradeFR.equals("C") && !gradeFR.equals("D") && !gradeFR.equals("F")) {
                            continue;
                        } else {
                            Statement statement4 = connection.createStatement();
                            String query4 = "select * from grades where studentId = '" + studentIdFR + "';";
                            ResultSet resultSet4 = statement4.executeQuery(query4);
                            ArrayList<Course> tempCourse = new ArrayList<>();
//                        ArrayList<CGPA> tempCgpa = new ArrayList<>();
                            while (resultSet4.next()) {
                                int semesterIdCheck = resultSet4.getInt("semesterId");
                                if (semesterIdCheck == semesterIdForPrediction) {
                                    break;
                                }
                                String courseCodePS = resultSet4.getString("courseCode");
                                String gradePS = resultSet4.getString("grade");
                                double creditsPS = 0;
                                Statement statement5 = connection.createStatement();
                                String query5 = "select * from course where courseCode = '" + courseCode + "';";
                                ResultSet resultSet5 = statement5.executeQuery(query5);
                                while (resultSet5.next()) {
                                    creditsPS = resultSet5.getDouble("credits");
                                }
                                tempCourse.add(new Course(courseCodePS, creditsPS, gradePS));
                            }
                            int tempSize = tempCourse.size();
                            for (int i = 0; i < tempSize; i++) {
                                String courseCodePS = tempCourse.get(i).getCourseCode();
                                String gradePS = tempCourse.get(i).getGrade();
                                double creditsPS = tempCourse.get(i).getCredits();

                                for (int j = i - 1; j >= 0; j--) {
                                    String courseCodePF = tempCourse.get(j).getCourseCode();
                                    String gradePF = tempCourse.get(j).getGrade();
                                    double creditsPF = tempCourse.get(j).getCredits();
                                    if (courseCodePS.equals(courseCodePF)) {
                                        if (gradePF.equals("A+")) {
                                            cgpaFR -= 4.00 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("A")) {
                                            cgpaFR -= 3.75 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("A-")) {
                                            cgpaFR -= 3.50 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("B+")) {
                                            cgpaFR -= 3.25 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("B")) {
                                            cgpaFR -= 3.0 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("B-")) {
                                            cgpaFR -= 2.75 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("C+")) {
                                            cgpaFR -= 2.50 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("C")) {
                                            cgpaFR -= 2.25 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("D")) {
                                            cgpaFR -= 2.0 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("F")) {
                                            cgpaFR -= 0 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        }
                                        break;
                                    }
                                }

                                if (gradePS.equals("A+")) {
                                    cgpaFR += 4.00 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("A")) {
                                    cgpaFR += 3.75 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("A-")) {
                                    cgpaFR += 3.50 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("B+")) {
                                    cgpaFR += 3.25 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("B")) {
                                    cgpaFR += 3.0 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("B-")) {
                                    cgpaFR += 2.75 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("C+")) {
                                    cgpaFR += 2.50 * creditsPS;

                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("C")) {
                                    cgpaFR += 2.25 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("D")) {
                                    cgpaFR += 2.0 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("F")) {
                                    cgpaFR += 0 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                }

                            }

                        }
                        if (gradeFR.equals("A+")) {
                            gpaFR = 4.00;
                        } else if (gradeFR.equals("A")) {
                            gpaFR = 3.75;
                        } else if (gradeFR.equals("A-")) {
                            gpaFR = 3.50;
                        } else if (gradeFR.equals("B+")) {
                            gpaFR = 3.25;
                        } else if (gradeFR.equals("B")) {
                            gpaFR = 3.0;
                        } else if (gradeFR.equals("B-")) {
                            gpaFR = 2.75;
                        } else if (gradeFR.equals("C+")) {
                            gpaFR = 2.50;
                        } else if (gradeFR.equals("C")) {
                            gpaFR = 2.25;
                        } else if (gradeFR.equals("D")) {
                            gpaFR = 2.0;
                        } else if (gradeFR.equals("F")) {
                            gpaFR = 0;
                        }
                        cgpaFR = cgpaFR / totalCreditsFR;

                    if(!Double.isNaN(gpaFR) && !Double.isNaN(cgpaFR))
                        regression.add(new Regression(cgpaFR, gpaFR, cgpaFR * gpaFR, cgpaFR * cgpaFR));
                    }
                }

                boolean F4check = false;
                if (!F3check) {
                    Statement statement7 = connection.createStatement();

                    String query7 = "select * from grades where semesterId = " + (semesterIdForPrediction - 1) + " and courseCode = '" + courseCode + "';";
                    ResultSet resultSet7 = statement7.executeQuery(query7);
                    while (resultSet7.next()) {
                        int semesterIdF2 = resultSet7.getInt("semesterId");
                        if (semesterIdF2 == (semesterIdForPrediction - 1)) {
                            break;
                        }
                        F4check = true;
                        Fcheck = true;
                        String studentIdFR;
                        String gradeFR;
                        double gpaFR = 0;
                        double cgpaFR = 0;
                        double totalCreditsFR = 0;
                        studentIdFR = resultSet7.getString("studentId");
                        gradeFR = resultSet7.getString("grade");
                        if (!gradeFR.equals("A+") && !gradeFR.equals("A") && !gradeFR.equals("A-")
                                && !gradeFR.equals("B+") && !gradeFR.equals("B") && !gradeFR.equals("B-")
                                && !gradeFR.equals("C+") && !gradeFR.equals("C") && !gradeFR.equals("D") && !gradeFR.equals("F")) {
                            continue;
                        } else {
                            Statement statement4 = connection.createStatement();
                            String query4 = "select * from grades where studentId = '" + studentIdFR + "';";
                            ResultSet resultSet4 = statement4.executeQuery(query4);
                            ArrayList<Course> tempCourse = new ArrayList<>();
//                        ArrayList<CGPA> tempCgpa = new ArrayList<>();
                            while (resultSet4.next()) {
                                int semesterIdCheck = resultSet4.getInt("semesterId");
                                if (semesterIdCheck == semesterIdForPrediction) {
                                    break;
                                }
                                String courseCodePS = resultSet4.getString("courseCode");
                                String gradePS = resultSet4.getString("grade");
                                double creditsPS = 0;
                                Statement statement5 = connection.createStatement();
                                String query5 = "select * from course where courseCode = '" + courseCode + "';";
                                ResultSet resultSet5 = statement5.executeQuery(query5);
                                while (resultSet5.next()) {
                                    creditsPS = resultSet5.getDouble("credits");
                                }
                                tempCourse.add(new Course(courseCodePS, creditsPS, gradePS));
                            }
                            int tempSize = tempCourse.size();
                            for (int i = 0; i < tempSize; i++) {
                                String courseCodePS = tempCourse.get(i).getCourseCode();
                                String gradePS = tempCourse.get(i).getGrade();
                                double creditsPS = tempCourse.get(i).getCredits();

                                for (int j = i - 1; j >= 0; j--) {
                                    String courseCodePF = tempCourse.get(j).getCourseCode();
                                    String gradePF = tempCourse.get(j).getGrade();
                                    double creditsPF = tempCourse.get(j).getCredits();
                                    if (courseCodePS.equals(courseCodePF)) {
                                        if (gradePF.equals("A+")) {
                                            cgpaFR -= 4.00 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("A")) {
                                            cgpaFR -= 3.75 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("A-")) {
                                            cgpaFR -= 3.50 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("B+")) {
                                            cgpaFR -= 3.25 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("B")) {
                                            cgpaFR -= 3.0 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("B-")) {
                                            cgpaFR -= 2.75 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("C+")) {
                                            cgpaFR -= 2.50 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("C")) {
                                            cgpaFR -= 2.25 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("D")) {
                                            cgpaFR -= 2.0 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        } else if (gradePF.equals("F")) {
                                            cgpaFR -= 0 * creditsPF;
                                            totalCreditsFR -= creditsPF;
                                        }
                                        break;
                                    }
                                }

                                if (gradePS.equals("A+")) {
                                    cgpaFR += 4.00 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("A")) {
                                    cgpaFR += 3.75 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("A-")) {
                                    cgpaFR += 3.50 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("B+")) {
                                    cgpaFR += 3.25 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("B")) {
                                    cgpaFR += 3.0 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("B-")) {
                                    cgpaFR += 2.75 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("C+")) {
                                    cgpaFR += 2.50 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("C")) {
                                    cgpaFR += 2.25 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("D")) {
                                    cgpaFR += 2.0 * creditsPS;
                                    totalCreditsFR += creditsPS;
                                } else if (gradePS.equals("F")) {
                                    cgpaFR += 0* creditsPS;
                                    totalCreditsFR += creditsPS;
                                }

                            }

                        }
                        if (gradeFR.equals("A+")) {
                            gpaFR = 4.00;
                        } else if (gradeFR.equals("A")) {
                            gpaFR = 3.75;
                        } else if (gradeFR.equals("A-")) {
                            gpaFR = 3.50;
                        } else if (gradeFR.equals("B+")) {
                            gpaFR = 3.25;
                        } else if (gradeFR.equals("B")) {
                            gpaFR = 3.0;
                        } else if (gradeFR.equals("B-")) {
                            gpaFR = 2.75;
                        } else if (gradeFR.equals("C+")) {
                            gpaFR = 2.50;
                        } else if (gradeFR.equals("C")) {
                            gpaFR = 2.25;
                        } else if (gradeFR.equals("D")) {
                            gpaFR = 2.0;
                        } else if (gradeFR.equals("F")) {
                            gpaFR = 0;
                        }
                        cgpaFR = cgpaFR / totalCreditsFR;

                    if(!Double.isNaN(gpaFR) && !Double.isNaN(cgpaFR))
                        regression.add(new Regression(cgpaFR, gpaFR, cgpaFR * gpaFR, cgpaFR * cgpaFR));
                    }
                }

                if (!F4check) {
                    predictGrade = "B+";
                }

                if (Fcheck) {
                    int lenRegression = regression.size();

                    double Sx = 0;
                    double Sy = 0;
                    double Sxy = 0;
                    double SxSqr = 0;
                    for (int i = 0; i < lenRegression; i++) {
                        double x = regression.get(i).getX();
                        double y = regression.get(i).getY();
                        double xy = regression.get(i).getXy();
                        double xSqr = regression.get(i).getxSqr();
                        Sx += x;
                        Sy += y;
                        Sxy += xy;
                        SxSqr += xSqr;

                    }

                    double a1 = ((lenRegression * Sxy) - (Sx * Sy)) / ((lenRegression * SxSqr) - (Sx * Sx));
                    double a0 = (Sy / lenRegression) - (a1 * (Sx / lenRegression));
                    if(Double.isNaN(a0)){
                        a0 = 0;
                    }
                    if(Double.isNaN(a1)){
                        a1 = 0;
                    }
                    
                    double X = 3.40;
                    int cgpaListLen = cgpaList.size();
                    for (int i = 0; i < cgpaListLen; i++) {

                        if ((semesterIdForPrediction - 1) == cgpaList.get(i).getSemesterId()) {
                            X = cgpaList.get(i).getSemesterCgpa();
                            break;
                        }

                    }
                    
                    double Y = a0+( a1*X);
                    

                    
                    if(Y>3.99)predictGrade = "A+";
                    else if(Y>3.74)predictGrade = "A";
                    else if(Y>3.49)predictGrade = "A-";
                    else if(Y>3.24)predictGrade = "B+";
                    else if(Y>2.99)predictGrade = "B";
                    else if(Y>2.74)predictGrade = "B-";
                    else if(Y>2.49)predictGrade = "C+";
                    else if(Y>2.24)predictGrade = "C";
                    else if(Y>1.99)predictGrade = "D";
                    else predictGrade = "F";
                    
                }

                predictInfoList.add(new PredictInfo(courseCode, courseTitle, credits, realGrade, predictGrade));


            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        predictorTableView.setItems(predictInfoList);
    
    predictorCourseCodeTableColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getCourseCode()));
    predictorCourseTitleTableColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getCourseTitle()));
    predictorCreditsTableColumn.setCellValueFactory(Data -> new SimpleDoubleProperty(Data.getValue().getCredits()));
    predictorRealGradeTableColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getRealGrade()));
    predictorPredictGradeTableColumn.setCellValueFactory(Data -> new SimpleStringProperty(Data.getValue().getPredictGrade()));
    
    int predictInfoListLen = predictInfoList.size();
    for(int i=0; i<predictInfoListLen; i++){
        
    }
        
//        System.out.print("Rian");
    }


}

