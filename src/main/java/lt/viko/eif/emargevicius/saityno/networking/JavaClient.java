package lt.viko.eif.emargevicius.saityno.networking;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import lt.viko.eif.emargevicius.saityno.pojo.Planets;
import lt.viko.eif.emargevicius.saityno.transformer.Transformations;
import lt.viko.eif.emargevicius.saityno.pojo.Planet;

/**
 * Java Client class. Responsible for the client side connection. This class has
 * a main function which does the current assigned tasks of asking for a file,
 * transforming it to Pojo class, and outputting it into the console.
 *
 * @author Emil
 * @version 1.0-SNAPSHOT
 * @since 1.0-SNAPSHOT
 * @see Planet
 * @see Transformations
 */
public class JavaClient {

    private final static String DEFAULT_IP = "127.0.0.1";
    private final static int DEFAULT_PORT = 6060;
    private final static String DEFAULT_XML_OUT_PATH = "planets_out.xml";
    private final static String DEFAULT_XSD_PATH = "planets.xsd";

    private Socket clientSocket;
    private OutputStream out;
    private InputStream in;
    private PrintWriter messengerOut;

    /**
     * Starts the clients connection to the server. Opens the Input, Output and
     * PrintWriter Streams
     *
     * @param ip server IP address
     * @param port server port
     * @throws IOException failed to open one of the streams
     */
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = clientSocket.getOutputStream();
        in = clientSocket.getInputStream();
        messengerOut = new PrintWriter(out, true);
    }

    /**
     * Should be used to send a simple message to the server. After sending the
     * message this function expects to receive a message back.
     *
     * @param msg message to send
     * @return message received from the server
     * @throws IOException Failed to read stream line
     */
    public String sendMessage(String msg) throws IOException {
        String resp;
        try (BufferedReader messengerIn = new BufferedReader(new InputStreamReader(in))) {
            messengerOut.println(msg);
            resp = messengerIn.readLine();
        }
        return resp;
    }

    /**
     * Should be used for requesting a file. After this function sends a message
     * to the server it expects to receive a byte stream, which is written into
     * the default output path
     *
     * @param msg
     * @throws IOException
     */
    public void sendFileRequest(String msg) throws IOException {
        messengerOut.println(msg);
        try (FileOutputStream fileOutputStream = new FileOutputStream(DEFAULT_XML_OUT_PATH)) {
            byte[] buffer = new byte[8 * 1024];

            int count;
            while ((count = in.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, count);
            }
        }
    }

    /**
     * Stops the clients connection by closing all of the Streams
     */
    private void stopConnection() {
        try {
            messengerOut.close();
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(JavaClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Main function. Creates a new JavaClient object that establishes a
     * connection to the server with given default parameters. After connecting, it requests
     * the XML file, which is converted to
     * {@link Planet} object using
     * {@link Transformations}
     * class. The object is then printed out into the console.
     *
     * @param args
     * @see Planet
     * @see Transformations
     */
    public static void main(String[] args) {
        JavaClient client = new JavaClient();
        try {
            client.startConnection(DEFAULT_IP, DEFAULT_PORT);

            client.sendFileRequest("GetXML");

            Transformations transformer = new Transformations(DEFAULT_XSD_PATH);
            Planets planets = transformer.transformToPOJO(DEFAULT_XML_OUT_PATH);

            System.out.println(planets.toString());
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(JavaClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            client.stopConnection();
        }
    }
}
