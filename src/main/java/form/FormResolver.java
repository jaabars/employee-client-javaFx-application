package form;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FormResolver {
    public FXMLLoader openForm(String path){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        try {
            loader.load();
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
            return loader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Stage openFormStage(String path){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        try {
            loader.load();
            Parent root = loader.getRoot();
            stage.setScene(new Scene(root));
            stage.show();
            return stage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
