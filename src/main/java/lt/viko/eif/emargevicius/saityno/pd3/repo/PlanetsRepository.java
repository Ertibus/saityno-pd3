package lt.viko.eif.emargevicius.saityno.pd3.repo;

import lt.viko.eif.emargevicius.saityno.pd3.pojo.Planet;
import lt.viko.eif.emargevicius.saityno.pd3.pojo.Planets;
import lt.viko.eif.emargevicius.saityno.pd3.transformer.Transformations;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class PlanetsRepository {
    private final static String DEFAULT_XML_PATH = "planets.xml";
    private final static String DEFAULT_XSD_PATH = "planets.xsd";

    private static final Transformations transformations = new Transformations(DEFAULT_XSD_PATH);
    private static Planets planets;

    public static void retrieveData() {
        try {
            planets = transformations.transformToPOJO(DEFAULT_XML_PATH);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void storeData(){
        try {
            transformations.transformToXML(planets, DEFAULT_XML_PATH);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Planet> getPlanets() {
        retrieveData();
        return planets.getPlanets();
    }

    public static void addPlanet(Planet newPlanet) {
        retrieveData();

        List<Planet> updList = planets.getPlanets();
        updList.add(newPlanet);
        planets.setPlanets(updList);

        storeData();
    }

    public static void setPlanets(List<Planet> planetList) {
        retrieveData();

        planets.setPlanets(planetList);

        storeData();
    }
}