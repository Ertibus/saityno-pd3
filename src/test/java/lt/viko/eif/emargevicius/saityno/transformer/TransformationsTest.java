package lt.viko.eif.emargevicius.saityno.transformer;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import lt.viko.eif.emargevicius.saityno.pojo.Planets;

/**
 *
 * @author Emil
 */
public class TransformationsTest {
    
    public TransformationsTest() {
    }
    private final static String DEFAULT_XML_IN_PATH = "planets_test.xml";
    private final static String DEFAULT_XML_OUT_PATH = "planets_test_out.xml";
    private final static String DEFAULT_XSD_PATH = "planets.xsd";

    @Test
    public void testTransformToPOJO() {
        System.out.println("Testing XML to POJO transform");
        Transformations transformer = new Transformations(DEFAULT_XSD_PATH);
        
        try {
            Planets p = transformer.transformToPOJO(DEFAULT_XML_IN_PATH);
        } catch (JAXBException | IOException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testTransformToXML() {
        System.out.println("Testing POJO to XML transform");
        Transformations transformer = new Transformations(DEFAULT_XSD_PATH);
        
        try {
            Planets p = new Planets();
            System.out.println("Testing POJO to XML");
            transformer.transformToXML(p, DEFAULT_XML_OUT_PATH);
        } catch (JAXBException | IOException ex) {
            fail(ex.getMessage());
        } finally {
            File file = new File(DEFAULT_XML_OUT_PATH);
            file.delete();
        }
    }

    @Test
    public void testValidateXmlSchema() {
        System.out.println("Testing XML Schema Validation");
        Transformations transformer = new Transformations(DEFAULT_XSD_PATH);

        boolean result = transformer.validateXmlSchema(DEFAULT_XML_IN_PATH);
        assertEquals(true, result);
    }
    
}
