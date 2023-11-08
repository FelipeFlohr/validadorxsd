package dev.felipeflohr.validadorxsd.controllers;

import com.google.inject.Injector;
import dev.felipeflohr.validadorxsd.container.InjectionContainer;
import dev.felipeflohr.validadorxsd.services.fileopener.FileOpenerService;
import dev.felipeflohr.validadorxsd.services.validator.XmlValidator;
import dev.felipeflohr.validadorxsd.utils.FxUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MainController {
    private final Injector injector = InjectionContainer.getInstance().getInjector();
    private final FileOpenerService fileOpener = injector.getInstance(FileOpenerService.class);
    private final XmlValidator validator = injector.getInstance(XmlValidator.class);

    private File xmlFile;
    private File xsdFile;

    @FXML
    private TextField xmlFileLocation;

    @FXML
    private TextField xsdFileLocation;

    @FXML
    protected void openFileDialogXml(ActionEvent event) {
        Window window = FxUtils.getWindowFromEvent(event);
        Optional<File> file = fileOpener.abrirArquivo(window, criaMapExtensaoUnica(".xml"));

        if (file.isPresent()) {
            xmlFile = file.get();
            xmlFileLocation.setText(file.get().getAbsolutePath());
        }
    }

    @FXML
    protected void openFileDialogXsd(ActionEvent event) {
        Window window = FxUtils.getWindowFromEvent(event);
        Optional<File> file = fileOpener.abrirArquivo(window, criaMapExtensaoUnica(".xsd"));

        if (file.isPresent()) {
            xsdFile = file.get();
            xsdFileLocation.setText(file.get().getAbsolutePath());
        }
    }

    @FXML
    protected void validaXsd(ActionEvent event) {
        if (validaCampoArquivos()) {
            List<String> logInconsistencias = validator.validaXml(xmlFile, xsdFile);
            if (logInconsistencias.isEmpty()) {
                mostraDialogSucesso();
            } else {
                ValidationErrorsDialogController dialog = new ValidationErrorsDialogController(logInconsistencias, FxUtils.getWindowFromEvent(event));
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                dialog.showAndWait();
            }
        }
    }

    private Map<String, List<String>> criaMapExtensaoUnica(String extensao) {
        extensao = extensao.startsWith(".") ? "*" + extensao.trim() : "*." + extensao.trim();

        List<String> extensoes = new ArrayList<>();
        extensoes.add(extensao);
        Map<String, List<String>> extensoesMap = new HashMap<>();
        extensoesMap.put("Arquivo " + extensao, extensoes);

        return extensoesMap;
    }

    private boolean validaCampoArquivos() {
        if (xsdFile == null && xsdFileLocation.getText() != null && !xsdFileLocation.getText().trim().isBlank()) {
            xsdFile = new File(xsdFileLocation.getText());
        }
        if (xmlFile == null && xmlFileLocation.getText() != null && !xmlFileLocation.getText().trim().isBlank()) {
            xmlFile = new File(xmlFileLocation.getText());
        }

        if (xsdFileLocation == null || xsdFile == null || xmlFileLocation == null || xmlFile == null) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, especifique o caminho do arquivo XML e XSD.");
            alert.show();
            return false;
        }
        return true;
    }

    private void mostraDialogSucesso() {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Nenhum erro encontrado!");
        alert.show();
    }
}