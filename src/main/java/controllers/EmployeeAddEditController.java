package controllers;

import apiRequester.HttpClient;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import models.Employee;
import models.Position;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAddEditController {

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<Position> cmbPositions;

    @FXML
    private ComboBox<String> cmbStatus;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    void onButtonClicked(ActionEvent event) {
        if (event.getSource().equals(btnSave)){
            saveEmployee();
        }
    }

    private void saveEmployee() {
        Employee employee = new Employee();
        employee.setName(txtName.getText());
        employee.setPosition(cmbPositions.getSelectionModel().getSelectedItem());
        employee.setEmployeeStatus(cmbStatus.getSelectionModel().getSelectedItem());
        employee = HttpClient.INSTANCE.saveEmployee(employee);
        if (employee.getId()!=null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Success");
            alert.show();
        }
    }

    @FXML
    void initialize(){
       initPositionComboBox();
       initStatusComboBox();
    }

    private void initStatusComboBox() {
        List<String> statusList = new ArrayList<>();
        statusList.add("WORK");
        statusList.add("FIRED");
        statusList.add("SICK");
        statusList.add("VACATION");
        cmbStatus.setItems(FXCollections.observableArrayList(statusList));

    }

    private void initPositionComboBox() {
        List<Position> positionList = HttpClient.INSTANCE.getAllPositions();
        cmbPositions.setItems(FXCollections.observableArrayList(positionList));
        cmbPositions.setConverter(new StringConverter<Position>() {
            @Override
            public String toString(Position position) {
                return position.getName();
            }

            @Override
            public Position fromString(String string) {
                return null;
            }
        });
    }

}
