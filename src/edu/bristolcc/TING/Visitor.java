package edu.bristolcc.TING;

public class Visitor {

    public int MY_FLOOR;
    
    public Visitor(int myFloor) {
        this.MY_FLOOR = myFloor;
        System.out.println("Hello from a visitor on level " + MY_FLOOR);
    }//Visitor
    
}//Class Visitor
