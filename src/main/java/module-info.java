module com.example.boulder {

    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens g58414.atlg.boulder to javafx.fxml;
    exports g58414.atlg.boulder.controller;
    exports g58414.atlg.boulder;
    exports g58414.atlg.boulder.model;
    exports g58414.atlg.boulder.fxview;
    exports g58414.atlg.boulder.fxview.component;
    exports g58414.atlg.boulder.model.elements;
    exports g58414.atlg.boulder.consoleview;

}