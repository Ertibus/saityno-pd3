package lt.viko.eif.emargevicius.saityno.pd3.repo;

import lt.viko.eif.emargevicius.saityno.pd3.pojo.Planet;
import lt.viko.eif.emargevicius.saityno.pd3.pojo.Planets;
import lt.viko.eif.emargevicius.saityno.pd3.transformer.Transformations;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

/**
 * Planets repository. Calls transformation to get end write data.
 *
 * @author Emil
 * @version 1.0
 * @since 1.0
 */
public class PlanetsRepository {
    private final static String DEFAULT_XML_PATH = "planets.xml";
    private final static String DEFAULT_XSD_PATH = "planets.xsd";

    private static final Transformations transformations = new Transformations(DEFAULT_XSD_PATH);
    private static Planets planets;

    /**
     * Retrieve data from XML file and store it into the static {@link Planets} object by calling {@link Transformations}
     */
    public static void retrieveData() {
        try {
            planets = transformations.transformToPOJO(DEFAULT_XML_PATH);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Store data from {@link Planets} into the XML file by calling {@link Transformations}
     */
    public static void storeData(){
        try {
            transformations.transformToXML(planets, DEFAULT_XML_PATH);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all of the currently stored planets
     * @return A list of {@link Planet} object
     */
    public static List<Planet> getPlanets() {
        retrieveData();
        return planets.getPlanets();
    }

    /**
     * Add a {@link Planet} to the list
     * @param newPlanet the planet to add
     */
    public static void addPlanet(Planet newPlanet) {
        retrieveData();

        List<Planet> updList = planets.getPlanets();
        updList.add(newPlanet);
        planets.setPlanets(updList);

        storeData();
    }

    /**
     * Set planets to a new planets list
     * @param planetList the new planets list
     */
    public static void setPlanets(List<Planet> planetList) {
        retrieveData();

        planets.setPlanets(planetList);

        storeData();
    }
}