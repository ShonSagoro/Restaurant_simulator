module com.example.restaurant_simulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.restaurant_simulator to javafx.fxml;
    exports com.example.restaurant_simulator;
}