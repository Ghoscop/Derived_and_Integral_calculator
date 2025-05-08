module heitor_e_matheus.calculadora {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.media;
//    requires matheclipse.core;

    opens heitor_e_matheus.calculadora to javafx.fxml;
    exports heitor_e_matheus.calculadora;
    exports Interface;
    opens Interface to javafx.fxml;
    exports Controller;
    opens Controller to javafx.fxml;
}