module com.example.tictacto {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires org.apache.commons.lang3;


    opens com.example.tictacto to javafx.fxml;
    exports com.example.tictacto;
    exports com.example.tictacto.model;
}