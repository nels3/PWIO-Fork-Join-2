package nels;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/nels/MainScene.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Projekt 2 - Fork Thread");
        stage.setScene(scene);
        stage.show();
    }
}