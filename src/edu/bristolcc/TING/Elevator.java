package edu.bristolcc.TING;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableModel;

public class Elevator 
{
    private Controller controller;
    
    public int TESTVAR = 0;
    public boolean GOINGUP = false;
    public int amount = 0;
    private static List<Double> scores = new ArrayList<>();
    private static List<Integer> columns = new ArrayList<>();
    
    public Elevator(Controller controller){
        this.controller = controller;
    }//Elevator
    
    //function for moving elevator up and down
    public void moveElevator(TableModel model, Statistics pnlStats) {
        
        int maxRows = model.getRowCount();

        for (int firstRow = 0; firstRow < maxRows; ++firstRow) {
            model.setValueAt(null, firstRow, 1);
        }

        if (GOINGUP == false) {
            if (TESTVAR < maxRows - 1) {
                TESTVAR++;
            }
        }
        if (GOINGUP == true) {
            if (TESTVAR > 0) {
                TESTVAR--;
            }
        }
        if (TESTVAR == maxRows - 1 && GOINGUP == false) {
            GOINGUP = true;
        }
        if (TESTVAR == 0 && GOINGUP == true) {
            GOINGUP = false;
        }

        model.setValueAt(amount, TESTVAR, 1);

        //adds amount of visitors to arraylist and updates view statistics graph when simulation is running
        scores.add((double) amount);
        pnlStats.setScores(scores);
    }//moveElevator

    //function to stop elevator and makes a call to add a visitor
    public void stopElevator() {
        controller.pauseAnimation();

    }//stopElevator
    
    public void setAmount(int amount) {
        this.amount = amount;
    }//setAmount
    
    public int getAmount() {
        return amount;
    }//getAmount
    
    public int getTESTVAR() {
        return TESTVAR;
    }//getTESTVAR
    
}//Class Elevator
