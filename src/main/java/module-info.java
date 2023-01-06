module com.example.tictacto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictacto to javafx.fxml;
    exports com.example.tictacto;
}