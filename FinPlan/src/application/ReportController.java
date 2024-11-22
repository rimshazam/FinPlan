package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ReportController {

    @FXML
    private Label repTLabel;

    @FXML
    private ComboBox<String> repType;

    @FXML
    void SelectRType(ActionEvent event) {
        if (repType != null) {
            String selectedReport = repType.getSelectionModel().getSelectedItem();
            if (selectedReport != null && repTLabel != null) {
                repTLabel.setText(selectedReport);
            }
        }
    }

    public void initialize() {
        if (repType != null) {
            ObservableList<String> list = FXCollections.observableArrayList("Report1", "Report2", "Report3");
            repType.setItems(list);
        }
        if (repTLabel != null) {
            repTLabel.setText("Select report type");
        }
    }

}
