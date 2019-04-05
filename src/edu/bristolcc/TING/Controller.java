package edu.bristolcc.TING;

import java.io.File;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import static java.lang.Thread.State.NEW;
import static java.lang.Thread.State.TIMED_WAITING;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    
    public boolean simulationStatus;
    
    Controller controller;
    private MainWindow window;
    private Visitor visitor= new Visitor(this);
    public Elevator elevator = new Elevator(this);
    private Scenario scenario = new Scenario(this);
    public ThreadAnimation animationThread = new ThreadAnimation(this);
    Thread th = new Thread(animationThread);
    
    public void setWindow(MainWindow window) {
        this.window = window;
    }//setWindow
    
    public MainWindow getWindow() {
        return window;
    }//getWindow
    
    public void startAnimation() {
        simulationStatus = true;
        if (((getAmount() == 0 && th.getState().equals(TIMED_WAITING)) || getAmount() != 0) && (!th.getState().equals(NEW))) {
            th.resume();
        } else {
            th.start();
        }
    }//startAnimation

    public void resetAnimation() {
        simulationStatus = false;
        th.suspend();
        setAmount(0);
    }//resetAnimation

    public void pauseAnimation() {
        simulationStatus = false;
        th.suspend();
    }//stopAnimation

    public void animate() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                window.update();
            }
        });
    }//animate

    public void setAmount(int amount) {
        elevator.setAmount(amount);
    }//setAmount
    
    public int getAmount() {
        return elevator.getAmount();
    }//getAmount
    
    public int getTESTVAR() {
        return elevator.getTESTVAR();
    }//getAmount
    
    public void saveScenario(JTable table, File file) {
        //scenario.saveScenario(table, file);
    }//saveScenario

    public void loadScenario(JTable table, File file) {
        //scenario.loadScenario(table, file);
    }//loadScenario
    
    //function for moving elevator up and down
    public void moveElevator(TableModel model, Statistics pnlStats) {
        elevator.moveElevator(model,pnlStats);
    }//moveElevator

    public void addVisitorToElevator(TableModel model) {
        System.out.println("test controller call add");
        visitor.addVisitorToElevator(model, getAmount(), getTESTVAR()/*, controller*/);
    }//addVisitorToElevator
    
    public void removeVisitorFromElevator(TableModel model) {
        System.out.println("test controller call remove");
        visitor.removeVisitorFromElevator(model, getAmount(), getTESTVAR()/*, controller*/);
    }//removeVisitorToElevator
    
    private static List<Integer> columns = new ArrayList<>();
    
    public void getColumnIndices(TableModel model) {
        int maxColumns = model.getColumnCount();
        for (int idx = 0; idx < maxColumns; ++idx) {
            columns.add(idx);
        }
    }//getColumnIndices

}//class ControllerDemo
