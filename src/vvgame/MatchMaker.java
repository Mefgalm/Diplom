/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vvgame;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mef
 */
public class MatchMaker implements Reciever {
    private List<Client> clientList;
    
    public MatchMaker() {
        clientList = new LinkedList<>();
        
    }
    
    public void addClient(Client client) {
        client.getExecutor().add(this);
        clientList.add(client);        
    }

    @Override
    public boolean recieveData(int code, Object object, Client client) {
        switch(code) {
            case 99:
                System.out.println("Match Maker");
                //client.getExecutor().remove(this);
                return true;
        }
        return false;
    }

}
