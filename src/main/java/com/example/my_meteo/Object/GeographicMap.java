package com.example.my_meteo.Object;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class GeographicMap {
    public static MapView build() {

        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        MapView mapView = new MapView();
        MapPoint mapPoint = new MapPoint(46.938813971617925, 2.730653362004647);

        mapView.setZoom(5);
        mapView.flyTo(0, mapPoint, 0.1);

        return mapView;
    }
}
