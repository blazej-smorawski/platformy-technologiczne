package pl.edu.pg.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9797)) {
            try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                Object inputObject;
                Object outputObject;

                output.writeObject("Hello");

                inputObject = input.readObject();
                if(inputObject.getClass() == String.class) {
                    System.out.println("Server said: "+(String)inputObject);
                }

                output.writeObject("Bye");

                inputObject = input.readObject();
                if(inputObject.getClass() == String.class) {
                    System.out.println("Server said: "+(String)inputObject);
                }


                System.out.println("Client session is finished");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
