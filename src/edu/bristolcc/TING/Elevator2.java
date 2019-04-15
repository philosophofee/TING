package edu.bristolcc.TING;

import java.util.ArrayList;

public class Elevator2 {
    
    public volatile int CURRENT_FLOOR;
    public int MAX_FLOOR;
    public int MY_CAPACITY;
    public int MY_IDENTIFIER; //column the elevator resides in
    //public boolean IS_AVAILABLE;
    
    public ArrayList<Visitor> PASSENGERS = new ArrayList();
    
    public Elevator2(int capacity, int ID, int startingFloor, int maxFloor/*, boolean isAvailable*/) {
        this.MY_CAPACITY = capacity;
        this.MY_IDENTIFIER = ID;
        this.CURRENT_FLOOR = startingFloor;
        this.MAX_FLOOR = maxFloor;
        //this.IS_AVAILABLE = isAvailable;
        /*System.out.println("hello from elevator " + MY_IDENTIFIER + ". "
                + "I currently have " + PASSENGERS.size() + " passengers "
                + "and I am on floor " + CURRENT_FLOOR);*/
    }//Elevator2
    
    public int getPassengerCount() {
        return PASSENGERS.size();
    }//getPassengerCount
    
    public ArrayList<Visitor> getPassengersArray() {
        return PASSENGERS;
    }//getPassengersArray
    
    public void swipeVisitorOn(Visitor visitor) {
        visitor.MY_STATUS = VisitorStatus.RIDING;
        PASSENGERS.add(visitor);
    }//swipeVisitorOn
    
    public void swipeVisitorOff(Visitor visitor) {
        visitor.MY_STATUS = VisitorStatus.ARRIVED;
        PASSENGERS.remove(visitor);
    }//swipeVisitorOff
  
    public void moveFloor(int amount) {
        if (CURRENT_FLOOR+amount>=0 && CURRENT_FLOOR+amount<=MAX_FLOOR) {
            CURRENT_FLOOR+=amount;
        }
    }//moveFloor
    
    public int getMyFloor() {
        return CURRENT_FLOOR;
    }//getMyFloor
    
    public void tick() {
        //System.out.println("tick from elevator " + MY_IDENTIFIER + ". I currently have " + PASSENGERS.size() + " passengers and I am on floor " + CURRENT_FLOOR);
        for (Visitor visitor : PASSENGERS) {
            visitor.tick();
        }
    }//tick
    
}//class Elevator2
