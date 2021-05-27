package lt.viko.eif.emargevicius.saityno.pd3.controllers;

import lt.viko.eif.emargevicius.saityno.pd3.pojo.Planet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetsResourceControllerTest {

    @Test
    void allPlanets() {
        System.out.println("Testing GET all planets");
        PlanetsResourceController controller = new PlanetsResourceController();
        try {
            controller.allPlanets();
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void getByName() {
        System.out.println("Testing GET by name");
        PlanetsResourceController controller = new PlanetsResourceController();
        try {
            controller.getByName("Earth");
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    void postByName() {
        System.out.println("Testing POST by name");
        PlanetsResourceController controller = new PlanetsResourceController();
        try {
            Planet testPlanet = new Planet();
            testPlanet.setName("TEST_Planet");
            controller.postByName(testPlanet);
        } catch (Exception ex) {
            fail(ex.getMessage());
        } finally {
            controller.deleteByName("TEST_Planet");
        }
    }

    @Test
    void putByName() {
        System.out.println("Testing PUT by name");
        PlanetsResourceController controller = new PlanetsResourceController();
        try {
            Planet testPlanet = new Planet();
            testPlanet.setName("TEST_Planet");
            controller.putByName("TEST_Planet", testPlanet);
        } catch (Exception ex) {
            fail(ex.getMessage());
        } finally {
            controller.deleteByName("TEST_Planet");
        }
    }

    @Test
    void deleteByName() {
        System.out.println("Testing PUT by name");
        PlanetsResourceController controller = new PlanetsResourceController();
        try {
            Planet testPlanet = new Planet();
            testPlanet.setName("TEST_Planet");
            controller.putByName("TEST_Planet", testPlanet);
            controller.deleteByName("TEST_Planet");
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
}