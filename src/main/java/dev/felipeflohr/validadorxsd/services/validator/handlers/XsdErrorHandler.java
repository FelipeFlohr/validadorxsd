package dev.felipeflohr.validadorxsd.services.validator.handlers;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.util.ArrayList;
import java.util.List;

public class XsdErrorHandler implements ErrorHandler {
    private List<String> logInconsistencias;

    public XsdErrorHandler(List<String> logInconsistencias) {
        this.logInconsistencias = logInconsistencias == null ? new ArrayList<>() : logInconsistencias;
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        this.logInconsistencias.add("ALERTA: " + e.getLocalizedMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        this.logInconsistencias.add("ERRO: " + e.getLocalizedMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        this.logInconsistencias.add("FATAL: " + e.getLocalizedMessage());
    }

    public List<String> getLogInconsistencias() {
        return logInconsistencias;
    }

    public void setLogInconsistencias(List<String> logInconsistencias) {
        this.logInconsistencias = logInconsistencias;
    }
}
