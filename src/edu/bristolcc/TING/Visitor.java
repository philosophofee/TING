package edu.bristolcc.TING;

public class Visitor {

    public int MY_FLOOR;
    public int DESTINATION; 
            
    public Visitor(int myFloor, int destination) {
        this.MY_FLOOR = myFloor;
        this.DESTINATION = destination;
        System.out.println("A visitor on floor " + MY_FLOOR + "+1" + " ,wants to go to floor " + DESTINATION + "+1");
    }//Visitor
    
}//Class Visitor
