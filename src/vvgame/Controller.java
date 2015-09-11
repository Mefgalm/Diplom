/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vvgame;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mef
 */
public class Controller {

    private final List<Client> clientList;

    public Controller() {
        clientList = new LinkedList<>();
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void addClient(Client client) {
        clientList.add(client);
        byte[] b = new byte[100];
        BufferedInputStream dataInputStream = new BufferedInputStream(client.getInputStream());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isAlive = true;
                int byteCount = 0;
                while (isAlive) {
                    try {  
                        byteCount = dataInputStream.read(b);
                        if( byteCount == -1 ) {
                            break;
                        }
                        System.out.println(new String(b));
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();

    }
}
