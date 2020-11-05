package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerWindSignIn {
    Connection connection;

    public ControllerWindSignIn() throws ClassNotFoundException {
        connection = ConnectionUtil.connectionDB();
    }

    @FXML
    private TextField txtLoginSignIn;
    @FXML
    private PasswordField txtPassSignIn;
    @FXML
    private Label txtStatusSignIn;
    @FXML
    private Button buttonIn1ToWind3;
    @FXML
    private Button buttonReg1ToWind2;
    String login;
    String password;

    @FXML
    public void pressIn(ActionEvent event) throws SQLException {
        String sign = "SELECT login, password FROM users WHERE login=? and password=?";

        PreparedStatement statement = connection.prepareStatement(sign);
        statement.setString(1, txtLoginSignIn.getText());
        statement.setString(2, txtPassSignIn.getText());
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            login = result.getString("login");
            password = result.getString("password");
        }
        if (txtLoginSignIn.getText().equals(login) && txtPassSignIn.getText().equals(password)) {
            txtStatusSignIn.setText("Successfully");
            buttonIn1ToWind3.setOnKeyPressed(keyEvent -> {
                buttonIn1ToWind3.getScene().getWindow().hide();
                if (keyEvent.getCode().toString().equals("ENTER")) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/WindowView.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Show tables in database");
                        stage.setScene(new Scene(root));
                        stage.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            buttonIn1ToWind3.setOnAction(event1 -> {
                buttonIn1ToWind3.getScene().getWindow().hide();
                if (txtLoginSignIn.getText().equals(login) && txtPassSignIn.getText().equals(password)) {
                    txtStatusSignIn.setText("Successfully");
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/WindowView.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Show tables in database");
                        stage.setScene(new Scene(root));
                        stage.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    @FXML
    void initialize() {
        buttonReg1ToWind2.setOnAction(event -> {
            buttonReg1ToWind2.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/FXML/WindowRegistration.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage = new Stage();
            stage.setTitle("Registration, please");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
}