package lt.viko.eif.emargevicius.saityno.pojo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TemperatureTest {
    
    /**
     * Test of setMin and getMin methods, of class Temperature.
     */
    @Test
    public void testSetGetMin() {
        System.out.println("getMin");
        Temperature instance = new Temperature();
        String expResult = "777";
        String min = "777";
        
        instance.setMin(min);
        String result = instance.getMin();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setMean and getMean methods, of class Temperature.
     */
    @Test
    public void testGetMean() {
        System.out.println("getMean");
        Temperature instance = new Temperature();
        String expResult = "777";
        String mean = "777";
        
        instance.setMean(mean);
        String result = instance.getMean();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMax and getMax methods, of class Temperature.
     */
    @Test
    public void testGetMax() {
        System.out.println("getMax");
        Temperature instance = new Temperature();
        String expResult = "777";
        String max = "777";
        
        instance.setMax(max);
        String result = instance.getMax();
        assertEquals(expResult, result);
    }
}
