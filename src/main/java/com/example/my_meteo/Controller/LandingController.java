package com.example.my_meteo.Controller;

import com.example.my_meteo.Entity.City;
import com.example.my_meteo.Entity.InfoClimatResponse;
import com.example.my_meteo.MainApplication;
import com.example.my_meteo.Object.CustomCircleMarkerLayer;
import com.example.my_meteo.Object.GeographicMap;
import com.example.my_meteo.Service.GeoRequest;
import com.example.my_meteo.Service.InfoClimatRequest;
import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import com.gluonhq.maps.MapView;

public class LandingController {
    @FXML
    public Button button;

    @FXML
    public TextField cityTextField;

    @FXML
    public VBox citiesVBox;

    @FXML
    public VBox mapVBox;

    @FXML
    protected void showWeather(ActionEvent event, City city) {
        SuperController superController = SuperController.getInstance();
        superController.setCurrent_city(city);

        InfoClimatRequest req = new InfoClimatRequest(city.getLatlong());
        InfoClimatResponse infoClimatResponse = null;
        try {
            infoClimatResponse = req.send();
            superController.setInfoClimatResponse(infoClimatResponse);
        } catch (Exception e) {
            buildErrorAlert("Une erreur est survenue !", e.getMessage());
            return;
        }

        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("weather.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void showCityListActionEvent(ActionEvent event) throws IOException, InterruptedException {
        showCityList();
    }
    @FXML
    protected void showCityListKeyEvent(KeyEvent event) throws IOException, InterruptedException {
        showCityList();
    }
    @FXML
    public void updateInput(KeyEvent event) throws IOException, InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            showCityList();
        }
    }

    protected void showCityList() throws IOException, InterruptedException {
        citiesVBox.getChildren().clear();

        String inputValue = cityTextField.getText();

        if (Objects.equals(inputValue, "")) {
            return;
        }

        if (Objects.equals(inputValue, "CEFIM")) {
            cefimRequest();
            return;
        }

        GeoRequest geo = new GeoRequest(inputValue, 10);
        List<City> cities = geo.getCities();


        MapView mapView = GeographicMap.build();
        for (City city : cities) {
            MapPoint mapPoint = new MapPoint(Double.parseDouble(city.getLat()), Double.parseDouble(city.getLon()));
            MapLayer mapLayer = new CustomCircleMarkerLayer(mapPoint);

            mapView.addLayer(mapLayer);

            Hyperlink link = new Hyperlink();
            link.setText(city.getCity_name() + " " + city.getState());
            link.setStyle("-fx-text-fill: white; -fx-background-color:  #0b0071; -fx-padding: 10 5 10 5; -fx-background-radius: 10px;-fx-border-insets: 5px; -fx-background-insets: 5px");
            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    showWeather(e, city);
                }
            });
            citiesVBox.getChildren().add(link);
        }

        mapVBox.getChildren().add(mapView);
    }

    public Alert buildErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);

        // You can customize the alert buttons as needed
        alert.getButtonTypes().setAll(ButtonType.OK);

        // Show the alert
        alert.showAndWait();
        return alert;
    }

    public void cefimRequest() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("cefim.fxml"));
        Scene landingScene = new Scene(fxmlLoader.load(), 600, 600);

        SuperController superController = SuperController.getInstance();
        Stage stage = superController.getStage();
        stage.setTitle("My Météo");
        stage.setScene(landingScene);
        stage.show();
    }

}
