package edu.bristolcc.TING;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class Scenario {
    
    private Controller controller;
    private MainWindow window;
    public ThreadAnimation animationThread = new ThreadAnimation(controller);
    
    //public boolean simulationStatus = controller.simulationStatus;

    public Scenario(Controller controller){
        this.controller = controller;
        if(controller.getWindow() != null){
        controller.getWindow();
        }
    }//Scenario
    
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
    
}//Class Scenario