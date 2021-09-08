package form;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FormResolver {
    public void openForm(String path){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        try {
            loader.load();
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
