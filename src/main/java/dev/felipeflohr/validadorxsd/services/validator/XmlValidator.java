package dev.felipeflohr.validadorxsd.services.validator;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface XmlValidator {
    List<String> validaXml(File xml, File xsd);
}
