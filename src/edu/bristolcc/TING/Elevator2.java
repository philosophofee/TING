package edu.bristolcc.TING;

import java.util.ArrayList;

public class Elevator2 {
    
    public volatile int CURRENT_FLOOR;
    public int MAX_FLOOR;
    public int MY_CAPACITY;
    public int MY_IDENTIFIER; //column the elevator resides in
    
    public ArrayList<Visitor> PASSENGERS = new ArrayList();
    
    public Elevator2(int capacity, int ID, int startingFloor, int maxFloor) {
        this.MY_CAPACITY = capacity;
        this.MY_IDENTIFIER = ID;
        this.CURRENT_FLOOR = startingFloor;
        this.MAX_FLOOR = maxFloor;
        System.out.println("hello from elevator " + MY_IDENTIFIER + ". "
                + "I currently have " + PASSENGERS.size() + " passengers "
                + "and I am on floor " + CURRENT_FLOOR);
    }
    
    public int getPassengers() {
        return PASSENGERS.size();
    }
    
    public void swipeVisitor(Visitor visitor) {
        PASSENGERS.add(visitor);
    }
    
    public void moveFloor(int amount) {
        if (CURRENT_FLOOR+amount>=0 && CURRENT_FLOOR+amount<=MAX_FLOOR) {
            CURRENT_FLOOR+=amount;
        }
    }
    
    public int getMyFloor() {
        return CURRENT_FLOOR;
    }
    
    public void tick() {
        System.out.println("tick from elevator " + MY_IDENTIFIER + ". I currently have " + PASSENGERS.size() + " passengers and I am on floor " + CURRENT_FLOOR);
    }
    
}
