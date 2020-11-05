package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectionUtil;
import sample.model.ModelTableCourses;
import sample.model.ModelTableLessons;
import sample.model.ModelTableTeachers;
import sample.model.ModelTableUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    Connection connection = null;

    public Controller() throws ClassNotFoundException {
        connection = ConnectionUtil.connectionDB();
    }

    //COURSES
    @FXML
    private TableView<ModelTableCourses> tableCourses;

    @FXML
    private TableColumn<ModelTableCourses, String> tableCoursesID;
    @FXML
    private TableColumn<ModelTableCourses, String> tableCoursesTitle;
    @FXML
    private TableColumn<ModelTableCourses, String> tableCoursesLength;
    @FXML
    private TableColumn<ModelTableCourses, String> tableCoursesDescription;

    //LESSONS
    @FXML
    private TableView<ModelTableLessons> tableLessons;

    @FXML
    private TableColumn<ModelTableLessons, String> tableLessonsID;
    @FXML
    private TableColumn<ModelTableLessons, String> tableLessonsTeacher;
    @FXML
    private TableColumn<ModelTableLessons, String> tableLessonsCourse;
    @FXML
    private TableColumn<ModelTableLessons, String> tableLessonsRoom;
    @FXML
    private TableColumn<ModelTableLessons, String> tableLessonsDate;

    //TEACHERS
    @FXML
    private TableView<ModelTableTeachers> tableTeachers;

    @FXML
    private TableColumn<ModelTableTeachers, String> tableTeachersID;
    @FXML
    private TableColumn<ModelTableTeachers, String> tableTeachersName;
    @FXML
    private TableColumn<ModelTableTeachers, String> tableTeachersAddr;
    @FXML
    private TableColumn<ModelTableTeachers, String> tableTeachersPhone;

    //USERS
    @FXML
    private TableView<ModelTableUsers> tableUsers;

    @FXML
    private TableColumn<ModelTableTeachers, String> tableUsersID;
    @FXML
    private TableColumn<ModelTableTeachers, String> tableUsersFirstName;
    @FXML
    private TableColumn<ModelTableTeachers, String> tableUsersLastName;
    @FXML
    private TableColumn<ModelTableTeachers, String> tableUsersEmail;
    @FXML
    private TableColumn<ModelTableTeachers, String> tableUsersLogin;
    @FXML
    private TableColumn<ModelTableTeachers, String> tableUsersPassword;

    @FXML
    public void initialize() {
        //Courses
        ObservableList<ModelTableCourses> listTableCourses = FXCollections.observableArrayList();

        String sqlTableCourses = "SELECT * FROM courses";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlTableCourses);
            ResultSet resultTableCourses = statement.executeQuery();
            while (resultTableCourses.next()) {

                listTableCourses.add(new ModelTableCourses(
                        resultTableCourses.getString(1),
                        resultTableCourses.getString(2),
                        resultTableCourses.getString(3),
                        resultTableCourses.getString(4)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        tableCoursesID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCoursesTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableCoursesLength.setCellValueFactory(new PropertyValueFactory<>("length"));
        tableCoursesDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableCourses.setItems(listTableCourses);

        //Lessons
        ObservableList<ModelTableLessons> listTableLessons = FXCollections.observableArrayList();

        String sqlTableLessons = "SELECT * FROM lessons";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlTableLessons);
            ResultSet resultTableLessons = statement.executeQuery();
            while (resultTableLessons.next()) {
                listTableLessons.add(new ModelTableLessons(
                        resultTableLessons.getString(1),
                        resultTableLessons.getString(2),
                        resultTableLessons.getString(3),
                        resultTableLessons.getString(4),
                        resultTableLessons.getString(5)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        tableLessonsID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableLessonsTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        tableLessonsCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        tableLessonsRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
        tableLessonsDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableLessons.setItems(listTableLessons);

        //Teachers
        ObservableList<ModelTableTeachers> listTableTeachers = FXCollections.observableArrayList();

        String sqlTableTeachers = "SELECT * FROM teachers";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlTableTeachers);
            ResultSet resultTableTeachers = statement.executeQuery();
            while (resultTableTeachers.next()) {
                listTableTeachers.add(new ModelTableTeachers(
                        resultTableTeachers.getString(1),
                        resultTableTeachers.getString(2),
                        resultTableTeachers.getString(3),
                        resultTableTeachers.getString(4)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        tableTeachersID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableTeachersName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableTeachersAddr.setCellValueFactory(new PropertyValueFactory<>("addr"));
        tableTeachersPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableTeachers.setItems(listTableTeachers);

        //Users
        ObservableList<ModelTableUsers> listTableUsers = FXCollections.observableArrayList();

        String sqlTableUsers = "SELECT * FROM users";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlTableUsers);
            ResultSet resultTableUsers = statement.executeQuery();
            while (resultTableUsers.next()) {
                listTableUsers.add(new ModelTableUsers(
                        resultTableUsers.getString(1),
                        resultTableUsers.getString(2),
                        resultTableUsers.getString(3),
                        resultTableUsers.getString(4),
                        resultTableUsers.getString(5),
                        resultTableUsers.getString(6)));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        tableUsersID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableUsersFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tableUsersLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableUsersEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableUsersLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        tableUsersPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        tableUsers.setItems(listTableUsers);
    }
}