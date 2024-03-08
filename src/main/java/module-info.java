module com.example.my_meteo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires static lombok;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.gluonhq.maps;
    requires java.desktop;

    opens com.example.my_meteo to javafx.fxml;
    exports com.example.my_meteo;

    exports com.example.my_meteo.Entity;
    opens com.example.my_meteo.Entity to com.fasterxml.jackson.databind;
    exports com.example.my_meteo.Controller;
    opens com.example.my_meteo.Controller to javafx.fxml;
}