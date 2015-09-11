/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_client;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import vvgame.CONSTANS;

/**
 *
 * @author Mef
 */
public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", CONSTANS.PORT);
        BufferedOutputStream dataOutputStream = new BufferedOutputStream(socket.getOutputStream());
        Thread.sleep(5000);
        try {
            dataOutputStream.write("Vlad".getBytes());
            dataOutputStream.flush();
            
            Thread.sleep(1000);
            
            dataOutputStream.write("Game".getBytes());
            dataOutputStream.flush();
            
            Thread.sleep(1000);
            
            dataOutputStream.write("TTT".getBytes());
            dataOutputStream.flush();
        } finally {            
            dataOutputStream.close();
            socket.close();
        }
        
    }
}
