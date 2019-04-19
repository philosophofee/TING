package edu.bristolcc.TING;

import java.util.ArrayList;

public class ElevatorBank {

    public ArrayList<Elevator2> MY_ELEVATORS = new ArrayList();
    public int ELEVATOR_COUNT;
    //public int MAX_FLOORS;

    public ElevatorBank(/*int elevatorCount, int maxFloor*/) {
        //this.ELEVATOR_COUNT = elevatorCount;
        //this.MAX_FLOORS = maxFloor - 1; //for tables sake
        //System.out.println("Hello from elevator bank. It appears I will need to create " + ELEVATOR_COUNT + " elevators\n");
        
    }//ElevatorBank

    public void instantiate(int elevators, int STARTING_FLOOR) {
        ELEVATOR_COUNT = elevators;
        
        for (int i = 0; i < ELEVATOR_COUNT; i++) {
            MY_ELEVATORS.add(new Elevator2(8, i, STARTING_FLOOR, STARTING_FLOOR));
        }
    }

    public ArrayList<Elevator2> getElevatorsArray() {
        return MY_ELEVATORS;
    }//getElevatorsArray

    public void moveElevator(int identifier, int amount) {
        MY_ELEVATORS.get(identifier).moveFloor(amount);
    }//moveElevator

    public void tick() {
        
        for (Elevator2 elevator : MY_ELEVATORS) {
            System.out.println("Elevator" + elevator.MY_IDENTIFIER + "(" + elevator.getPassengerCount() + ") : Floor " + elevator.CURRENT_FLOOR + ". State: " + elevator.MY_STATUS);
            elevator.tick();
        }
    }//tick

}//Class ElevatorBank
