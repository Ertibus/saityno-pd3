package lt.viko.eif.emargevicius.saityno.networking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JavaClientTest {

    private static final String IP_ADDRESS = "127.0.0.1";
    private static final int PORT = 6060;
    Thread serverThread;

    @BeforeEach
    public void setUp() {
        serverThread = new Thread(() -> {
            JavaServer serverInstance = new JavaServer();
            serverInstance.startServer(PORT);
        });
        serverThread.start();
    }

    @Test
    public void testConnection() throws Exception {
        System.out.println("test Client and Server connection");
        JavaClient instance = new JavaClient();
        instance.startConnection(IP_ADDRESS, PORT);
        instance.sendMessage("");
    }

    @Test
    public void testGreeting() throws Exception {
        System.out.println("test Client and Server greeting");
        String msg = "Hello";
        JavaClient instance = new JavaClient();
        instance.startConnection(IP_ADDRESS, PORT);
        String expResult = "Hello client!";
        String result = instance.sendMessage(msg);
        assertEquals(expResult, result);
    }

    @Test
    public void testUnknownMessage() throws Exception {
        System.out.println("test Client sending unknown message");
        String msg = "7afw";
        JavaClient instance = new JavaClient();
        instance.startConnection(IP_ADDRESS, PORT);
        String expResult = "Unknown Message received";
        String result = instance.sendMessage(msg);
        assertEquals(expResult, result);
    }

    @Test
    public void testFileRequest() throws Exception {
        System.out.println("test Client requesting the XML file");
        String msg = "GetXML";
        JavaClient instance = new JavaClient();
        instance.startConnection(IP_ADDRESS, PORT);
        instance.sendFileRequest(msg);
    }
}
