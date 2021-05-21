/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.emargevicius.saityno.pd3.pojo;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Emil
 */
public class PlanetsTest {
    /**
     * Test of setPlanets and getPlanets method, of class Planets.
     */
    @Test
    public void testSetGetPlanets() {
        System.out.println("Testing setPlanets and getPlanets methods");
        Planets instance = new Planets();
        List<Planet> planets = new ArrayList<>();
        List<Planet> expPlanets = new ArrayList<>();

        instance.setPlanets(planets);

        List<Planet> result = instance.getPlanets();
        assertArrayEquals(result.toArray(), expPlanets.toArray());
    }
}
