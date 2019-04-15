package edu.bristolcc.TING;

import java.util.ArrayList;

public class ElevatorBank {

    public ArrayList<Elevator2> MY_ELEVATORS = new ArrayList();
    public int ELEVATOR_COUNT;
    public int MAX_FLOORS;
    //public boolean IS_AVAILABLE;

    public ElevatorBank(int elevatorCount, int maxFloor/*, boolean isAvailable*/) {
        this.ELEVATOR_COUNT = elevatorCount;
        this.MAX_FLOORS = maxFloor - 1; //for tables sake
        //this.IS_AVAILABLE = isAvailable;
        System.out.println("Hello from elevator bank. It appears I will need to create " + ELEVATOR_COUNT + " elevators\n");

        for (int i = 0; i < ELEVATOR_COUNT; i++) {
            Elevator2 elevatorToAdd = new Elevator2(8, i, MAX_FLOORS, MAX_FLOORS/*, IS_AVAILABLE*/); //adding to the bottom
            MY_ELEVATORS.add(elevatorToAdd);
        }
    }//ElevatorBank

    public ArrayList<Elevator2> getElevatorsArray() {
        return MY_ELEVATORS;
    }//getElevatorsArray

    public void moveElevator(int identifier, int amount) {
        MY_ELEVATORS.get(identifier).moveFloor(amount);
    }//moveElevator

    /*public void elevatorIsAvaliable() {
        for (int i = 0; i < ELEVATOR_COUNT; ++i) {
            if ((MY_ELEVATORS.get(i).getPassengerCount() >= 0) && (MY_ELEVATORS.get(i).getPassengerCount() < MY_ELEVATORS.get(i).MY_CAPACITY)) {
                MY_ELEVATORS.get(i).IS_AVAILABLE = true;
                System.out.println("Elevator: " + MY_ELEVATORS.get(i).MY_IDENTIFIER + " = " + MY_ELEVATORS.get(i).IS_AVAILABLE);
            } else {
                MY_ELEVATORS.get(i).IS_AVAILABLE = false;
                System.out.println("Elevator: " + MY_ELEVATORS.get(i).MY_IDENTIFIER + " = " + MY_ELEVATORS.get(i).IS_AVAILABLE);
            }
        }
    }//elevatorIsAvaliable*/

    public void tick() {
        for (Elevator2 elevator : MY_ELEVATORS) {
            elevator.tick();
        }
    }//tick

}//Class ElevatorBank
