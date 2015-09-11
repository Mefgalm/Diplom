/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vvgame;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mef
 */
public class VVServerSocket {
    private ServerSocket serverSocket;
    
    public VVServerSocket() {
        try {
            serverSocket = new ServerSocket(CONSTANS.PORT);
        } catch (IOException ex) {
            Logger.getLogger(VVServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
}
