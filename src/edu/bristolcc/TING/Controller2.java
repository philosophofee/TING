package edu.bristolcc.TING;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Thread.State.NEW;
import static java.lang.Thread.State.TIMED_WAITING;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class Controller2 implements Serializable{

    public boolean simulationStatus;

    FloorBank floorBank = new FloorBank();
    ElevatorBank elevatorBank = new ElevatorBank();
    
    private TestWindow window;
    private JTable bigTable;
    public ThreadAnimation2 animationThread = new ThreadAnimation2(this);
    Thread th = new Thread(animationThread);

    public void setWindow(TestWindow window) {
        this.window = window;
    }//setWindow

    public TestWindow getWindow() {
        return window;
    }//getWindow
    
    public void setTable(JTable bigTable) {
        this.bigTable = bigTable;
    }//setTable
    
    public void instantiateFloor() {
        floorBank.instantiate(bigTable.getModel().getRowCount());
    }

    public void instantiateElevator() {
        elevatorBank.instantiate(bigTable.getModel().getColumnCount(), bigTable.getModel().getRowCount() - 1);
        System.out.println("\nTICK " + giveCount() + "\n----------");
        for (int i = 0; i < elevatorBank.getElevatorsArray().size(); ++i) {
            System.out.println("Elevator " + elevatorBank.getElevatorsArray().get(i).MY_IDENTIFIER + " (" + elevatorBank.getElevatorsArray().get(i).getPassengerCount() + "): Floor " + elevatorBank.getElevatorsArray().get(i).CURRENT_FLOOR + ". State: " + elevatorBank.getElevatorsArray().get(i).MY_STATUS);
        }
    }

    public void startAnimation() {
        simulationStatus = true;
        if (((animationThread.getCount() == 0 && th.getState().equals(TIMED_WAITING)) || animationThread.getCount() != 0) && (!th.getState().equals(NEW))) {
            th.resume();
        } else {
            th.start();
        }
    }//startAnimation

    public void pauseAnimation() {
        simulationStatus = false;
        th.suspend();
    }//stopAnimation

    public void resetAnimation() {
        simulationStatus = false;
        th.suspend();
        animationThread.setCount(0);
        
    }//resetAnimation
    
    public void animate() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                window.update();
            }
        });
    }//animate

    public int giveCount() {
        return animationThread.getCount();
    }

    public void elevatorTick() {
        elevatorBank.tick();
    }

    public void floorTick() {
        floorBank.tick();
    }

    public void simulation() {
        //move visitor onto elevator if elevator is IDLE meaning ground floor and change state to MOVE_UP
        for (int e = 0; e < elevatorBank.getElevatorsArray().size(); ++e) {
            if (elevatorBank.getElevatorsArray().get(e).MY_STATUS == ElevatorStatus.IDLE) {
                for (int v = 0; v < floorBank.getFloorsArray().get(bigTable.getRowCount() - 1).getVisitorCount(); ++v) {
                    if (floorBank.getFloorsArray().get(bigTable.getRowCount() - 1).getVisitorsArray().get(v).MY_STATUS == VisitorStatus.WAITING) {
                        moveOnToElevator(bigTable.getRowCount() - 1, v, e);
                        updateTable();
                        elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.MOVE_UP);
                    }
                }
            }
        }

        //move elevator up by 1 each tick
        for (int e = 0; e < elevatorBank.getElevatorsArray().size(); ++e) {
            if ((elevatorBank.getElevatorsArray().get(e).getPassengerCount() > 0) && (elevatorBank.getElevatorsArray().get(e).getPassengerCount() <= elevatorBank.getElevatorsArray().get(e).MY_CAPACITY)) {
                if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR == floorBank.getFloorsArray().get(bigTable.getRowCount() - 1).FLOOR_LEVEL) {
                    elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.MOVE_UP);
                }
                if ((elevatorBank.getElevatorsArray().get(e).MY_STATUS == ElevatorStatus.MOVE_UP)) {
                    int MOVE_UP = -1;
                    elevatorBank.moveElevator(e, MOVE_UP);
                    updateTable();
                }
            }
        }

        //move passenger off of elevator and if passenger count equals 0 change state to MOVE_DOWN
        for (int e = 0; e < elevatorBank.getElevatorsArray().size(); ++e) {
            for (int k = 0; k < elevatorBank.getElevatorsArray().get(e).getPassengerCount(); ++k) {
                if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR == elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(k).DESTINATION) {

                    boolean isStopped = false;

                    if (elevatorBank.getElevatorsArray().get(e).MY_STATUS == ElevatorStatus.STOPPED) {

                        moveOffOfElevator(k, e);
                        updateTable();
                        isStopped = true;
                        elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.MOVE_DOWN);
                        //if (elevatorBank.getElevatorsArray().get(e).getPassengersArray().size() > 0) {
                            //if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR > elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(k).DESTINATION) {
                                //elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.MOVE_UP);
                            //} else if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR < elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(k).DESTINATION) {
                                //elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.MOVE_DOWN);
                            //} else {
                                //elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.MOVE_DOWN);
                            //}
                        //}
                    }
                    if (isStopped == false) {
                        elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.STOPPED);
                    }
                }
            }
        }

        //move elevator down by 1 each tick and if the elevator hits ground floor change state to IDLE
        for (int e = 0; e < elevatorBank.getElevatorsArray().size(); ++e) {
            if (elevatorBank.getElevatorsArray().get(e).getPassengerCount() == 0) {
                if (elevatorBank.getElevatorsArray().get(e).MY_STATUS == ElevatorStatus.MOVE_DOWN) {
                    if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR != floorBank.getFloorsArray().get(bigTable.getRowCount() - 1).FLOOR_LEVEL) {
                        int MOVE_DOWN = 1;
                        elevatorBank.moveElevator(e, MOVE_DOWN);
                        updateTable();
                        if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR == floorBank.getFloorsArray().get(bigTable.getRowCount() - 1).FLOOR_LEVEL) {
                            elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.IDLE);
                        }
                    }
                }
            } else { //THIS IS A TEMPORARY FIX FOR ELEVATOR GETTING STUCK AT TOP FLOOR WITH PASSENGER STILL ON IT
                if ((elevatorBank.getElevatorsArray().get(e).MY_STATUS == ElevatorStatus.MOVE_DOWN) && (elevatorBank.getElevatorsArray().get(e).MY_STATUS == ElevatorStatus.MOVE_DOWN) && (elevatorBank.getElevatorsArray().get(e).getPassengerCount() > 0)) {
                    if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR != floorBank.getFloorsArray().get(bigTable.getRowCount() - 1).FLOOR_LEVEL) {
                        int MOVE_DOWN = 1;
                        elevatorBank.moveElevator(e, MOVE_DOWN);
                        updateTable();
                        if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR == floorBank.getFloorsArray().get(bigTable.getRowCount() - 1).FLOOR_LEVEL) {
                            elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.IDLE);
                        }
                    }
                }
            }
        }
    }
    
    public void addVisitors() {
        //generate random amount of visitors 0 through 5 that come into ground floor
        Random rand = new Random();
        int random_integer = rand.nextInt(5 - 0) + 0;

        for (int i = 0; i < random_integer; ++i) {
            floorBank.getFloorsArray().get(bigTable.getModel().getRowCount() - 1).addVisitorToFloor(bigTable.getModel().getRowCount() - 1);
        }
        updateTable();
    }//addVisitors
    
    public void moveOnToElevator(int f, int v, int e) {
        if ((floorBank.getFloorsArray().get(f).getVisitorCount() > 0) && (elevatorBank.getElevatorsArray().get(e).getPassengerCount() < elevatorBank.getElevatorsArray().get(e).MY_CAPACITY)) {
            if (floorBank.getFloorsArray().get(f).getVisitorsArray().get(v).MY_FLOOR != floorBank.getFloorsArray().get(f).getVisitorsArray().get(v).DESTINATION) {
                if (floorBank.getFloorsArray().get(f).getVisitorsArray().get(v).MY_FLOOR == elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR) {
                    elevatorBank.getElevatorsArray().get(e).swipeVisitorOn(floorBank.getFloorsArray().get(f).getVisitorsArray().get(v));
                    floorBank.getFloorsArray().get(f).giveVisitorToElevator(floorBank.getFloorsArray().get(f).getVisitorsArray().get(v));
                }
            }
        }
    }//moveOnToElevator

    public void moveOffOfElevator(int p, int e) {
        if ((elevatorBank.getElevatorsArray().get(e).getPassengerCount() > 0) && (elevatorBank.getElevatorsArray().get(e).getPassengerCount() <= elevatorBank.getElevatorsArray().get(e).MY_CAPACITY)) {
            if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR == elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(p).DESTINATION) {
                floorBank.getFloorsArray().get(elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(p).DESTINATION).recieveVisitorFromElevator(elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(p));

                if (floorBank.getFloorsArray().get(elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(p).DESTINATION).getVisitorsArray().contains(elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(p))) {
                    for (int k = 0; k < floorBank.getFloorsArray().get(elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(p).DESTINATION).getVisitorsArray().size(); ++k) {
                        floorBank.getFloorsArray().get(elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(p).DESTINATION).getVisitorsArray().get(k).setMY_FLOOR(elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(p).DESTINATION);
                    }
                }
                elevatorBank.getElevatorsArray().get(e).swipeVisitorOff(elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(p));
            }
        }
    }//moveOffOfElevator
    
    public void configureGrid(int elevators, int floors){
        for (int e = 0; e < elevatorBank.getElevatorsArray().size(); ++e) {
                elevatorBank.getElevatorsArray().get(e).getPassengersArray().clear();
            }
            elevatorBank.getElevatorsArray().clear();
            for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {
                floorBank.getFloorsArray().get(i).getVisitorsArray().clear();
            }
            floorBank.getFloorsArray().clear();
            window.generateNewTable(elevators, floors);
            floorBank.instantiate(floors);
            elevatorBank.instantiate(elevators, bigTable.getModel().getRowCount() - 1);
            resetAnimation();
            updateTable();
    }//configureGrid
    
    public void updateTable() {
        for (int i = 0; i < bigTable.getModel().getRowCount(); i++) {
            bigTable.getModel().setValueAt(floorBank.getFloorsArray().get(i).getVisitorCount(), i, 0);
            /*String daBoys = "";
             for (Visitor visitor : floorBank.getFloorsArray().get(i).getVisitorsArray() ) {
             daBoys += visitor.MY_NAME + " | ";
             }
             bigTable.getModel().setValueAt(daBoys, i, 0);*/
            //System.out.println("floor " + i + " contains " + floorBank.getFloorsArray().get(i).getVisitorCount() + " visitors");
        }

        for (int i = 0; i < bigTable.getModel().getRowCount(); i++) {
            for (int j = 1; j < bigTable.getModel().getColumnCount(); j++) {
                //System.out.println(i + ", " + j + ", " + elevatorBank.getElevatorsArray().size());
                if (elevatorBank.getElevatorsArray().get(j - 1).getMyFloor() == i) {
                    bigTable.getModel().setValueAt(elevatorBank.getElevatorsArray().get(j - 1).getPassengerCount(), i, j);
                    /*String daBoys = "";
                     for (Visitor visitor : elevatorBank.getElevatorsArray().get(0).getPassengersArray() ) {
                     daBoys += visitor.MY_NAME + "|";
                     }
                     bigTable.getModel().setValueAt(daBoys, i, 1);*/
                } else {
                    bigTable.getModel().setValueAt(null, i, j);
                }
            }
        }
    }//updateTable

