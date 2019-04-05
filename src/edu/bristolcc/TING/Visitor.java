package edu.bristolcc.TING;

import javax.swing.table.TableModel;

public class Visitor{    
    Controller controller;
    
    public Visitor(Controller controller){
        this.controller = controller;
    }//Visitor
    
    public void addVisitorToElevator(TableModel model, int amount, int TESTVAR) {
        try {
            try {
                if (amount < 10) {
                    if(controller != null){
                        System.out.println("test Visitor call add");
                    controller.pauseAnimation();
                    amount = (int) model.getValueAt(TESTVAR, 1);
                    ++amount;

                    model.setValueAt(amount, TESTVAR, 1);

                    controller.startAnimation();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                controller.startAnimation();
                amount = (int) model.getValueAt(TESTVAR, 1);
            }
        } catch (NullPointerException ex) {
            controller.startAnimation();
        }
    }//addVisitorToElevator

    public void removeVisitorFromElevator(TableModel model, int amount, int TESTVAR) {
        try {
            try {
                if (amount > 0) {
                    System.out.println("test Visitor call remove");
                    controller.pauseAnimation();
                    amount = (int) model.getValueAt(TESTVAR, 1);
                    --amount;

                    model.setValueAt(amount, TESTVAR, 1);

                    controller.startAnimation();
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                controller.startAnimation();
                amount = (int) model.getValueAt(TESTVAR, 1);
            }
        } catch (NullPointerException ex) {
            controller.startAnimation();
        }

    }//removeVisitorToElevator
    
}//Class Visitor