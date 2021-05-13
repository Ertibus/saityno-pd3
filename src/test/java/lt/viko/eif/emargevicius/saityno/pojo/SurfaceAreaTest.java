package lt.viko.eif.emargevicius.saityno.pojo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SurfaceAreaTest {
    
    /**
     * Test of setKm2 and getKm2 methods, of class SurfaceArea.
     */
    @Test
    public void testGetKm2() {
        System.out.println("Testing getKm2 and setKm2 methods");
        SurfaceArea instance = new SurfaceArea();
        long expResult = 999L;
        long km2 = 999L;
        
        instance.setKm2(km2);
        long result = instance.getKm2();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEarths and getEarths methods, of class SurfaceArea.
     */
    @Test
    public void testGetEarths() {
        System.out.println("Testing getEarths and setEarths mothods");
        SurfaceArea instance = new SurfaceArea();
        float expResult = 456.789F;
        float earths = 456.789F;
        instance.setEarths(earths);
        float result = instance.getEarths(); 
        assertEquals(expResult, result);
    }
}
