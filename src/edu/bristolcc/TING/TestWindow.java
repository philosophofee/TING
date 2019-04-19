package edu.bristolcc.TING;

import java.io.File;
import java.util.Random;

public class TestWindow extends javax.swing.JFrame {

    FloorBank floorBank = new FloorBank();
    ElevatorBank elevatorBank;
    Controller2 controller;
    public static File scenarioFile = new File("scenario.txt");

    public TestWindow(Controller2 controller) {
        initComponents();
        this.controller = controller;
        floorBank.instantiate(bigTable.getModel().getRowCount());
        elevatorBank = new ElevatorBank(/*bigTable.getModel().getColumnCount() - 1,*/bigTable.getModel().getRowCount()); //because the floor column doesn't count
        elevatorBank.instantiate(bigTable.getModel().getColumnCount(), bigTable.getModel().getRowCount() - 1);
        updateTable();
        System.out.println("\nTICK " + controller.giveCount() + "\n----------");
        for (int i = 0; i < elevatorBank.getElevatorsArray().size(); ++i) {
            System.out.println("Elevator " + elevatorBank.getElevatorsArray().get(i).MY_IDENTIFIER + " (" + elevatorBank.getElevatorsArray().get(i).getPassengerCount() + "): Floor " + elevatorBank.getElevatorsArray().get(i).CURRENT_FLOOR + ". State: " + elevatorBank.getElevatorsArray().get(i).MY_STATUS);
        }
    }

