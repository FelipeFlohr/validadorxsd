package dev.felipeflohr.validadorxsd.services.fileopener;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class FileOpenerServiceImpl implements FileOpenerService {
    @Override
    public Optional<File> abrirArquivo(Window janela) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(janela);
        return file == null ? Optional.empty() : Optional.of(file);
    }

    @Override
    public Optional<File> abrirArquivo(Window janela, Map<String, List<String>> extensoes) {
        FileChooser fileChooser = new FileChooser();
        if (extensoes != null) {
            defineExtensoesFileChooser(fileChooser, extensoes);
        }

        File file = fileChooser.showOpenDialog(janela);
        return file == null ? Optional.empty() : Optional.of(file);
    }

    public void defineExtensoesFileChooser(FileChooser fileChooser, Map<String, List<String>> extensoes) {
        extensoes.forEach((k, v) -> fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(k, v)));
    }
}
