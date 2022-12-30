module com.example.swinglesson2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.swinglesson2 to javafx.fxml;
    exports com.example.swinglesson2;
}