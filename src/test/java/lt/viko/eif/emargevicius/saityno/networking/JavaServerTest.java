package lt.viko.eif.emargevicius.saityno.networking;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JavaServerTest {
    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 6666;

    @Test
    public void testStartServer() {
        try {
            System.out.println("Starting Server");
            Thread serverThread = new Thread(() -> {
                JavaServer serverInstance = new JavaServer();
                serverInstance.startServer(PORT);
            });
            serverThread.start();
            JavaClient instance = new JavaClient();
            instance.startConnection(IP_ADDRESS, PORT);
            instance.sendMessage("");
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }    
}
