package controllers;

import apiRequester.HttpClient;
import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import models.Position;

public class PositionAddEditController {

    Position position;
    @FXML
    private TextField txtPosition;

    @FXML
    private CheckBox chkActive;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    void onButtonClicked(ActionEvent event) {
        if (event.getSource().equals(btnSave)){
            savePosition();
        }
    }

    private void savePosition() {
        if (txtPosition.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Название не может быть пустым");
            alert.show();
            return;
        }
        Position position = new Position();
        position.setActive(chkActive.isSelected());
        position.setName(txtPosition.getText());
        position = HttpClient.INSTANCE.savePosition(position);
        if (position.getId()!=null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Успешно");
            alert.show();
        }
    }

    public void setDataToEdit(Position selectedItem) {
        position = selectedItem;
        txtPosition.setText(selectedItem.getName());
        chkActive.setSelected(selectedItem.isActive());
    }
}
