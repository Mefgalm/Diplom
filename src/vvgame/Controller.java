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
public class Controller {
    private final List<Client> clientList;
    
    public Controller() {
        clientList = new LinkedList<>();
    }
    
    public void daMany() {
        
    }

    public List<Client> getClientList() {
        return clientList;
    }    
    
    public void addClient(Client client) {
        clientList.add(client);
    }
}
