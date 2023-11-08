package dev.felipeflohr.validadorxsd.services.fileopener;

import javafx.stage.Window;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FileOpenerService {
    Optional<File> abrirArquivo(Window janela);
    Optional<File> abrirArquivo(Window janela, Map<String, List<String>> extensoes);
}
