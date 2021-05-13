package lt.viko.eif.emargevicius.saityno.networking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java server class, which is responsible for the server side connection.
 * This class has a main function, which starts a new server on the default port.
 * Currently the class has a single XML file reference. This file can be sent to a server client with a specific request.
 * 
 * @author Emil
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 */
public class JavaServer {

    private final static int DEFAULT_PORT = 6060;
    private final static String DEFAULT_XML_IN_PATH = "planets.xml";
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private OutputStream out;
    private InputStream in;
    private PrintWriter messageOut;
    private BufferedReader messageIn;
    /**
     * Starts the server and listens for messages.
     * If the message is a request for a file, it reads its file and sends it as a byte stream;
     * 
     * @param port what port to listen to
     */
    public void startServer(int port) {
        
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = clientSocket.getOutputStream();
            in = clientSocket.getInputStream();
            
            messageOut = new PrintWriter(out, true);
            messageIn = new BufferedReader(new InputStreamReader(in));
            String mesg = messageIn.readLine();

            switch (mesg) {
                case "Hello":
                    messageOut.println("Hello client!");
                    break;
                case "GetXML":
                    try (FileInputStream fileInputStream = new FileInputStream(new File(DEFAULT_XML_IN_PATH))) {
                        byte[] buffer = new byte[8 * 1024];

                        int count;
                        while ((count = fileInputStream.read(buffer)) > 0) {
                            out.write(buffer, 0, count);
                        }
                    }   break;
                default:
                    messageOut.println("Unknown Message received");
                    break;
                }
        } catch (IOException ex) {
            System.out.println("Exception occurred at: " + ex);
        } finally {
            stopServer();
        }
    }
    /**
     * Stops the server by closing all of the streams and sockets.
     */
    private void stopServer() {
        try {
            messageOut.close();
            messageIn.close();
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(JavaServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Main function.  It simply creates a server object and start it on the default port.
     * @param args 
     */
    public static void main(String[] args) {
        JavaServer server = new JavaServer();
        server.startServer(DEFAULT_PORT);
    }
}
