module com.example.mazesolver {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires javafx.swing;

    opens com.belskaya.mazesolver to javafx.fxml;
    exports com.belskaya.mazesolver;
    exports com.belskaya.mazesolver.controller;
    opens com.belskaya.mazesolver.controller to javafx.fxml;
    exports com.belskaya.mazesolver.view;
    opens com.belskaya.mazesolver.view to javafx.fxml;
}