module org.ftpviewer {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.ftpviewer to javafx.fxml;
    exports org.ftpviewer;
}