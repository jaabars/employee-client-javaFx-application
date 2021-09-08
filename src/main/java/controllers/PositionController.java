package controllers;

import form.FormResolver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Position;

import java.io.IOException;
import java.net.URL;
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
            formResolver.openForm("../views/positionAddEdit.fxml");
        }
    }



    @FXML
    void initialize() {

    }
}
