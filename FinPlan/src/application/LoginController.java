package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Alert alert;

    // Use the PersistenceHandler
    private PersistenceHandler persistenceHandler;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passs;

    @FXML
    private TextField userr;

    public LoginController() {
        // Initialize the PersistenceHandler
        this.persistenceHandler = new PersistenceHandler();
    }

    @FXML
    void createAcc(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterPage.fxml"));
        stage = (Stage) ((javafx.scene.Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logBtn(ActionEvent event) throws IOException {
        System.out.println("Login button pressed!");

        // Debugging to check what is being returned by getText()
        System.out.println("Username: '" + userr.getText() + "'"); // Print the username value
        System.out.println("Password: '" + passs.getText() + "'"); // Print the password value

        // Trim the text to remove leading/trailing spaces and check if it's empty
        if (userr.getText().trim().isEmpty() || passs.getText().trim().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the blank fields.");
            alert.showAndWait();
        } else {
            // The query to check user credentials
            String selectData = "SELECT username, password FROM Users WHERE username=? and password=?";

            try {
                // Use the PersistenceHandler to execute the query
                ResultSet result = persistenceHandler.executeQuery(selectData, userr.getText(), passs.getText());

                if (result.next()) {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("You've been successfully logged in");
                    alert.showAndWait();

                    // Load the dashboard screen
                    Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                    stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect username/password. Please enter a valid username/password.");
                    alert.showAndWait();
                }

            } catch (SQLException e) {
                e.printStackTrace();
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while accessing the database. Please try again.");
                alert.showAndWait();
            }
        }
    }
}
