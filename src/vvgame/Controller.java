/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vvgame;

import LinkObjects.UserData;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mef
 */
public class Controller implements Reciever {

    private final List<Client> clientList;
    private MatchMaker matchMaker;

    public Controller() {
        clientList = new LinkedList<>();
        matchMaker = new MatchMaker();
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void addClient(Client client) {
        clientList.add(client);
        client.setExecutor(Executor.newInstance().add(this));
        client.startThread();
    }

    @Override
    public boolean recieveData(int code, Object data, Client client) {
        switch (code) {
            case CONSTANS.CONTROLLER_REQUEST_USER_DATA:
                client.setUserData((UserData) data);
                System.out.println("Client = " + client + " set data = " + data);
                return true;
            case CONSTANS.CONTROLLER_REQUEST_FIND_MATCH:
                matchMaker.addClient(client);
                clientList.remove(client);   
                System.out.println("Client = " + client + " used match maker");
                return true;
        }
        return false;
    }
}
