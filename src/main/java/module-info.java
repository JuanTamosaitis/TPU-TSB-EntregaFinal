module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens visual to javafx.fxml;
    opens entities to javafx.fxml;
    exports visual;
    exports entities;
}