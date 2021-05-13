package lt.viko.eif.emargevicius.saityno;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import lt.viko.eif.emargevicius.saityno.pojo.Planets;
import lt.viko.eif.emargevicius.saityno.transformer.Transformations;

/**
 *
 * @author Emil
 */
public class Main {
    private final static String DEFAULT_XML_IN_PATH = "planets.xml";
    private final static String DEFAULT_XML_OUT_PATH = "planets_out.xml";
    private final static String DEFAULT_XSD_PATH = "planets.xsd";

    public static void main(String[] args) {
        Transformations transformer = new Transformations(DEFAULT_XSD_PATH);
        
        try {
            Planets p = transformer.transformToPOJO(DEFAULT_XML_IN_PATH);
            transformer.transformToXML(p, DEFAULT_XML_OUT_PATH);
            
            
        } catch (JAXBException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
