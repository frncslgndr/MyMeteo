package com.example.my_meteo;

import com.example.my_meteo.Controller.SuperController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            SuperController superController = SuperController.getInstance();
            superController.setStage(stage);

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("landing.fxml"));
            Scene landingScene = new Scene(fxmlLoader.load(), 600, 600);
            stage.setTitle("My Météo");
            stage.setScene(landingScene);

            stage.getIcons().add(new Image(getClass().getResource("/com/example/my_meteo/assets/soleil.png").toExternalForm()));
            stage.show();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}