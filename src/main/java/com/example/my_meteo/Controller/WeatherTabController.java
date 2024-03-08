package com.example.my_meteo.Controller;

import com.example.my_meteo.Entity.InfoClimatResponse;
import com.example.my_meteo.Utils.AreaChartCustom;
import com.example.my_meteo.Utils.BarChartCustom;
import com.example.my_meteo.Utils.LineChartCustom;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Getter
@Setter
public class WeatherTabController implements Initializable {
    private LocalDate date;

    @FXML
    public AreaChart areaChart;

    @FXML
    public LineChart lineChart;

    @FXML
    public BarChart barChart;

    @FXML
    public AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Platform.runLater(() -> {
            SuperController superController = SuperController.getInstance();
            InfoClimatResponse infoClimatResponse = superController.getInfoClimatResponse();

            AreaChartCustom areaChartCustom = new AreaChartCustom();
            LineChartCustom lineChartCustom = new LineChartCustom();
            BarChartCustom barChartCustom = new BarChartCustom();

            anchorPane.getChildren().add(areaChartCustom.createAreaChart(infoClimatResponse, this.date));
            anchorPane.getChildren().add(lineChartCustom.createLineChart(infoClimatResponse, this.date));
            anchorPane.getChildren().add(barChartCustom.createBarChart(infoClimatResponse, this.date));
        });
    }

    public void setData(LocalDate date) {
        this.date = date;
    }
}
