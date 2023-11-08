package dev.felipeflohr.validadorxsd.services.validator;

import dev.felipeflohr.validadorxsd.services.validator.handlers.XsdErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class XmlValidatorImpl implements XmlValidator {
    @Override
    public List<String> validaXml(File xml, File xsd) {
        var logInconsistencias = new ArrayList<String>();
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsd);
            Validator validator = schema.newValidator();
            validator.setErrorHandler(new XsdErrorHandler(logInconsistencias));

            validator.validate(new StreamSource(xml));
        } catch (SAXException | IOException e) {
            logInconsistencias.add(e.getLocalizedMessage());
        }
        return logInconsistencias;
    }
}
