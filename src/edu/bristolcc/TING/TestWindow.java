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
        elevatorBank = new ElevatorBank(bigTable.getModel().getColumnCount() - 1, bigTable.getModel().getRowCount()/*, true*/); //because the floor column doesn't count
    }

    public void update() {
        /*Adds visitors to random floors every tick of the simulation*/
        addVisitors();

        /*RIGHT NOW THIS ONLY WORKS 1 ELEVATOR*/
        for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {

            //if (floorBank.getFloorsArray().get(i).getVisitorCount() > 0) {
            for (int m = 0; m < floorBank.getFloorsArray().get(i).getVisitorCount(); ++m) {
                if (floorBank.getFloorsArray().get(i).getVisitorsArray().get(m).DESTINATION != floorBank.getFloorsArray().get(i).getVisitorsArray().get(m).getMY_FLOOR()) {
                    elevatorBank.moveElevator(0, -((elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR) - (floorBank.getFloorsArray().get(i).getVisitorsArray().get(m).getMY_FLOOR())));
                    if (elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR == floorBank.getFloorsArray().get(i).getVisitorsArray().get(m).getMY_FLOOR()) {
                            //System.out.println("\nFloor Object of Visitor: " + floorBank.getFloorsArray().get(i).getVisitorsArray().get(m) + "(Before Elevator)");
                        //System.out.println("Floor that Visitor is on: " + floorBank.getFloorsArray().get(i).getVisitorsArray().get(m).getMY_FLOOR() + "+1 should = orignal floor ");
                        moveOnToElevator();

                        for (int k = 0; k < elevatorBank.getElevatorsArray().get(0).getPassengersArray().size(); ++k) {
                                //System.out.println("Elevator Object of Visitor: " + elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(k) + "(In Elevator)");
                            //System.out.println("Visitors Destination according to Elevator: " + elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(k).DESTINATION + "+1");

                            elevatorBank.moveElevator(0, -((elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR) - (elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(k).DESTINATION)));
                            if (elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(k).DESTINATION == elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR) {
                                moveOffOfElevator();
                            }
                        }
                    }
                }
            }
            updateTable();
        }
        //}
    }

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
        btnMoveElevator = new javax.swing.JButton();
        lblElevator = new javax.swing.JLabel();
        tfMoveElevator1 = new javax.swing.JTextField();
        lblFloor = new javax.swing.JLabel();
        tfMoveElevator2 = new javax.swing.JTextField();
        btnMoveVisitor = new javax.swing.JButton();
        btnMovePassenger = new javax.swing.JButton();
        btnRunFullTick = new javax.swing.JButton();
        btnShowVisitors = new javax.swing.JButton();
        btnShowPassengers = new javax.swing.JButton();
        btnUpdateTable = new javax.swing.JButton();
        tfFPS = new javax.swing.JTextField();
        lblSpeed = new javax.swing.JLabel();

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

        btnSaveScenario.setText("Save Scenario (Fix according to extra column \"Floor\")");
        btnSaveScenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveScenarioActionPerformed(evt);
            }
        });

        btnLoadScenario.setText("Load Scenario (Fix according to extra column \"Floor\")");
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

        btnMoveElevator.setText("Move [Elevator] by [Floor]");
        btnMoveElevator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveElevatorActionPerformed(evt);
            }
        });

        lblElevator.setText("Elevator: ");

        tfMoveElevator1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMoveElevator1ActionPerformed(evt);
            }
        });

        lblFloor.setText("Floor: ");

        tfMoveElevator2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMoveElevator2ActionPerformed(evt);
            }
        });

        btnMoveVisitor.setText("Remove Visitor from Floor and Add to Elevator");
        btnMoveVisitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveVisitorActionPerformed(evt);
            }
        });

        btnMovePassenger.setText("Remove Passenger from Elevator and Add to Floor");
        btnMovePassenger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovePassengerActionPerformed(evt);
            }
        });

        btnRunFullTick.setText("Run 1 Full Tick of Simulation");
        btnRunFullTick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRunFullTickActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout pnlDEBUGLayout = new javax.swing.GroupLayout(pnlDEBUG);
        pnlDEBUG.setLayout(pnlDEBUGLayout);
        pnlDEBUGLayout.setHorizontalGroup(
            pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAddVisitor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMoveElevator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMoveVisitor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnMovePassenger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnRunFullTick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDEBUGLayout.createSequentialGroup()
                .addGroup(pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDEBUGLayout.createSequentialGroup()
                        .addComponent(btnShowVisitors)
                        .addGap(18, 18, 18)
                        .addComponent(btnShowPassengers)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateTable))
                    .addGroup(pnlDEBUGLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDEBUGLayout.createSequentialGroup()
                                .addComponent(lblSpeed)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfFPS, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDEBUGLayout.createSequentialGroup()
                                .addComponent(lblElevator, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfMoveElevator1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                                .addComponent(lblFloor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfMoveElevator2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(53, 53, 53))
        );
        pnlDEBUGLayout.setVerticalGroup(
            pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDEBUGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddVisitor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoveElevator)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMoveElevator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMoveElevator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFloor)
                    .addComponent(lblElevator))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMoveVisitor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMovePassenger)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRunFullTick)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowVisitors)
                    .addComponent(btnShowPassengers)
                    .addComponent(btnUpdateTable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDEBUGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSpeed)
                    .addComponent(tfFPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(239, Short.MAX_VALUE))
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

    private void btnMoveElevatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveElevatorActionPerformed
        elevatorBank.moveElevator(Integer.parseInt(tfMoveElevator1.getText()), Integer.parseInt(tfMoveElevator2.getText()));
        updateTable();
    }//GEN-LAST:event_btnMoveElevatorActionPerformed

    private void tfMoveElevator1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMoveElevator1ActionPerformed
        //do nothing
    }//GEN-LAST:event_tfMoveElevator1ActionPerformed

    private void btnAddVisitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddVisitorActionPerformed
        addVisitors();
        updateTable();
    }//GEN-LAST:event_btnAddVisitorActionPerformed

    private void btnMoveVisitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveVisitorActionPerformed
        moveOnToElevator();
        updateTable();
    }//GEN-LAST:event_btnMoveVisitorActionPerformed

    private void btnMovePassengerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovePassengerActionPerformed
        moveOffOfElevator();
        updateTable();
    }//GEN-LAST:event_btnMovePassengerActionPerformed

    private void tfMoveElevator2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMoveElevator2ActionPerformed
        //do nothing
    }//GEN-LAST:event_tfMoveElevator2ActionPerformed

    private void btnRunFullTickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunFullTickActionPerformed
        update();
    }//GEN-LAST:event_btnRunFullTickActionPerformed

    private void btnShowVisitorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowVisitorsActionPerformed
        for (int j = 0; j < floorBank.getFloorsArray().size(); ++j) {
            for (int i = 0; i < floorBank.getFloorsArray().get(j).visitorsArray.size(); ++i) {
                System.out.println("Visitor " + floorBank.getFloorsArray().get(j).getVisitorsArray().get(i) + " is on floor " + floorBank.getFloorsArray().get(j).getVisitorsArray().get(i).MY_FLOOR + "+1 and, wants to go to floor " + floorBank.getFloorsArray().get(j).getVisitorsArray().get(i).DESTINATION + "+1");

                if (floorBank.getFloorsArray().get(j).getVisitorsArray().get(i).MY_FLOOR == floorBank.getFloorsArray().get(j).getVisitorsArray().get(i).DESTINATION) {
                    System.out.println("Visitor " + floorBank.getFloorsArray().get(j).getVisitorsArray().get(i) + " is at their destination floor " + floorBank.getFloorsArray().get(j).getVisitorsArray().get(i).MY_FLOOR + "+1");
                }
            }
        }
        System.out.println("\n");
    }//GEN-LAST:event_btnShowVisitorsActionPerformed

    private void btnShowPassengersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowPassengersActionPerformed
        for (int j = 0; j < elevatorBank.getElevatorsArray().size(); ++j) {
            for (int i = 0; i < elevatorBank.getElevatorsArray().get(j).getPassengersArray().size(); ++i) {
                System.out.println("Passenger " + elevatorBank.getElevatorsArray().get(j).getPassengersArray().get(i) + " wants to go to floor " + elevatorBank.getElevatorsArray().get(j).getPassengersArray().get(i).DESTINATION + "+1");
            }
        }
        System.out.println("\n");
    }//GEN-LAST:event_btnShowPassengersActionPerformed

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
        elevatorBank.getElevatorsArray().get(0).getPassengersArray().clear();
        for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {
            floorBank.getFloorsArray().get(i).getVisitorsArray().clear();
        }
        generateNewTable(4/*elevators*/, 5/*floors*/);
        floorBank.instantiate(5);
        controller.resetAnimation(/*bigTable*/);
        pnlStats.setScores(null);
    }//GEN-LAST:event_btnResetTableActionPerformed

    private void btnConfigureGridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigureGridActionPerformed
        try {
            int elevators = Integer.parseInt(txtElevators.getText());
            int floors = Integer.parseInt(txtFloors.getText());
            elevatorBank.getElevatorsArray().get(0).getPassengersArray().clear();
            for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {
                floorBank.getFloorsArray().get(i).getVisitorsArray().clear();
            }
            generateNewTable(elevators, floors);
            floorBank.instantiate(floors);
            controller.resetAnimation();
            pnlStats.setScores(null);
        } catch (NumberFormatException ex) {
            controller.pauseAnimation();
            txtFloors.setText("NaN");
            txtElevators.setText("NaN");
        }
    }//GEN-LAST:event_btnConfigureGridActionPerformed

    private void tfFPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFPSActionPerformed
        //do nothing
    }//GEN-LAST:event_tfFPSActionPerformed

    public void addVisitors() {
        for (int i = 0; i < 6; ++i) {
            //generates random number for floor a visitor gets added to
            Random rand = new Random();
            int random_integer = rand.nextInt(bigTable.getModel().getRowCount() - 0) + 0;
            floorBank.getFloorsArray().get(random_integer).addVisitorToFloor(bigTable.getModel().getRowCount());
        }
    }//addVisitors

    public void moveOnToElevator() {

        /*RIGHT NOW THIS ONLY WORKS FOR 1 ELEVATOR*/
        for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {
            for (int m = 0; m < floorBank.getFloorsArray().get(i).getVisitorCount(); ++m) {
                if ((floorBank.getFloorsArray().get(i).getVisitorCount() > 0) && (elevatorBank.getElevatorsArray().get(0).getPassengerCount() < elevatorBank.getElevatorsArray().get(0).MY_CAPACITY)) {
                    if (floorBank.getFloorsArray().get(i).getVisitorsArray().get(m).getMY_FLOOR() != floorBank.getFloorsArray().get(i).getVisitorsArray().get(m).DESTINATION) {
                        if (floorBank.getFloorsArray().get(i).getVisitorsArray().get(m).getMY_FLOOR() == elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR) {
                            elevatorBank.getElevatorsArray().get(0).swipeVisitorOn(floorBank.getFloorsArray().get(i).getVisitorsArray().get(m));
                            floorBank.getFloorsArray().get(i).giveVisitorToElevator(floorBank.getFloorsArray().get(i).getVisitorsArray().get(m));

                        }
                    }
                }
            }
        }
    }//moveOnToElevator

    public void moveOffOfElevator() {

        /*RIGHT NOW THIS ONLY WORKS FOR 1 ELEVATOR*/
        for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {
            if ((elevatorBank.getElevatorsArray().get(0).getPassengerCount() > 0) && (elevatorBank.getElevatorsArray().get(0).getPassengerCount() <= elevatorBank.getElevatorsArray().get(0).MY_CAPACITY)) {

                for (int j = 0; j < elevatorBank.getElevatorsArray().get(0).getPassengerCount(); ++j) {
                    if (elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR == elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(j).DESTINATION) {
                        floorBank.getFloorsArray().get(elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(j).DESTINATION).recieveVisitorFromElevator(elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(j));

                        if (floorBank.getFloorsArray().get(elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(j).DESTINATION).getVisitorsArray().contains(elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(j))) {
                            for (int k = 0; k < floorBank.getFloorsArray().get(elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(j).DESTINATION).getVisitorsArray().size(); ++k) {
                                floorBank.getFloorsArray().get(elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(j).DESTINATION).getVisitorsArray().get(k).setMY_FLOOR(elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(j).DESTINATION);
                            }
                        }
                        elevatorBank.getElevatorsArray().get(0).swipeVisitorOff(elevatorBank.getElevatorsArray().get(0).getPassengersArray().get(j));
                    }
                }
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

        //adjustTableColumns();
    }//generateNewTable

    private void updateTable() {
        //System.out.println("------------ STARTING TICK ------------");
        for (int i = 0; i < bigTable.getModel().getRowCount(); i++) {
            bigTable.getModel().setValueAt(floorBank.getFloorsArray().get(i).getVisitorCount(), i, 0);
            //System.out.println("floor " + i + " contains " + floorBank.getFloorsArray().get(i).getVisitorCount() + " visitors");
        }
        for (int i = 0; i < bigTable.getModel().getRowCount(); i++) {
            for (int j = 1; j < bigTable.getModel().getColumnCount(); j++) {
                //System.out.println(i + ", " + j + ", " + elevatorBank.getElevatorsArray().size());
                if (elevatorBank.getElevatorsArray().get(j - 1).getMyFloor() == i) {
                    bigTable.getModel().setValueAt(elevatorBank.getElevatorsArray().get(j - 1).getPassengerCount(), i, j);
                } else {
                    bigTable.getModel().setValueAt(null, i, j);
                }
            }
        }
        elevatorBank.tick();
    }//updateTable

    public static void main(String args[]) {
        Controller2 controller = new Controller2();
        /* Create and display the form */
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
    private javax.swing.JButton btnMoveElevator;
    private javax.swing.JButton btnMovePassenger;
    private javax.swing.JButton btnMoveVisitor;
    private javax.swing.JButton btnResetTable;
    private javax.swing.JButton btnRunFullTick;
    private javax.swing.JButton btnSaveScenario;
    private javax.swing.JButton btnShowPassengers;
    private javax.swing.JButton btnShowVisitors;
    private javax.swing.JButton btnStartSimulation;
    private javax.swing.JButton btnStopSimulation;
    private javax.swing.JButton btnUpdateTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblElevator;
    private javax.swing.JLabel lblElevators;
    private javax.swing.JLabel lblFloor;
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
    private javax.swing.JTextField tfMoveElevator1;
    private javax.swing.JTextField tfMoveElevator2;
    private javax.swing.JTextField txtElevators;
    private javax.swing.JTextField txtFloors;
    // End of variables declaration//GEN-END:variables
}//class TestWindow
