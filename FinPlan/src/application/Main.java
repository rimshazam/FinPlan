package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Transaction.fxml"));
            Scene scene = new Scene(root);

            String css= this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);

            primaryStage.setTitle("Finance Management System");
            primaryStage.setMinHeight(450);
            primaryStage.setMinWidth(600);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Prints the stack trace to help identify the issue
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
