module com.example.projektsijad {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.projektsijad to javafx.fxml;
    exports com.example.projektsijad;
    exports com.example.projektsijad.Algorithms;
    opens com.example.projektsijad.Algorithms to javafx.fxml;
}