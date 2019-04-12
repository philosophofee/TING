package edu.bristolcc.TING;

import java.util.ArrayList;

public class FloorBank {

    public int FLOOR_MAX;
    private ArrayList<Floor> MY_FLOORS = new ArrayList();
    
    public void instantiate(int floors) {
        this.FLOOR_MAX = floors;
        for (int i=0; i < FLOOR_MAX; i++) {
            MY_FLOORS.add(new Floor(i));
        }
    }//instantiate
    
    public ArrayList<Floor> getFloorsArray() {
        return MY_FLOORS;
    }//getFloorsArray
    
}//class FloorBank
