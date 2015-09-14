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
public class Executor {
    private final List<Reciever> receiverList;
    
    private Executor() {
        receiverList = new LinkedList<>();
    }
    
    public Executor add(Reciever receiver) {
        receiverList.add(receiver);
        return this;
    }
    
    @SuppressWarnings("empty-statement")
    public void execute(int code, Object object, Client client) {
        for( int i = 0; i < receiverList.size() && !receiverList.get(i).recieveData(code, object, client); i++);
    }
    
    public Executor remove(Reciever reciever) {
        receiverList.remove(reciever);
        return this;
    }
    
    public static Executor newInstance() {
        return new Executor();
    }
}
