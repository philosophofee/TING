package edu.bristolcc.TING;

import java.util.ArrayList;

public class Floor 
{
    public int FLOOR_LEVEL; //the level that this floor resides in
    public ArrayList<Visitor> visitorsArray = new ArrayList(); //to contain visitors
    Elevator2 elevator;
    
    public Floor(int level) {
        this.FLOOR_LEVEL = level;
        System.out.println("Hello from floor " + FLOOR_LEVEL + ". we currently have " + visitorsArray.size() + " visitors.");
    }//Floor
    
    public int getVisitorCount() {
        return visitorsArray.size();
    }//getVisitorCount
    
    public ArrayList<Visitor> getVisitorsArray() {
        return visitorsArray;
    }//getVisitorsArray
    
    public void giveVisitorToElevator(Visitor visitor){
        visitorsArray.remove(visitor);
    }//giveVisitorToElevator
    
    
    public void addVisitorToFloor() {
        Visitor newVisitor = new Visitor(FLOOR_LEVEL);
        visitorsArray.add(newVisitor);
    }//addVisitorToFloor
    
}//class Floor
