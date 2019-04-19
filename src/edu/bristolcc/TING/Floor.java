package edu.bristolcc.TING;

import java.util.ArrayList;
import java.util.Random;

public class Floor {

    public int FLOOR_LEVEL; //the level that this floor resides in
    public int DESTINATION; //the level the visitor would like to go to
    public ArrayList<Visitor> visitorsArray = new ArrayList(); //to contain visitors

    public Floor(int level) {
        this.FLOOR_LEVEL = level;
        //System.out.println("Floor " + FLOOR_LEVEL + ". Has " + visitorsArray.size() + " visitors.");
    }//Floor

    public int getVisitorCount() {
        return visitorsArray.size();
    }//getVisitorCount

    public ArrayList<Visitor> getVisitorsArray() {
        return visitorsArray;
    }//getVisitorsArray

    public void giveVisitorToElevator(Visitor visitor) {
        visitorsArray.remove(visitor);
    }//giveVisitorToElevator

    public void recieveVisitorFromElevator(Visitor visitor) {
        visitorsArray.add(visitor);
    }//giveVisitorToElevator

    public void addVisitorToFloor(int MAX_FLOORS) {
        Random rand = new Random();
        DESTINATION = rand.nextInt(((MAX_FLOORS - 1) - 0) + 1) + 0;

        if (DESTINATION != FLOOR_LEVEL) {
            Visitor newVisitor = new Visitor(FLOOR_LEVEL, DESTINATION);
            visitorsArray.add(newVisitor);
        }
    }//addVisitorToFloor

}//class Floor
