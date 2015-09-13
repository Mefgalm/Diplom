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
        clientList.add(client);
    }

    @Override
    public void recieveData(int code, Object object, Client client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
