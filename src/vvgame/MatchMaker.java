/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vvgame;

import java.util.List;

/**
 *
 * @author Mef
 */
public class MatchMaker {
    private List<Client> clientList;
    
    public void addClient(Client client) {
        clientList.add(client);
    }

}
