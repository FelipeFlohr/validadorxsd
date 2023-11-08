module dev.felipeflohr.validadorxsd {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.guice;
    requires java.xml;


    opens dev.felipeflohr.validadorxsd to javafx.fxml;
    opens dev.felipeflohr.validadorxsd.services.fileopener to com.google.guice;
    opens dev.felipeflohr.validadorxsd.services.validator to com.google.guice;
    exports dev.felipeflohr.validadorxsd;
    exports dev.felipeflohr.validadorxsd.controllers;
    opens dev.felipeflohr.validadorxsd.controllers to javafx.fxml;
}