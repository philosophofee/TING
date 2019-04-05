package edu.bristolcc.TING;

import java.util.ArrayList;

public class Floor 
{
    public int FLOOR_LEVEL; //the level that this floor resides in
    public ArrayList<Visitor> visitorsArray = new ArrayList(); //to contain visitors
    
    public Floor(int level) {
        this.FLOOR_LEVEL = level;
        System.out.println("Hello from floor " + FLOOR_LEVEL + ". we currently have " + visitorsArray.size() + " visitors.");
    }
    
    public int getVisitorCount() {
        return visitorsArray.size();
    }
    
    public ArrayList<Visitor> getVisitorsArray() {
        return visitorsArray;
    }
    
    
    public void addVisitor() {
        Visitor newVisitor = new Visitor(FLOOR_LEVEL);
        visitorsArray.add(newVisitor);
    }
}//Class Floor
