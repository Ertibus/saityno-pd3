package lt.viko.eif.emargevicius.saityno.pd3.transformer;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import lt.viko.eif.emargevicius.saityno.pd3.pojo.Planets;
import org.xml.sax.SAXException;

/**
 * Class that handles all transformation logic.
 *
 * @author Emil
 * @version 1.0
 * @since 1.0
 */
public class Transformations {

    private final String xsdPath;

    /**
     * Class constructor that takes in XSD schema file path for validation.
     *
     * @param xsdPath a string representing XSD schema file path
     */
    public Transformations(String xsdPath) {
        this.xsdPath = xsdPath;
    }

    /**
     * Writes POJO object
     * {@link Planets} object to XML
     * file
     *
     * @param planets a Planets object
     * @param outPath XML file path to export the given Planets object. If file
     * exists it will be overwritten, else it will be created
     * @see Planets
     * @throws java.io.IOException
     * @throws JAXBException JAXBContext exception
     */
    public void transformToXML(Planets planets, String outPath) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Planets.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(planets, new File(outPath));
        if (!validateXmlSchema(outPath)) {
            throw new IOException("XML filed has failed validation against XSD file");
        }
    }

    /**
     * Reads XML file, calls for validation, and returns it transformed into
     * POJO object {@link Planets}
     *
     * @param xmlPath XML file location path.
     * @return POJO object Planets
     * @see Planets
     * @throws JAXBException JAXBContext exception
     * @throws IOException File not found exception
     */
    public Planets transformToPOJO(String xmlPath) throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance(Planets.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Path path = Path.of(xmlPath);

        if (!validateXmlSchema(xmlPath)) {
            throw new IOException("XML filed has failed validation against XSD file");
        }
        String xmlContent = Files.readString(path);

        StringReader reader = new StringReader(xmlContent);

        return (Planets) unmarshaller.unmarshal(reader);
    }

    /**
     * Validates given XML file against XSD schema
     *
     * @param xmlPath path to the XML file
     * @return a Boolean value - was the validation successful
     */
    public boolean validateXmlSchema(String xmlPath) {

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException ex) {
            Logger.getLogger(Transformations.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
