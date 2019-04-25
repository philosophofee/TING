package edu.bristolcc.TING;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.State.NEW;
import static java.lang.Thread.State.TIMED_WAITING;
import java.nio.file.Files;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controller2 {

    public boolean simulationStatus;
    
    FloorBank floorBank = new FloorBank();
    ElevatorBank elevatorBank = new ElevatorBank();
    Statistics stats = new Statistics();
    
    private TestWindow window;
    private JTable bigTable;
    private JTable debugTable;
    public ThreadAnimation2 animationThread = new ThreadAnimation2(this);
    public Thread th = new Thread(animationThread);

    public void setWindow(TestWindow window) {
        this.window = window;
    }//setWindow

    public TestWindow getWindow() {
        return window;
    }//getWindow
    
    public void setTable(JTable bigTable) {
        this.bigTable = bigTable;
    }//setTable
    
    public void setDebugTable(JTable debugTable) {
        this.debugTable = debugTable;
    }//setDebugTable
    
    public void instantiateFloor() {
        floorBank.instantiate(bigTable.getModel().getRowCount());
    }//instantiateFloor

    public void instantiateElevator() {
        elevatorBank.instantiate(bigTable.getModel().getColumnCount(), bigTable.getModel().getRowCount() - 1);
        System.out.println("\nTICK " + giveCount() + "\n----------");
        for (int i = 0; i < elevatorBank.getElevatorsArray().size(); ++i) {
            System.out.println("Elevator " + elevatorBank.getElevatorsArray().get(i).MY_IDENTIFIER + " (" + elevatorBank.getElevatorsArray().get(i).getPassengerCount() + "): Floor " + elevatorBank.getElevatorsArray().get(i).CURRENT_FLOOR + ". State: " + elevatorBank.getElevatorsArray().get(i).MY_STATUS);
        }
    }//instantiateElevator

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
    }//giveCount

    public void elevatorTick() {
        elevatorBank.tick();
    }//elevatorTick

    public void floorTick() {
        floorBank.tick();
    }//floorTick

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
                    stats.updateElevatorInStats(e, floorBank.getFloorsArray().size() - elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR);
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
                        
                        //stats.updateElevatorInStats(e, elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR);
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
    }//simulation
    
    public void addVisitors() {
        //generate random amount of visitors 0 through 5 that come into ground floor
        Random rand = new Random();
        int random_integer = rand.nextInt(5 - 0) + 0;

        for (int i = 0; i < random_integer; ++i) {
            floorBank.getFloorsArray().get(bigTable.getModel().getRowCount() - 1).addVisitorToFloor(bigTable.getModel().getRowCount() - 1);
            DefaultTableModel model = (DefaultTableModel) debugTable.getModel(); 
            model.addRow(new Object[]{"", "", "", ""});
            model.addRow(new Object[]{"", "", "", ""});
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
            generateNewTable(elevators, floors);
            floorBank.instantiate(floors);
            elevatorBank.instantiate(elevators, bigTable.getModel().getRowCount() - 1);
            resetAnimation();
            updateTable();
    }//configureGrid
    
    public void configureGridFromFile(int elevators, int floors){
            generateNewTable(elevators, floors);
            updateTable();
    }//configureGridFromFile
    
    public void generateNewTable(int elevators, int floors) {

        int columnCount = elevators;

        // configure column names
        String[] columnNames = new String[columnCount+1];
        char[] dummy = {'A'};
        columnNames[0] = "Floor";
        for (int idx = 1; idx < columnCount+1; ++idx) {
            dummy[0] = (char) (idx - 1 + 'A');
            columnNames[idx] = new String(dummy);
        }
        // populate 2-dimensional array of data
        Object[][] tableContent = new Object[floors][columnCount];

        bigTable.setModel(new javax.swing.table.DefaultTableModel(tableContent, columnNames));
    }//generateNewTable
    
    public void updateTable() {
        
        int debugNum = 0;
        for (int i = 0; i < bigTable.getModel().getRowCount(); i++) {
            bigTable.getModel().setValueAt(floorBank.getFloorsArray().get(i).getVisitorCount(), i, 0);
            //String daBoys = "";
             for (Visitor visitor : floorBank.getFloorsArray().get(i).getVisitorsArray() ) {
             debugTable.getModel().setValueAt(visitor.MY_NAME, debugNum, 0);
             debugTable.getModel().setValueAt(visitor.DESTINATION, debugNum, 1);
             debugTable.getModel().setValueAt(visitor.MY_FLOOR, debugNum, 2);
             debugTable.getModel().setValueAt(visitor.MY_STATUS, debugNum, 3);
             debugNum+=1;
             }
             //bigTable.getModel().setValueAt(daBoys, i, 0);*/
            //System.out.println("floor " + i + " contains " + floorBank.getFloorsArray().get(i).getVisitorCount() + " visitors");
        }

        for (int i = 0; i < bigTable.getModel().getRowCount(); i++) {
            for (int j = 1; j < bigTable.getModel().getColumnCount(); j++) {
                //System.out.println(i + ", " + j + ", " + elevatorBank.getElevatorsArray().size());
                if (elevatorBank.getElevatorsArray().get(j - 1).getMyFloor() == i) {
                    bigTable.getModel().setValueAt(elevatorBank.getElevatorsArray().get(j - 1).getPassengerCount(), i, j);
                    if(elevatorBank.getElevatorsArray().get(j-1).getPassengersArray().size() > 0){//add this if statement for fix
                    for (Visitor visitor : elevatorBank.getElevatorsArray().get(j-1).getPassengersArray() ) {//change i to j-1 for fix
                        
                        debugTable.getModel().setValueAt(visitor.MY_NAME, debugNum, 0);
                        debugTable.getModel().setValueAt(visitor.DESTINATION, debugNum, 1);
                        debugTable.getModel().setValueAt(visitor.MY_FLOOR, debugNum, 2);
                        debugTable.getModel().setValueAt(visitor.MY_STATUS, debugNum, 3);
                        debugNum+=1;
                        }
                    }
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

    public void saveScenario(File file) {
        try {

            long size_elevator;
            long size_floor;
            
            //Create temp files and hide them from the user because we're evil
            File elevatorFile = new File(file.getParent() + "\\TING_temp_elevator");
            if (elevatorFile.exists()) {
                elevatorFile.delete();
            }
            
            File floorFile = new File(file.getParent() + "\\TING_temp_floor");
            if (floorFile.exists()) {
                floorFile.delete();
            }
            
            //Create output file streams for both of theses sub files
            FileOutputStream elevatorFileOut = new FileOutputStream(elevatorFile);
            FileOutputStream floorFileOut = new FileOutputStream(floorFile);

            Files.setAttribute(elevatorFile.toPath(), "dos:hidden", true);
            Files.setAttribute(floorFile.toPath(), "dos:hidden", true);
            
            ObjectOutputStream elevatorOut = new ObjectOutputStream(elevatorFileOut);
            ObjectOutputStream floorOut = new ObjectOutputStream(floorFileOut);

            elevatorOut.writeObject(elevatorBank);
            size_elevator = elevatorFile.length();
            System.out.println("size of elevator section should be " + size_elevator + " bytes");
            
            floorOut.writeObject(floorBank);
            size_floor = floorFile.length();
            System.out.println("size of floor section should be " + size_floor + " bytes");
            
            elevatorOut.close();
            floorOut.close();
            elevatorFileOut.close();
            floorFileOut.close();
            
            FileInputStream elevatorInput = new FileInputStream(elevatorFile);
            FileInputStream floorInput = new FileInputStream(floorFile);
            FileOutputStream fileOutput = new FileOutputStream(file);
            DataOutputStream bigData = new DataOutputStream(fileOutput);
            bigData.writeLong(size_elevator);
            bigData.writeLong(size_floor);
            
            int len = 0;
            
            byte[] buffer = new byte[1024 * 1024]; // 1MB buffer
            while ((len = elevatorInput.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, len);
            }
            while ((len = floorInput.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, len);
            }
            
            bigData.close();
            fileOutput.close();
            elevatorInput.close();
            floorInput.close();
            
            if (elevatorFile.exists()) {
                if (elevatorFile.delete()) {
                    System.out.println("deleted elevator File");
                };
            }
            if (floorFile.exists()) {
                if (floorFile.delete()) {
                    System.out.println("deleted floor File");
                };
            }
            
            System.out.println("The elevator and floor banks were succesfully written to file");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }//saveScenario
    
    public void loadScenario(File file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            DataInputStream dataIn = new DataInputStream(fileIn);
                        
            long size_elevator = dataIn.readLong();
            long size_floor = dataIn.readLong();
            
            File elevatorFile = new File(file.getParent() + "\\TING_temp_elevator");
            if (elevatorFile.exists()) {
                elevatorFile.delete();
            }
            
            File floorFile = new File(file.getParent() + "\\TING_temp_floor");
            if (floorFile.exists()) {
                floorFile.delete();
            }
            
            
            FileOutputStream elevatorFileOutput = new FileOutputStream(elevatorFile);
            FileOutputStream floorFileOutput = new FileOutputStream(floorFile);
            
            byte[] elevatorInputData = new byte[(int)size_elevator];
            byte[] floorInputData = new byte[(int)size_floor];
            
            
            fileIn.read(elevatorInputData);
            fileIn.read(floorInputData);
            
            elevatorFileOutput.write(elevatorInputData);
            floorFileOutput.write(floorInputData);
            
            dataIn.close();
            elevatorFileOutput.close();
            floorFileOutput.close();
            
            FileInputStream elevatorFileInputStream = new FileInputStream(elevatorFile);
            FileInputStream floorFileInputStream = new FileInputStream(floorFile);
            
            ObjectInputStream elevatorObjectInputStream = new ObjectInputStream(elevatorFileInputStream);
            ObjectInputStream floorObjectInputStream = new ObjectInputStream(floorFileInputStream);
            
            elevatorBank = (ElevatorBank)elevatorObjectInputStream.readObject();
            floorBank = (FloorBank)floorObjectInputStream.readObject();
            
            elevatorFileInputStream.close();
            floorFileInputStream.close();
            elevatorObjectInputStream.close();
            floorObjectInputStream.close();
            
            if (elevatorFile.exists()) {
                if (elevatorFile.delete()) {
                    System.out.println("deleted elevator File");
                };
            }
            if (floorFile.exists()) {
                if (floorFile.delete()) {
                    System.out.println("deleted floor File");
                };
            }
            
            System.out.println("elevator size is " + size_elevator + " and floor size is " + size_floor);
            
            System.out.println("The elevator and floor banks were succesfully read from file");
            configureGridFromFile(elevatorBank.ELEVATOR_COUNT, floorBank.FLOOR_MAX);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }//loadScenario
    
}//class Controller2