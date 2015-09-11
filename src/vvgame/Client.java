/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vvgame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

/**
 *
 * @author Mef
 */
public class Client {

    private InputStream inputStream;
    private OutputStream outputStream;
    private String nickname;

    public Client(Socket socket) throws IOException {
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
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

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.inputStream);
        hash = 67 * hash + Objects.hashCode(this.outputStream);
        hash = 67 * hash + Objects.hashCode(this.nickname);
        return hash;
    }

    public void sendData(byte[] data) throws IOException {
        outputStream.write(data);
    }

    public byte[] receiveData() {
        byte[] b = new byte[1000];
        new Thread(() -> {
            boolean isAlive = true;
            while (isAlive) {
                try {
                    inputStream.read(b);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    isAlive = false;
                } finally {
                    isAlive = false;
                }
            }
        }).start();
        return b;
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
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        return true;
    }
}
