package edu.bristolcc.TING;

import java.util.ArrayList;

public class FloorBank {

    public int FLOOR_MAX;
    private ArrayList<Floor> MY_FLOORS = new ArrayList();

    public void instantiate(int floors) {
        this.FLOOR_MAX = floors;
        for (int i = 0; i < FLOOR_MAX; i++) {
            MY_FLOORS.add(new Floor(i));
        }
    }//instantiate

    public ArrayList<Floor> getFloorsArray() {
        return MY_FLOORS;
    }//getFloorsArray

    public void tick() {
        for (Floor floor : MY_FLOORS) {
            for (Visitor visitor : floor.getVisitorsArray()) {
                
                System.out.println("Visitor " + visitor.MY_NAME + "(wants " + visitor.DESTINATION + "): Floor " + visitor.MY_FLOOR + ". State: " + visitor.MY_STATUS);
                //visitor.tick();
            }
        }
    }

}//class FloorBank
