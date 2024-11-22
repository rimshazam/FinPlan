package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransactionController {
	
	private Stage stage;
    private Scene scene;
    private Alert alert;
	
	@FXML
    private Button goback;

    @FXML
    private ComboBox<String> accountdd;

    @FXML
    private TextField amt;

    @FXML
    private TextField cat;

    @FXML
    private Button clear;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton exprb;

    @FXML
    private TextField famax;

    @FXML
    private TextField famin;

    @FXML
    private TextField fcat;

    @FXML
    private DatePicker fedate;

    @FXML
    private Button filter;

    @FXML
    private DatePicker fsdate;

    @FXML
    private RadioButton incrb;

    @FXML
    private Button savett;

    @FXML
    private TableView<?> tranTable;

    @FXML
    private Label userText;
    
    private PersistenceHandler persistenceHandler;

    public TransactionController() {
        this.persistenceHandler = new PersistenceHandler();
    }

    @FXML
    public void saveTransaction(ActionEvent event) {
        String category = cat.getText();
        String transactionDate = date.getValue() != null ? date.getValue().toString() : "";
        String account = accountdd.getValue();
        String amountStr = amt.getText();

        String transactionTypeStr = null;
        if (incrb.isSelected()) {
            transactionTypeStr = "Income";
        } else if (exprb.isSelected()) {
            transactionTypeStr = "Expense";
        }

        if (category.isEmpty() || transactionDate.isEmpty() || account == null || amountStr.isEmpty() || transactionTypeStr == null) {
            showAlert(AlertType.ERROR, "All fields must be filled out.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Please enter a valid amount.");
            return;
        }

        boolean isSaved = persistenceHandler.saveTransaction(category, transactionDate, account, amount, transactionTypeStr);

        if (isSaved) {
            showAlert(AlertType.INFORMATION, "Transaction saved successfully.");
        } else {
            showAlert(AlertType.ERROR, "Failed to save the transaction.");
        }
    }


    private void showAlert(AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertType == AlertType.ERROR ? "Error" : "Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void initialize() {
        ObservableList<String> accountTypes = FXCollections.observableArrayList(
            "Savings", "Current", "Other"
        );
        accountdd.setItems(accountTypes);
    }
    
    @FXML
    void goBackToDashboard(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void applyFilter(ActionEvent event) {
        if (fcat.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Filter");
            alert.setHeaderText(null);
            alert.setContentText("Please select a category.");
            alert.showAndWait();
        } else {
            System.out.println("Filtering by category: " + fcat.getText());
        }
    }
}
