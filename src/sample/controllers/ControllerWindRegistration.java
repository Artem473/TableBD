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

public class ControllerWindRegistration {
    Connection connection;

    public ControllerWindRegistration() throws ClassNotFoundException {
        connection = ConnectionUtil.connectionDB();
    }

    @FXML
    private TextField txtNameReg;
    @FXML
    private TextField txtLastnameReg;
    @FXML
    private TextField txtEmailReg;
    @FXML
    private TextField txtLoginReg;
    @FXML
    private PasswordField txtPassReg;
    @FXML
    private Label txtStatusReg;
    @FXML
    private Button buttonIn2ToWind1;
    String email;
    String login;

    @FXML
    void initialize() {
        buttonIn2ToWind1.setOnAction(event -> {
            buttonIn2ToWind1.getScene().getWindow().hide();

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/FXML/WindowSignIn.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Sign In, please.");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    @FXML
    public void pressRegBD(ActionEvent event) throws SQLException {
        String sql = "SELECT email, login FROM users WHERE email=? and login=?";

        PreparedStatement statement1 = connection.prepareStatement(sql);
        statement1.setString(1, txtEmailReg.getText());
        statement1.setString(2, txtLoginReg.getText());

        ResultSet result1 = statement1.executeQuery();

        while (result1.next()) {
            email = result1.getString("email");
            login = result1.getString("login");
        }
        if (!(txtEmailReg.getText().equals(email) && txtLoginReg.getText().equals(login))) {
            String Insert = "INSERT INTO users(firstName, lastName, email, login, password) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(Insert);
            statement.setString(1, txtNameReg.getText());
            statement.setString(2, txtLastnameReg.getText());
            statement.setString(3, txtEmailReg.getText());
            statement.setString(4, txtLoginReg.getText());
            statement.setString(5, txtPassReg.getText());

            int result = statement.executeUpdate();

            if (result == 1) {
                txtStatusReg.setText("Registration was successful");
            }
        }else txtStatusReg.setText("Введенные логин и/или пароль уже используеться  ");

    }
}