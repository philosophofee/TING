package edu.bristolcc.TING;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import static java.lang.Thread.State.TIMED_WAITING;

public class Controller {

    public boolean simulationStatus;
    private MainWindow window;
    private ThreadAnimation animationThread = new ThreadAnimation(this);
    Thread th = new Thread(animationThread);

    public MainWindow getWindow() {
        return window;
    }//getWindow

    public void setWindow(MainWindow window) {
        this.window = window;
    }//setWindow

    public void startAnimation() {
        simulationStatus = true;

        if (animationThread.getCount() == 0 && th.getState().equals(TIMED_WAITING) || animationThread.getCount() != 0) {
            th.resume();
            //System.out.println("resume after reset: " + th.getState());
        } else {
            th.start();
            //System.out.println("start: " + th.getState());
        }
    }//startAnimation

    public void resetAnimation() {
        simulationStatus = false;
        th.suspend();
        animationThread.setCount(0);
        //System.out.println("stop: " + th.getState());
    }//resetAnimation

    public void pauseAnimation() {
        simulationStatus = false;
        th.suspend();
        //System.out.println("pause: " + th.getState());
    }//stopAnimation

    public void animate() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                window.update();
            }
        });
    }//animate

    public int getCurrentData() {
        return animationThread.getCount();
    }//getCurrentData

    //FINISH LATER aka saveScenario
    public void saveScenario(JTable table, File file) {
        //try {
            TableModel model = table.getModel();
            //FileWriter scenario = new FileWriter(file);

            int row_idx = model.getRowCount();
            int column_idx = model.getColumnCount();

            //for (int i = 0; i < column_idx; i++) {
                //scenario.write(model.getColumnName(i) + "\t");
            //}
            //scenario.write("\n");

            for (int i = 0; i < row_idx; i++) {
                for (int j = 0; j < column_idx; j++) {

                    //scenario.write(model.getValueAt(i, j).toString());
                    System.out.println(model.getValueAt(i, j));
                }
                //scenario.write("\n");
            }
            //scenario.close();
        //} catch (IOException ex) {
           // ex.printStackTrace();
        //}
    }//saveScenario

    public void loadScenario() {
        //to do
    }//loadScenario

}//class ControllerDemo
