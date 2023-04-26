module com.example.client {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.client to javafx.fxml;
    exports com.example.client;
    //exports;
    opens to;
}