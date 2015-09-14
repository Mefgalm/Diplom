/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Mef
 */
class Executore {
    List<A> aList;
    
    public Executore() {
        aList = new LinkedList<>();
    }
    
    public void add(A a) {
        aList.add(a);
    }
    
    @SuppressWarnings("empty-statement")
    public void execute(int code) {
        for( int i = 0; i < aList.size() && !aList.get(i).recieve(code); i++);
    }
}

abstract class A {
    protected String name;
    
    public A(String name) {
        this.name = name;
    }   
    
    abstract boolean recieve(int code);
}

class A1 extends A {

    public A1(String name) {
        super(name);
    }

    @Override
    boolean recieve(int code) {
        switch(code) {
            case 0:
            case 1:
                System.out.println(name);
                return true;
        }
        return false;
    }    
}


class A2 extends A {

    public A2(String name) {
        super(name);
    }

    @Override
    boolean recieve(int code) {
        switch(code) {
            case 2:
            case 3:
                System.out.println(name);
                return true;
        }
        return false;
    }    
}

public class Test {   
    public static void main(String[] args) {
        Executore executore = new Executore();
        executore.add(new A1("v"));
        executore.add(new A2("l"));
        executore.execute(2);
        
    }
}
