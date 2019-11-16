module org.ftpviewer {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.net;
    requires java.desktop;
    requires javafx.swing;

    opens org.ftpviewer to javafx.fxml;
    exports org.ftpviewer;
}