/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vvgame;

import LinkObjects.UserData;
import Tools.SerialazibleMessage;
import Tools.SerialazibleTools;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mef
 */
public class Client {

    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;

    private final InetAddress address;
    private final int port;
    private final int localport;

    private UserData userData;

    private final List<Reciever> recieverCoreList;
    
    private Executor executor;

    public Client(Socket socket) throws IOException {
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
        this.socket = socket;

        address = socket.getInetAddress();
        port = socket.getPort();
        localport = socket.getLocalPort();

        recieverCoreList = new LinkedList<>();
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void sendData(byte[] data) throws IOException {
        outputStream.write(data);
    }
    
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    class ClientThread implements Runnable {

        private boolean isActive;
        private byte[] data;
        private int byteCountReceived;

        public ClientThread() {
            this.isActive = true;
            data = new byte[CONSTANS.BYTE_BUFFER_SIZE];
        }

        public void closeStreams() {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        @Override
        public void run() {
            while (isActive) {
                try {
                    inputStream.read(data);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    isActive = false;
                    closeStreams();
                } finally {
                    try {
                        SerialazibleMessage sm = SerialazibleTools.getSerialazibleMessage(data);
                        executor.execute(sm.getCode(), sm.getObject(), Client.this);
                    } catch (IOException | ClassNotFoundException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Arrays.fill(data, (byte) 0);
                }
            }
        }
    }

    public Executor getExecutor() {
        return executor;
    }
        
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public void registerNewReciever(Reciever reciever) {
        recieverCoreList.add(reciever);
    }

    @Override
    public String toString() {
        return "Client{" + ", address=" + address + ", port=" + port + ", localport=" + localport + '}';
    }

    public void startThread() {
        ClientThread clientThread = new ClientThread();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(clientThread);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.inputStream);
        hash = 61 * hash + Objects.hashCode(this.outputStream);
        hash = 61 * hash + Objects.hashCode(this.socket);
        hash = 61 * hash + Objects.hashCode(this.address);
        hash = 61 * hash + this.port;
        hash = 61 * hash + this.localport;
        hash = 61 * hash + Objects.hashCode(this.userData);
        hash = 61 * hash + Objects.hashCode(this.recieverCoreList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.inputStream, other.inputStream)) {
            return false;
        }
        if (!Objects.equals(this.outputStream, other.outputStream)) {
            return false;
        }
        if (!Objects.equals(this.socket, other.socket)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (this.port != other.port) {
            return false;
        }
        if (this.localport != other.localport) {
            return false;
        }
        if (!Objects.equals(this.userData, other.userData)) {
            return false;
        }
        if (!Objects.equals(this.recieverCoreList, other.recieverCoreList)) {
            return false;
        }
        return true;
    }        
}
