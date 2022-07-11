module com.project.gameoflife {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.project.gameoflife to javafx.fxml;
    exports com.project.gameoflife;
}