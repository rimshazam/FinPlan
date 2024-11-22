package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class DashboardController {
	private Stage stage;
    private Scene scene;
    
    
    @FXML
    private Label userText;
    
    @FXML
    private ComboBox<String> userType;
    
    @FXML
    private Button transacBtn;  // Button to redirect to the transaction page
    
    @FXML
    private TableView<?> incomeTable;

    @FXML
    private TableView<?> expenseTable;

    @FXML
    void selectUser(ActionEvent event) {
        if (userType != null) {
            String selectedReport = userType.getSelectionModel().getSelectedItem();
            if (selectedReport != null && userText != null) {
                userText.setText(selectedReport);
            }
        }
    }

    @FXML
    private void initialize() {
        // Initialize ComboBox with user roles
        if (userType != null) {
            ObservableList<String> list = FXCollections.observableArrayList("Student", "Admin");
            userType.setItems(list);
        }

        // Set default welcome text
        if (userText != null) {
            userText.setText("Welcome");
        }
    }

    // Function to handle transaction button click
    @FXML
    void goToTransactionPage(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Transaction.fxml"));
        stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
   
}
