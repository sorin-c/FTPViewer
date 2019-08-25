module org.ftpviewer {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.net;

    opens org.ftpviewer to javafx.fxml;
    exports org.ftpviewer;
}