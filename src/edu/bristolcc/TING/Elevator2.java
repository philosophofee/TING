package edu.bristolcc.TING;

import java.io.Serializable;
import java.util.ArrayList;

enum ElevatorStatus {

    IDLE, LOADING, MOVE_UP, MOVE_DOWN, STOPPED;
}

public class Elevator2 implements Serializable {

    public volatile int CURRENT_FLOOR;
    public int MAX_FLOOR;
    public int MY_CAPACITY;
    public int MY_IDENTIFIER; //column the elevator resides in
    public ElevatorStatus MY_STATUS;

    public ArrayList<Visitor> PASSENGERS = new ArrayList();
    public final String MY_NAME;

    public Elevator2(int capacity, int ID, int startingFloor, int maxFloor) {
        this.MY_CAPACITY = capacity;
        this.MY_IDENTIFIER = ID;
        this.CURRENT_FLOOR = startingFloor;
        this.MAX_FLOOR = maxFloor;
        this.MY_STATUS = ElevatorStatus.IDLE;
        
        this.MY_NAME = this.toString().replace("edu.bristolcc.TING.Visitor@", "");
        /*System.out.println("hello from elevator " + MY_IDENTIFIER + ". "
         + "I currently have " + PASSENGERS.size() + " passengers "
         + "and I am on floor " + CURRENT_FLOOR);*/
    }//Elevator2

    public ElevatorStatus getMY_STATUS() {
        return MY_STATUS;
    }//getMY_STATUS

    public void setMY_STATUS(ElevatorStatus MY_STATUS) {
        this.MY_STATUS = MY_STATUS;
    }//setMY_STATUS

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
        if (CURRENT_FLOOR + amount >= 0 && CURRENT_FLOOR + amount <= MAX_FLOOR) {
            CURRENT_FLOOR += amount;
        }
    }//moveFloor

    public int getMyFloor() {
        return CURRENT_FLOOR;
    }//getMyFloor

    public void setMyFloor(int floor) {
        this.CURRENT_FLOOR = floor;
    }//getMyFloor

    public void tick() {
        //System.out.println("tick from elevator " + MY_IDENTIFIER + ". I currently have " + PASSENGERS.size() + " passengers and I am on floor " + CURRENT_FLOOR);
        for (Visitor visitor : PASSENGERS) {
            System.out.println("Passenger " + visitor.MY_NAME + "(wants " + visitor.DESTINATION + "): Floor " + visitor.MY_FLOOR + ". State: " + visitor.MY_STATUS);
            //visitor.tick();
        }
    }//tick

}//class Elevator2
