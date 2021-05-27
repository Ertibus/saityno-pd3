package lt.viko.eif.emargevicius.saityno.pd3.configs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Swagger Config Test file
 * Tests if Swagger has a configuration Bean
 */
public class SwaggerConfigTest {
    /**
     * Attempts to get the SwaggerUI configuration Bean
     */
    @Test
    public void testApiDocker() {
        System.out.println("Testing API Docker (Swagger Config)");
        SwaggerConfig config = new SwaggerConfig();
        assertNotNull(config.planetsAPI());
    }

}
