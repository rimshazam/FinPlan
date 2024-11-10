package com.example.finplan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class FinPlan extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FinPlan.class.getResource("finplan-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("FinPlan System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://127.0.0.1:3306/finplan";
        String username = "root";
        String password = "mysql7890";
Connection connection = DriverManager.getConnection(url, username,password);
Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Persons;");
        while(rs.next())
        {
            System.out.println(rs.getString("PersonID"));
            System.out.println(rs.getString("FName"));
            System.out.println(rs.getString("Age"));
        }
        launch();


    }
}