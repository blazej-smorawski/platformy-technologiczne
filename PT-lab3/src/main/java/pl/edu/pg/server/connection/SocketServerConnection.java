package pl.edu.pg.server.connection;

import pl.edu.pg.server.protocol.Protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketServerConnection implements Runnable {

    private final Socket socket;
    private final Protocol protocol;

    public SocketServerConnection(Socket socket, Protocol protocol) {
        this.socket = socket;
        this.protocol = protocol;
    }

    @Override
    public void run() {
        try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            Object inputObject;
            Object outputObject;
            while ((inputObject = input.readObject()) != null) {
                outputObject = protocol.getResponseTo(inputObject);
                output.writeObject(outputObject);
                if (outputObject.getClass() == String.class) {
                    System.out.println("Server received: "+(String) inputObject);
                    if (((String) outputObject).equals("Session Ended")){
                        break;
                    }
                }
            }
            System.out.println("Session is finished");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
