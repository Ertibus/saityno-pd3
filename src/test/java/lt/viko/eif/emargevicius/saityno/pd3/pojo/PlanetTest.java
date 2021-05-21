package lt.viko.eif.emargevicius.saityno.pd3.pojo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlanetTest {
    /**
     * Test of getName and setName methods, of class Planet.
     */
    @Test
    public void testSetGetName() {
        System.out.println("Testing getName and setName methods methods");
        String name = "Name"; 
        String expResult = "Name";
        Planet instance = new Planet();
        
        instance.setName(name);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGod and setGod methods, of class Planet.
     */
    @Test
    public void testSetGetGod() {
        System.out.println("Testing getGod and setGod methods methods");
        String god = "God";
        String expResult = "God";
        Planet instance = new Planet();
        
        instance.setGod(god);
        String result = instance.getGod();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGodAspect and setGodAspect methods, of class Planet.
     */
    @Test
    public void testSetGetGodAspect() {
        System.out.println("Testing getGodAspect and setGodAspect methods");
        String godAspect = "Testing";
        String expResult = "Testing";
        Planet instance = new Planet();
        
        instance.setGodAspect(godAspect);
        String result = instance.getGodAspect();
        assertEquals(expResult, result);
    }
    /**
     * Test of setOrbitTime and getOrbitTime methods, of class Planet.
     */
    @Test
    public void testSetGetOrbitTime() {
        System.out.println("Testing setOrbitTime and getOrbitTime methods");
        OrbitTime orbitTime = new OrbitTime();
        OrbitTime expResult = new OrbitTime();
        Planet instance = new Planet();
        
        instance.setOrbitTime(orbitTime);
        OrbitTime result = instance.getOrbitTime();
        assertEquals(expResult.toString(), result.toString());
    }
    /**
     * Test of setOrbitalSpeed and getOrbitalSpeed methods, of class Planet.
     */
    @Test
    public void testSetGetOrbitalSpeed() {
        System.out.println("Testing setOrbitalSpeed and getOrbitalSpeed methods");
        float orbitalSpeed = 1.23F;
        float expResult = 1.23F;
        Planet instance = new Planet();
        
        instance.setOrbitalSpeed(orbitalSpeed);
        float result = instance.getOrbitalSpeed();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setEscapeVelocity and getEscapeVelocity methods, of class Planet.
     */
    @Test
    public void testSetGetEscapeVelocity() {
        System.out.println("Testing setEscapeVelocity and getEscapeVelocity methods");
        float escapeVelocity = 10.5F;
        float expResult = 10.5F;
        Planet instance = new Planet();
        
        instance.setEscapeVelocity(escapeVelocity);
        float result = instance.getEscapeVelocity();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setGravity and getGravity methods, of class Planet.
     */
    @Test
    public void testSetGetGravity() {
        System.out.println("Testing setGravity and getGravity methods");
        Planet instance = new Planet();
        float gravity = 9.80F;
        float expResult = 9.80F;
        
        instance.setGravity(gravity);
        float result = instance.getGravity();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setSurfacePressure and getSurfacePressure methods, of class Planet.
     */
    @Test
    public void testSetGetSurfacePressure() {
        System.out.println("Testing setSurfacePressure and getSurfacePressure methods");
        Planet instance = new Planet();
        String surfacePressure = "987654";
        String expResult = "987654";
        
        instance.setSurfacePressure(surfacePressure);
        String result = instance.getSurfacePressure();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSurfaceArea and getSurfaceArea methods, of class Planet.
     */
    @Test
    public void testSetGetSurfaceArea() {
        System.out.println("Testing setSurfaceArea and getSurfaceArea methods");
        Planet instance = new Planet();
        SurfaceArea expResult = new SurfaceArea();
        SurfaceArea surfaceArea = new SurfaceArea();

        instance.setSurfaceArea(surfaceArea);
        SurfaceArea result = instance.getSurfaceArea();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of setSatellites and getSatellites methods, of class Planet.
     */
    @Test
    public void testSetGetSatellites() {
        System.out.println("Testing setSatellites and getSatellites methods");
        Planet instance = new Planet();
        int expResult = 123;
        int satellites = 123;
        
        instance.setSatellites(satellites);
        int result = instance.getSatellites();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTemperature and getTemperature methods, of class Planet.
     */
    @Test
    public void testGetTemperature() {
        System.out.println("Testing setTemperature and getTemperature methods");
        Planet instance = new Planet();
        Temperature expResult = new Temperature();
        Temperature temperature = new Temperature();
        
        instance.setTemperature(temperature);
        Temperature result = instance.getTemperature();
        assertEquals(expResult.toString(), result.toString());
    }
}
