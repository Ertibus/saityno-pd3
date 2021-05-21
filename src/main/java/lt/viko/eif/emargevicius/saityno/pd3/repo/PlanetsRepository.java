package lt.viko.eif.emargevicius.saityno.pd3.repo;

import lt.viko.eif.emargevicius.saityno.pd3.pojo.Planet;
import lt.viko.eif.emargevicius.saityno.pd3.pojo.Planets;
import lt.viko.eif.emargevicius.saityno.pd3.transformer.Transformations;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class PlanetsRepository {
    private final static String DEFAULT_XML_IN_PATH = "planets_test.xml";
    private final static String DEFAULT_XSD_PATH = "planets.xsd";

    private Transformations transformations = new Transformations(DEFAULT_XSD_PATH);
    private Planets planets;

    public PlanetsRepository() {
        try {
            planets = transformations.transformToPOJO(DEFAULT_XML_IN_PATH);
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Planet> getPlanets() {
        return planets.getPlanets();
    }
}