    public void update() {

        /*Add random number of visitors to ground floor*/
        //addVisitors();
        //print beginning of tick for each tick
        System.out.println("\nTICK " + controller.giveCount() + "\n----------");
        //print all elevators and their status
        for (int i = 0; i < elevatorBank.getElevatorsArray().size()-1; ++i) {
            System.out.println("Elevator" + i + "(" + elevatorBank.getElevatorsArray().get(i).getPassengerCount() + ") : Floor " + elevatorBank.getElevatorsArray().get(i).CURRENT_FLOOR + ". State: " + elevatorBank.getElevatorsArray().get(i).MY_STATUS);
        }

        //print all passengers in all elevators and their status
        for (int i = 0; i < elevatorBank.getElevatorsArray().size()-1; ++i) {
            for (int k = 0; k < elevatorBank.getElevatorsArray().get(i).getPassengerCount(); ++k) {
                System.out.println("Passenger " + elevatorBank.getElevatorsArray().get(i).getPassengersArray().get(k).MY_NAME + "(wants " + elevatorBank.getElevatorsArray().get(i).getPassengersArray().get(k).DESTINATION + "): Floor " + elevatorBank.getElevatorsArray().get(i).CURRENT_FLOOR + ". State: " + elevatorBank.getElevatorsArray().get(i).getPassengersArray().get(k).MY_STATUS);
            }
        }

        //print all visitor on all floors and their status
        for (int i = 0; i < floorBank.getFloorsArray().size(); ++i) {
            for (int k = 0; k < floorBank.getFloorsArray().get(i).getVisitorsArray().size(); ++k) {
                System.out.println("Visitor " + floorBank.getFloorsArray().get(i).getVisitorsArray().get(k).MY_NAME + "(wants " + floorBank.getFloorsArray().get(i).getVisitorsArray().get(k).DESTINATION + "): Floor " + floorBank.getFloorsArray().get(i).getVisitorsArray().get(k).MY_FLOOR + ". State: " + floorBank.getFloorsArray().get(i).getVisitorsArray().get(k).MY_STATUS);
            }
        }

        //move visitor onto elevator if elevator is IDLE meaning ground floor and change state to MOVE_UP
        for (int e = 0; e < elevatorBank.getElevatorsArray().size()-1; ++e) {
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
        for (int e = 0; e < elevatorBank.getElevatorsArray().size()-1; ++e) {
            if ((elevatorBank.getElevatorsArray().get(e).getPassengerCount() > 0) && (elevatorBank.getElevatorsArray().get(e).getPassengerCount() <= elevatorBank.getElevatorsArray().get(e).MY_CAPACITY)) {
                if(elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR == floorBank.getFloorsArray().get(bigTable.getRowCount()-1).FLOOR_LEVEL){
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
        for (int e = 0; e < elevatorBank.getElevatorsArray().size()-1; ++e) {
            for (int k = 0; k < elevatorBank.getElevatorsArray().get(e).getPassengerCount(); ++k) {
                if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR == elevatorBank.getElevatorsArray().get(e).getPassengersArray().get(k).DESTINATION) {
                    //get off elevator
                    moveOffOfElevator(k, e);
                    updateTable();
                    if ((elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR == 0)) {
                        elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.MOVE_DOWN);
                    } else if (elevatorBank.getElevatorsArray().get(e).getPassengerCount() == 0) {
                        elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.MOVE_DOWN);
                    }
                    
                }
            }
            if (elevatorBank.getElevatorsArray().get(e).CURRENT_FLOOR == 0) {
                elevatorBank.getElevatorsArray().get(e).setMY_STATUS(ElevatorStatus.MOVE_DOWN);
            }
        }

        //move elevator down by 1 each tick and if the elevator hits ground floor change state to IDLE
        for (int e = 0; e < elevatorBank.getElevatorsArray().size()-1; ++e) {
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
    }//update

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bigTable = new javax.swing.JTable();
        pnlTabbedPane = new javax.swing.JPanel();
        tbdPaneMain = new javax.swing.JTabbedPane();
        pnlSimulation = new javax.swing.JPanel();
        btnStartSimulation = new javax.swing.JButton();
        btnStopSimulation = new javax.swing.JButton();
        pnlTable = new javax.swing.JPanel();
        txtFloors = new javax.swing.JTextField();
        lblFloors = new javax.swing.JLabel();
        lblElevators = new javax.swing.JLabel();
        txtElevators = new javax.swing.JTextField();
        btnConfigureGrid = new javax.swing.JButton();
        btnResetTable = new javax.swing.JButton();
        pnlScenario = new javax.swing.JPanel();
        btnSaveScenario = new javax.swing.JButton();
        btnLoadScenario = new javax.swing.JButton();
        pnlStatistics = new javax.swing.JPanel();
        pnlStats = new edu.bristolcc.TING.Statistics();
        pnlDEBUG = new javax.swing.JPanel();
        btnAddVisitor = new javax.swing.JToggleButton();
        btnUpdateTable = new javax.swing.JButton();
        tfFPS = new javax.swing.JTextField();
        lblSpeed = new javax.swing.JLabel();
        btnShowVisitors = new javax.swing.JButton();
        btnShowPassengers = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TING Elevator Simulation");

        bigTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Floor", "A", "B", "C", "D"
            }
        ));
        jScrollPane1.setViewportView(bigTable);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        btnStartSimulation.setText("Start Simulation");
        btnStartSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartSimulationActionPerformed(evt);
            }
        });

        btnStopSimulation.setText("Stop Simulation");
        btnStopSimulation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopSimulationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSimulationLayout = new javax.swing.GroupLayout(pnlSimulation);
        pnlSimulation.setLayout(pnlSimulationLayout);
        pnlSimulationLayout.setHorizontalGroup(
            pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnStartSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
            .addComponent(btnStopSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlSimulationLayout.setVerticalGroup(
            pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSimulationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStartSimulation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStopSimulation)
                .addContainerGap(417, Short.MAX_VALUE))
        );

        tbdPaneMain.addTab("Modify Simulation", pnlSimulation);

        lblFloors.setText("Floors:");

        lblElevators.setText("Elevators:");

        btnConfigureGrid.setText("Populate Table");
        btnConfigureGrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigureGridActionPerformed(evt);
            }
        });

        btnResetTable.setText("Reset Table");
        btnResetTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfigureGrid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addGroup(pnlTableLayout.createSequentialGroup()
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFloors)
                            .addComponent(lblElevators))
                        .addGap(12, 12, 12)
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFloors, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(txtElevators))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnResetTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFloors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFloors))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblElevators)
                    .addComponent(txtElevators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfigureGrid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnResetTable)
                .addContainerGap(360, Short.MAX_VALUE))
        );

        tbdPaneMain.addTab("Update Table", pnlTable);

        btnSaveScenario.setText("Save Scenario");
        btnSaveScenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveScenarioActionPerformed(evt);
            }
        });

        btnLoadScenario.setText("Load Scenario");
        btnLoadScenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadScenarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlScenarioLayout = new javax.swing.GroupLayout(pnlScenario);
        pnlScenario.setLayout(pnlScenarioLayout);
        pnlScenarioLayout.setHorizontalGroup(
            pnlScenarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScenarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlScenarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveScenario, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                    .addComponent(btnLoadScenario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlScenarioLayout.setVerticalGroup(
            pnlScenarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScenarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSaveScenario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoadScenario)
                .addContainerGap(417, Short.MAX_VALUE))
        );

        tbdPaneMain.addTab("Change Scenario", pnlScenario);

        javax.swing.GroupLayout pnlStatsLayout = new javax.swing.GroupLayout(pnlStats);
        pnlStats.setLayout(pnlStatsLayout);
        pnlStatsLayout.setHorizontalGroup(
            pnlStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 484, Short.MAX_VALUE)
        );
        pnlStatsLayout.setVerticalGroup(
            pnlStatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlStatisticsLayout = new javax.swing.GroupLayout(pnlStatistics);
        pnlStatistics.setLayout(pnlStatisticsLayout);
        pnlStatisticsLayout.setHorizontalGroup(
            pnlStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatisticsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlStatisticsLayout.setVerticalGroup(
            pnlStatisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatisticsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbdPaneMain.addTab("View Statistics", pnlStatistics);

        btnAddVisitor.setText("Add Visitor to Random Floor");
        btnAddVisitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddVisitorActionPerformed(evt);
            }
        });

        btnUpdateTable.setText("Update Table");
        btnUpdateTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTableActionPerformed(evt);
            }
        });

        tfFPS.setText("500");
        tfFPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFPSActionPerformed(evt);
            }
        });

        lblSpeed.setText("Animation Speed (ms):");
        lblSpeed.setToolTipText("");

        btnShowVisitors.setText("Show Visitors");
        btnShowVisitors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowVisitorsActionPerformed(evt);
            }
        });

        btnShowPassengers.setText("Show Passengers");
        btnShowPassengers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowPassengersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDEBUGLayout = new javax.swing.GroupLayout(pnlDEBUG);
        pnlDEBUG.setLayout(pnlDEBUGLayout);
        pnlDEBUGLayout.setHorizontalGroup(
            pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddVisitor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDEBUGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSpeed)
                    .addComponent(btnShowVisitors))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDEBUGLayout.createSequentialGroup()
                        .addComponent(btnShowPassengers)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateTable))
                    .addComponent(tfFPS, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        pnlDEBUGLayout.setVerticalGroup(
            pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDEBUGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddVisitor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowVisitors)
                    .addComponent(btnShowPassengers)
                    .addComponent(btnUpdateTable))
                .addGap(11, 11, 11)
                .addGroup(pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSpeed)
                    .addComponent(tfFPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(386, Short.MAX_VALUE))
        );

        tbdPaneMain.addTab("DEBUG", pnlDEBUG);

        javax.swing.GroupLayout pnlTabbedPaneLayout = new javax.swing.GroupLayout(pnlTabbedPane);
        pnlTabbedPane.setLayout(pnlTabbedPaneLayout);
        pnlTabbedPaneLayout.setHorizontalGroup(
            pnlTabbedPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTabbedPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tbdPaneMain, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTabbedPaneLayout.setVerticalGroup(
            pnlTabbedPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbdPaneMain)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTableActionPerformed
        updateTable();
    }//GEN-LAST:event_btnUpdateTableActionPerformed

    private void btnAddVisitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddVisitorActionPerformed
        addVisitors();
        updateTable();
    }//GEN-LAST:event_btnAddVisitorActionPerformed

    private void btnStartSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartSimulationActionPerformed
        controller.animationThread.setSpeed(Long.parseLong(tfFPS.getText()));
        controller.startAnimation();
    }//GEN-LAST:event_btnStartSimulationActionPerformed

    private void btnStopSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopSimulationActionPerformed
        controller.pauseAnimation();
    }//GEN-LAST:event_btnStopSimulationActionPerformed

    private void btnSaveScenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveScenarioActionPerformed
        controller.pauseAnimation();
        FilePicker picker = new FilePicker(null);
        File toSave = picker.pickFile(".esf", "TING Elevator Scenario File (*.esf)", true);
        if (toSave == null) {
            return;
        }
        String path = toSave.getAbsolutePath();
        if (!path.endsWith(".esf")) {
            path = path + ".esf";
        }
        controller.saveScenario(bigTable, new File(path));
    }//GEN-LAST:event_btnSaveScenarioActionPerformed

    private void btnLoadScenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadScenarioActionPerformed
        controller.pauseAnimation();
        FilePicker picker = new FilePicker(null);
        File toLoad = picker.pickFile(".esf", "TING Elevator Scenario File (*.esf)", false);
        if (toLoad == null) {
            return;
        }
        controller.loadScenario(bigTable, toLoad); //open ui to load scenario file into jTable
    }//GEN-LAST:event_btnLoadScenarioActionPerformed

    private void btnResetTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTableActionPerformed
        for (int e = 0; e < elevatorBank.getElevatorsArray().size(); ++e) {
            elevatorBank.getElevatorsArray().get(e).getPassengersArray().clear();
        }
        elevatorBank.getElevatorsArray().clear();
        for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {
            floorBank.getFloorsArray().get(i).getVisitorsArray().clear();
        }
        floorBank.getFloorsArray().clear();
        generateNewTable(4/*elevators*/, 5/*floors*/);
        floorBank.instantiate(5);
        elevatorBank.instantiate(4, bigTable.getModel().getRowCount() - 1);
        controller.resetAnimation();
        pnlStats.setScores(null);
        updateTable();
    }//GEN-LAST:event_btnResetTableActionPerformed

    private void btnConfigureGridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigureGridActionPerformed
        try {
            int elevators = Integer.parseInt(txtElevators.getText());
            int floors = Integer.parseInt(txtFloors.getText());

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
            controller.resetAnimation();
            pnlStats.setScores(null);
            updateTable();
        } catch (NumberFormatException ex) {
            controller.pauseAnimation();
            txtFloors.setText("NaN");
            txtElevators.setText("NaN");
        }
    }//GEN-LAST:event_btnConfigureGridActionPerformed

    private void tfFPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFPSActionPerformed
        //do nothing
    }//GEN-LAST:event_tfFPSActionPerformed

    private void btnShowVisitorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowVisitorsActionPerformed
        //print all visitor on all floors and their status
        for (int i = 0; i < floorBank.getFloorsArray().size(); ++i) {
            for (int k = 0; k < floorBank.getFloorsArray().get(i).getVisitorsArray().size(); ++k) {
                System.out.println("Visitor " + floorBank.getFloorsArray().get(i).getVisitorsArray().get(k).MY_NAME + "(wants " + floorBank.getFloorsArray().get(i).getVisitorsArray().get(k).DESTINATION + "): Floor " + floorBank.getFloorsArray().get(i).getVisitorsArray().get(k).MY_FLOOR + ". State: " + floorBank.getFloorsArray().get(i).getVisitorsArray().get(k).MY_STATUS);
            }
        }
    }//GEN-LAST:event_btnShowVisitorsActionPerformed

    private void btnShowPassengersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPassengersActionPerformed
        //print all passengers in all elevators and their status
        for (int i = 0; i < elevatorBank.getElevatorsArray().size(); ++i) {
            for (int k = 0; k < elevatorBank.getElevatorsArray().get(i).getPassengerCount(); ++k) {
                System.out.println("Passenger " + elevatorBank.getElevatorsArray().get(i).getPassengersArray().get(k).MY_NAME + "(wants " + elevatorBank.getElevatorsArray().get(i).getPassengersArray().get(k).DESTINATION + "): Floor " + elevatorBank.getElevatorsArray().get(i).CURRENT_FLOOR + ". State: " + elevatorBank.getElevatorsArray().get(i).getPassengersArray().get(k).MY_STATUS);
            }
        }
    }//GEN-LAST:event_btnShowPassengersActionPerformed

    public void addVisitors() {
        /*generate random amount of visitors 0 through 5 that come into ground floor*/
        Random rand = new Random();
        int random_integer = rand.nextInt(5 - 0) + 0;

        for (int i = 0; i < random_integer; ++i) {
            floorBank.getFloorsArray().get(bigTable.getModel().getRowCount() - 1).addVisitorToFloor(bigTable.getModel().getRowCount() - 1);
        }
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

    public void generateNewTable(int elevators, int floors) {

        int columnCount = elevators + 1;

        // configure column names
        String[] columnNames = new String[columnCount];
        char[] dummy = {'A'};
        columnNames[0] = "Floor";
        for (int idx = 1; idx < columnCount; ++idx) {
            dummy[0] = (char) (idx - 1 + 'A');
            columnNames[idx] = new String(dummy);
        }
        // populate 2-dimensional array of data
        Object[][] tableContent = new Object[floors/*rows*/][columnCount/*columns*/];

        bigTable.setModel(new javax.swing.table.DefaultTableModel(tableContent, columnNames));

        //updateTable();
    }//generateNewTable

    private void updateTable() {
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

    public static void main(String args[]) {
        Controller2 controller = new Controller2();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TestWindow window = new TestWindow(controller);
                controller.setWindow(window);
                window.setVisible(true);
            }
        });
    }//main

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bigTable;
    private javax.swing.JToggleButton btnAddVisitor;
    private javax.swing.JButton btnConfigureGrid;
    private javax.swing.JButton btnLoadScenario;
    private javax.swing.JButton btnResetTable;
    private javax.swing.JButton btnSaveScenario;
    private javax.swing.JButton btnShowPassengers;
    private javax.swing.JButton btnShowVisitors;
    private javax.swing.JButton btnStartSimulation;
    private javax.swing.JButton btnStopSimulation;
    private javax.swing.JButton btnUpdateTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblElevators;
    private javax.swing.JLabel lblFloors;
    private javax.swing.JLabel lblSpeed;
    private javax.swing.JPanel pnlDEBUG;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlScenario;
    private javax.swing.JPanel pnlSimulation;
    private javax.swing.JPanel pnlStatistics;
    private edu.bristolcc.TING.Statistics pnlStats;
    private javax.swing.JPanel pnlTabbedPane;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JTabbedPane tbdPaneMain;
    private javax.swing.JTextField tfFPS;
    private javax.swing.JTextField txtElevators;
    private javax.swing.JTextField txtFloors;
    // End of variables declaration//GEN-END:variables
}//class TestWindow
