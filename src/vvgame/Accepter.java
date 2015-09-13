/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vvgame;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mef
 */
public class Accepter {

    private final Controller controller;
    private final VVServerSocket serverSocket;

    private boolean isActive;

    public Accepter() {
        this.controller = new Controller();
        this.serverSocket = new VVServerSocket();

        isActive = true;
    }

    public void execute() {
        System.out.println("Server is running on port: " + CONSTANS.PORT);
        Thread thread = new Thread(new ClientListener());
        thread.start();
    }

    public void stop() {
        isActive = false;
    }

    class ClientListener implements Runnable {

        @Override
        public void run() {
            while (isActive) {
                try {
                    Socket socket = serverSocket.getServerSocket().accept();
                    System.out.println("New connection " + socket);
                    controller.addClient(new Client(socket));
                } catch (IOException ex) {
                    Logger.getLogger(Accepter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        Accepter accepter = new Accepter();
        accepter.execute();
    }
}
