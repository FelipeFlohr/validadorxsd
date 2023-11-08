package dev.felipeflohr.validadorxsd.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Modality;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;

public class ValidationErrorsDialogController extends Dialog<Boolean> {
    @FXML
    private TextArea errosLog;

    public ValidationErrorsDialogController(List<String> logInconsistencias, Window owner) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/dev/felipeflohr/validadorxsd/validation-errors-view.fxml"));
            loader.setController(this);

            DialogPane dialogPane = loader.load();

            initOwner(owner);
            initModality(Modality.APPLICATION_MODAL);

            setResizable(true);
            setTitle("Erros encontrados.");
            setDialogPane(dialogPane);
            defineErrosLog(logInconsistencias);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void copiarAreaTransferencia() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(errosLog.getText());
        clipboard.setContent(content);
    }

    private void defineErrosLog(List<String> logInconsistencias) {
        errosLog.setText(logInconsistencias.stream().reduce((a, b) -> a + "\n" + b + "\n").get());
    }
}
