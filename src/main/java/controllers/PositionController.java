package controllers;

import apiRequester.HttpClient;
import form.FormResolver;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.Position;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PositionController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem mntEdit;

    @FXML
    private MenuItem mntAdd;

    @FXML
    private MenuItem mntActive;

    @FXML
    private TableView<Position> tblPositions;

    @FXML
    private TableColumn<Position, Long> colmnId;

    @FXML
    private TableColumn<Position, String> colmName;
    FormResolver formResolver = new FormResolver();

    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(mntAdd)){
            openAddForm();
;        }else if(event.getSource().equals(mntEdit)){
            openEditForm();
        }
    }

    private void openEditForm() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/positionAddEdit.fxml"));
        try {
            loader.load();
            PositionAddEditController positionAddEditController = loader.getController();
            positionAddEditController.setDataToEdit(tblPositions.getSelectionModel().getSelectedItem());
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    tblPositions.refresh();
                    initTableView();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openAddForm() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/positionAddEdit.fxml"));
        try {
            loader.load();
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    tblPositions.refresh();
                    initTableView();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        initColumnProperties();
        initTableView();
    }

    private void initTableView() {
        List<Position> positionList = HttpClient.INSTANCE.getAllPositions();
        tblPositions.setItems(FXCollections.observableArrayList(positionList));
    }

    private void initColumnProperties() {
        colmnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblPositions.setRowFactory(tv -> new TableRow<Position>() {
            @Override
            protected void updateItem(Position category, boolean empty) {
                super.updateItem(category, empty);
                if (category!=null&&!category.isActive()){
                    setStyle("-fx-background-color:red");
                }else {
                    setStyle("");
                }
            }
        });
    }

}
