package lt.viko.eif.emargevicius.saityno.pd3.configs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SwaggerConfigTest {

    @Test
    public void testApiDocker() {
        System.out.println("Testing API Docker (Swagger Config)");
        SwaggerConfig config = new SwaggerConfig();
        assertNotNull(config.planetsAPI());
    }

}
