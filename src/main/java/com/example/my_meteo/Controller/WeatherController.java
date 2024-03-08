package com.example.my_meteo.Controller;

import com.example.my_meteo.Entity.City;
import com.example.my_meteo.Entity.Forecast;
import com.example.my_meteo.Entity.InfoClimatResponse;
import com.example.my_meteo.MainApplication;
import com.example.my_meteo.Service.InfoClimatRequest;
import com.example.my_meteo.Service.TouitRequest;
import com.example.my_meteo.Utils.AreaChartCustom;
import com.example.my_meteo.Utils.BarChartCustom;

import com.example.my_meteo.Utils.LineChartCustom;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class WeatherController implements Initializable {

    //Scene
    @FXML
    public Label title;

    @FXML
    public Button touitButton;

    @FXML
    public TabPane tabPane;

    //Tabs
    @FXML
    public Tab tab_1;
    public Tab tab_2;
    public Tab tab_3;
    public Tab tab_4;
    public Tab tab_5;

    //Charts
    @FXML
    public AreaChart areaChart_1;
    public LineChart lineChart_1;
    public BarChart barChart_1;
    public AreaChart areaChart_2;
    public LineChart lineChart_2;
    public BarChart barChart_2;
    public AreaChart areaChart_3;
    public LineChart lineChart_3;
    public BarChart barChart_3;
    public AreaChart areaChart_4;
    public LineChart lineChart_4;
    public BarChart barChart_4;
    public AreaChart areaChart_5;
    public LineChart lineChart_5;
    public BarChart barChart_5;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SuperController superController = SuperController.getInstance();
        City city = superController.getCurrent_city();
        title.setText("Météo de la ville de " + city.getCity_name());
        LocalDate today = LocalDate.now();

        InfoClimatResponse infoClimatResponse = superController.getInfoClimatResponse();

        InfoClimatRequest req = new InfoClimatRequest(city.getLatlong());

        Platform.runLater(() -> {
            LocalDate date1 = LocalDate.now();
            LocalDate date2 = date1.plusDays(1);
            LocalDate date3 = date1.plusDays(2);
            LocalDate date4 = date1.plusDays(3);
            LocalDate date5 = date1.plusDays(4);

            addNewTab(date1);
            addNewTab(date2);
            addNewTab(date3);
            addNewTab(date4);
            addNewTab(date5);
        });
    }

    public void touitThis(ActionEvent event) throws IOException, InterruptedException {

        SuperController superController = SuperController.getInstance();
        City city = superController.getCurrent_city();

        InfoClimatResponse infoClimatResponse = superController.getInfoClimatResponse();
        Forecast forecast = infoClimatResponse.getMaxTemperatureForecastByDate(LocalDate.now());
        String temperature = Float.toString(forecast.getTemperature().getTemp_2m() - 273);

        TouitRequest.SendTouit("MyMeteo", "Point météo : Aujourd'hui, il fait "+ temperature+"°C à "+ city.getCity_name());
    }

    public void goToSearch(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("landing.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewTab(LocalDate date) {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("weather_tab.fxml"));
        try {
            Tab newTab = loader.load();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd MMM");
            String tab_title = date.format(formatter);

            WeatherTabController controller = loader.getController();
            controller.setData(date);

            newTab.setText(tab_title);
            tabPane.getTabs().add(newTab);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

