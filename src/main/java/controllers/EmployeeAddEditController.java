package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class EmployeeAddEditController {

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<?> cmbPositions;

    @FXML
    private ComboBox<?> cmbStatus;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    void onButtonClicked(ActionEvent event) {

    }

}
