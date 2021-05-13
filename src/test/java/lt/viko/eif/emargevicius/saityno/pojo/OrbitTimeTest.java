package lt.viko.eif.emargevicius.saityno.pojo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrbitTimeTest {

    /**
     * Test of setYears and getYears methods, of class OrbitTime.
     */
    @Test
    public void testSetGetYears() {
        System.out.println("Testing: getYears and setYears methods");
        OrbitTime instance = new OrbitTime();
        float expResult = 93F;
        float years = 93F;
        
        instance.setYears(years);
        float result = instance.getYears();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDays and getDays method, of class OrbitTime.
     */
    @Test
    public void testSetGetDays() {
        System.out.println("getDays");
        OrbitTime instance = new OrbitTime();
        float expResult = 0.0F;
        float days = 0.0F;
        
        instance.setDays(days);
        float result = instance.getDays();
        assertEquals(expResult, result);
    }
}
