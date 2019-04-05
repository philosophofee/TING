package edu.bristolcc.TING;

import java.util.ArrayList;

public class FloorBank {

    public int FLOOR_COUNT;
    private ArrayList<Floor> MY_FLOORS = new ArrayList();
    
    public void instantiate(int floors) {
        this.FLOOR_COUNT = floors;
        for (int i=0; i<FLOOR_COUNT; i++) {
            MY_FLOORS.add(new Floor(i));
        }
    }
    
    public ArrayList<Floor> getFloorsArray() {
        return MY_FLOORS;
    }
    
}
