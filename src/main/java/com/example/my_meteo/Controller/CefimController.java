package com.example.my_meteo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CefimController {

    @FXML
    public void openRepository(ActionEvent event) {
        System.out.println("event");
        try {
            URI uri = new URI("https://gitlab.cefim-formation.org/francois.lg/mymeteo");

            System.out.println("Open");
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

}
