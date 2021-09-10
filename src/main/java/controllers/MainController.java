package controllers;

import apiRequester.HttpClient;
import form.FormResolver;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.Employee;
import models.Position;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem mntPosition;

    @FXML
    private MenuItem mntAddEmployee;

    @FXML
    private MenuItem mntActive;

    @FXML
    private MenuItem mntEditEmployee;

    @FXML
    private TableView<Employee> tblEmployees;

    @FXML
    private TableColumn<Employee, Long> colmnId;

    @FXML
    private TableColumn<Employee, String> colmnName;

    @FXML
    private TableColumn<Employee, String> colmnStatus;

    @FXML
    private TableColumn<Employee, String> colmnPosition;
    FormResolver formResolver = new FormResolver();

    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(mntPosition)){
            formResolver.openForm("../views/position.fxml");
        }else if (event.getSource().equals(mntAddEmployee)){
            Stage stage = formResolver.openFormStage("../views/employeeAddEdit.fxml");
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    tblEmployees.refresh();
                    initTableView();
                }
            });
        }
    }




    @FXML
    void initialize() {
        initColumnProperties();
        initTableView();
    }

    private void initTableView() {
        List<Employee> employeeList = HttpClient.INSTANCE.getEmployees();
        tblEmployees.setItems(FXCollections.observableArrayList(employeeList));
    }

    private void initColumnProperties() {
        colmnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmnStatus.setCellValueFactory(new PropertyValueFactory<>("employeeStatus"));
        colmnPosition.setCellValueFactory(colmn->new SimpleStringProperty(colmn.getValue().getPosition().getName()));
    }
}
