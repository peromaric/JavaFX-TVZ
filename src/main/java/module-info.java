module com.example.javafxtvz {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;


    opens tvz.main to javafx.fxml;
    exports tvz.main;
}