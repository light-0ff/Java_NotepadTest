module notepad {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens notepad to javafx.fxml;
    exports notepad;
}