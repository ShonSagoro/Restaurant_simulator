module com.example.restaurant_simulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.restaurant_simulator to javafx.fxml;
    exports com.example.restaurant_simulator;
    exports com.example.restaurant_simulator.controller;
    opens com.example.restaurant_simulator.controller to javafx.fxml;
}