package edu.bristolcc.TING;

import java.util.ArrayList;

public class ElevatorBank {

    public ArrayList<Elevator2> MY_ELEVATORS = new ArrayList();
    public int ELEVATOR_COUNT;
    public int MAX_FLOORS;

    public ElevatorBank(/*int elevatorCount,*/ int maxFloor) {
        //this.ELEVATOR_COUNT = elevatorCount;
        this.MAX_FLOORS = maxFloor - 1; //for tables sake
        //System.out.println("Hello from elevator bank. It appears I will need to create " + ELEVATOR_COUNT + " elevators\n");
        
        /*for (int i = 0; i < ELEVATOR_COUNT; i++) {
            //Elevator2 elevatorToAdd = new Elevator2(8, i, MAX_FLOORS, MAX_FLOORS); //adding to the bottom
            MY_ELEVATORS.add(new Elevator2(8, i, MAX_FLOORS, MAX_FLOORS));
        }*/
    }//ElevatorBank

    public void instantiate(int elevators, int STARTING_FLOOR) {
        ELEVATOR_COUNT = elevators;
        for (int i = 0; i < ELEVATOR_COUNT; i++) {
            MY_ELEVATORS.add(new Elevator2(8, i, STARTING_FLOOR, MAX_FLOORS));
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
            elevator.tick();
        }
    }//tick

}//Class ElevatorBank