/*NEEDS TO BE FIXED ACCORDING FIRST COLUMN "Floor"*/
public void saveScenario(JTable table, File file) {
        try {
            //get information about the table right now
            TableModel model = table.getModel();

            //create the data output stream for scenario
            DataOutputStream scenario = new DataOutputStream(new FileOutputStream(file));

            //write out the animation thread counter
            int count = animationThread.getCount();
            scenario.writeInt(count);

            //write out the number of rows and columns
            int row_idx = model.getRowCount();
            int column_idx = model.getColumnCount();
            scenario.writeInt(row_idx);
            scenario.writeInt(column_idx);

            //for each row and each column...
            for (int i = 0; i < row_idx; i++) {
                for (int j = 0; j < column_idx; j++) {
                    if (model.getValueAt(i, j) != null) { //if the value in the box is not null
                        //write integer of the contents of each cell
                        scenario.writeInt(Integer.parseInt(model.getValueAt(i, j).toString()));
                    } else {
                        //write 0xFFFFFFFF (effectively null)
                        scenario.writeInt(-1);
                    }
                    //System.out.println(model.getValueAt(i, j));
                }
            }
            //remember to wash your hands after eating
            scenario.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//saveScenario

    /*NEEDS TO BE FIXED ACCORDING FIRST COLUMN "Floor"*/
    public void loadScenario(JTable table, File file) {
        try {
            //get file the user loaded in and turn it into an inputstream
            DataInputStream scenario = new DataInputStream(new FileInputStream(file));

            //set the animation count from the first integer in the data
            animationThread.setCount(scenario.readInt());

            //get row and column informatiom from scenario
            int row_idx = scenario.readInt();
            int column_idx = scenario.readInt();

            window.generateNewTable(row_idx, column_idx - 1);
            //now that we have a new table, let's create a new object to reference later
            TableModel model = table.getModel();

            //for each row and column cell in order
            for (int i = 0; i < row_idx; i++) {
                for (int j = 0; j < column_idx; j++) {
                    //read integer for cell from scenario data
                    int toWorkWith = scenario.readInt();

                    //if the data is not 0xFFFFFFFF (aka what we effectively saved as null)
                    if (toWorkWith != -1) {
                        model.setValueAt(toWorkWith, i, j);
                    }
                }
            }
            //make sure we set the simulation status to yes, we have one going on
            //simulationStatus = true;
            //job well done
            scenario.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//loadScenario

}//class Controller2

