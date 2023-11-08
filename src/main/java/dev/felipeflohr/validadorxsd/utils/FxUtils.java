package dev.felipeflohr.validadorxsd.utils;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Window;

public class FxUtils {
    public static Window getWindowFromEvent(ActionEvent event) {
        return ((Node) event.getTarget()).getScene().getWindow();
    }
}
