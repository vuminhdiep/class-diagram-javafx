module csc260 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
//    requires javafx.web;


//    requires validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
    requires java.datatransfer;
    requires java.desktop;
    requires org.jgrapht.core;

//    requires eu.hansolo.tilesfx;
    opens csc260 to javafx.fxml;
    opens csc260.Controller to javafx.fxml;
    exports csc260;
}