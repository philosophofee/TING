package edu.bristolcc.TING;

import java.util.ArrayList;

public class ElevatorBank 
{
    public ArrayList<Elevator> MY_ELEVATORS = new ArrayList();
    public int ELEVATOR_COUNT;
    
    public ElevatorBank(int elevatorCount) {
        this.ELEVATOR_COUNT = elevatorCount;
    }
    
    public void initiateMePlease(int elevatorCount) {
        for (int i=0; i<ELEVATOR_COUNT; i++) {
            MY_ELEVATORS.add(new Elevator(8, i));
        }
    }
    public void tick() {
        for (Elevator elevator : MY_ELEVATORS) {
            //to be continued
        }
    }
}//Class ElevatorBank
