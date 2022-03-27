package pl.edu.pg.server.service;

import pl.edu.pg.server.connection.SocketServerConnection;
import pl.edu.pg.server.protocol.StandardProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerService implements Runnable {

    public ServerService() {

    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try (ServerSocket server = new ServerSocket(9797)) {
                Socket socket = server.accept();
                System.out.println("New connection!");
                new Thread(new SocketServerConnection(socket,new StandardProtocol())).start();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
