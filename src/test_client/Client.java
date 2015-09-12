/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import vvgame.CONSTANS;
import vvgame.Controller;

/**
 *
 * @author Mef
 */
public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", CONSTANS.PORT);
        BufferedOutputStream dataOutputStream = new BufferedOutputStream(socket.getOutputStream());
        BufferedInputStream dataInputStream = new BufferedInputStream(socket.getInputStream());
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
                     
        }
         Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isAlive = true;
                int byteCount = 0;
                byte[] b = new byte[100];
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
