/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mef
 */
public class Test {

    static ConcurrentLinkedQueue<Integer> clq = new ConcurrentLinkedQueue<>();
    static LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<>();
    static List<Integer> intList = new LinkedList<>();

    static class Runn implements Runnable {

        @Override
        public void run() {
            while (true) {
                Integer head = lbq.poll();
                System.out.println(head);
                head++;
                try {
                    lbq.put(head);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        intList.add(0);
        clq.add(0);
        lbq.add(0);
    }
}
