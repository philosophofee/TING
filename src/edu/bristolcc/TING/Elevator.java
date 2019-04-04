package edu.bristolcc.TING;

public class Elevator 
{
    public int ELEVATOR_CAPACITY;
    public int DX; //Elevator ID
    public int DY; //Floor ID
    
    public Elevator(int capacity, int identifier) {
        this.ELEVATOR_CAPACITY = capacity; //number of visitors allowed in elevator
        this.DX = identifier; //A, B, C, D...
    }
    
    public int getCapacity() {
        return ELEVATOR_CAPACITY;
    }
    
    public int getFloor() {
        return DY; //Floor ID
    }
    
    public void moveMePlease(int where) {
        this.DY+=where; //move elevator one space vertically, either up or down.
        //theoretically you could make this 5 and your elevator would warp
        //cool
    }
    
    
    
    
    
}//Class Elevator
