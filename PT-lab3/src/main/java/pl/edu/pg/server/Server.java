package pl.edu.pg.server;

import pl.edu.pg.server.service.ServerService;

import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerService service = new ServerService();
        Thread serverThread = new Thread(service);
        serverThread.start();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.print("command: ");
            String command = scanner.next();
            switch (command) {
                case "quit":
                    running = false;
                    break;
                default:
                    System.out.println("quit");
            }
        }

        serverThread.interrupt();
    }
}
