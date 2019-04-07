package edu.bristolcc.TING;

import java.util.Random;

public class TestWindow extends javax.swing.JFrame {

    FloorBank floorBank = new FloorBank();
    ElevatorBank elevatorBank;
    Controller2 controller;

    public TestWindow() {

        initComponents();
        //this.floorBank = new FloorBank(bigTable.getModel().getRowCount());
        floorBank.instantiate(bigTable.getModel().getRowCount());
        elevatorBank = new ElevatorBank(bigTable.getModel().getColumnCount() - 1, bigTable.getModel().getRowCount()); //because the floor column doesn't count
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        bigTable = new javax.swing.JTable();
        btnAddVisitor = new javax.swing.JToggleButton();
        btnUpdateTable = new javax.swing.JButton();
        btnMoveElevator = new javax.swing.JButton();
        tfMoveElevator1 = new javax.swing.JTextField();
        tfMoveElevator2 = new javax.swing.JTextField();
        lblFloor = new javax.swing.JLabel();
        lblElevator = new javax.swing.JLabel();
        btnMoveVisitor = new javax.swing.JButton();
        btnMovePassenger = new javax.swing.JButton();
        btnRunFullTick = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        btnMoveElevator.setText("Move [Elevator] by [Floor]");
        btnMoveElevator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveElevatorActionPerformed(evt);
            }
        });

        tfMoveElevator1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMoveElevator1ActionPerformed(evt);
            }
        });

        tfMoveElevator2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMoveElevator2ActionPerformed(evt);
            }
        });

        lblFloor.setText("Floor: ");

        lblElevator.setText("Elevator: ");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddVisitor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMoveElevator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lblElevator)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfMoveElevator1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblFloor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfMoveElevator2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnMoveVisitor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMovePassenger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRunFullTick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnUpdateTable)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddVisitor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMoveElevator)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMoveElevator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMoveElevator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFloor)
                    .addComponent(lblElevator))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMoveVisitor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMovePassenger)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRunFullTick)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateTable)
                .addGap(97, 97, 97))
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
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMoveElevator1ActionPerformed

    private void btnAddVisitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddVisitorActionPerformed
        //generates random number for floor a visitor gets added to
        Random rand = new Random();
        int random_integer = rand.nextInt(bigTable.getModel().getRowCount() - 0) + 0;

        floorBank.getFloorsArray().get(random_integer).addVisitorToFloor(bigTable.getModel().getRowCount());
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
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMoveElevator2ActionPerformed

    private void btnRunFullTickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRunFullTickActionPerformed
        
        /*NEED TO FIND A BETTER WAY TO KEEP TRACK OF VISITORS*/
        /*RIGHT NOW THIS ONLY WORKS FOR 1 VISITOR AND 1 ELEVATOR*/
        
        for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {
            if (floorBank.getFloorsArray().get(i).getVisitorCount() > 0) {
                if (((elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR) - (floorBank.getFloorsArray().get(i).FLOOR_LEVEL)) != 0) {
                    elevatorBank.moveElevator(0, -((elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR) - (floorBank.getFloorsArray().get(i).FLOOR_LEVEL)));
                    if (elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR == floorBank.getFloorsArray().get(i).FLOOR_LEVEL) {
                        moveOnToElevator();
                        elevatorBank.moveElevator(0, -((elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR) - (floorBank.getFloorsArray().get(i).DESTINATION)));
                        moveOffOfElevator();
                    }
                } else {
                    elevatorBank.moveElevator(0, ((elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR) + (floorBank.getFloorsArray().get(i).DESTINATION)));
                    if (elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR == floorBank.getFloorsArray().get(i).FLOOR_LEVEL) {
                        moveOnToElevator();
                        elevatorBank.moveElevator(0, ((elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR) + (floorBank.getFloorsArray().get(i).DESTINATION)));
                        moveOffOfElevator();
                    }
                }
            }
        }
        updateTable();
    }//GEN-LAST:event_btnRunFullTickActionPerformed

    public void moveOnToElevator() {

        /*NEED TO FIND A BETTER WAY TO KEEP TRACK OF VISITORS*/
        /*RIGHT NOW THIS ONLY WORKS FOR 1 VISITOR AND 1 ELEVATOR*/
        
        //working on multiple elevators once first elevator is full switch to next one
        /*for (int j = 0; j < elevatorBank.getElevatorsArray().size(); ++j) {
         if (elevatorBank.getElevatorsArray().get(j).getPassengers() == 10) {
         elevator_floor = elevatorBank.getElevatorsArray().get(++j).CURRENT_FLOOR;
         System.out.println("elevator " + elevatorBank.getElevatorsArray().get(++j).MY_IDENTIFIER + " is on floor " + elevator_floor);
         }
         }*/
        //hard coded for only first elevator
        int elevator_floor = elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR;

        for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {

            int visitor_floor = floorBank.getFloorsArray().get(i).FLOOR_LEVEL;

            if ((floorBank.getFloorsArray().get(i).getVisitorCount() > 0) && (elevatorBank.getElevatorsArray().get(0).getPassengers() < 10)) {
                if (visitor_floor == elevator_floor) {
                    for (int j = 0; j < floorBank.getFloorsArray().get(i).getVisitorCount(); ++j) {
                        elevatorBank.getElevatorsArray().get(0).swipeVisitorOn(floorBank.getFloorsArray().get(i).getVisitorsArray().get(j));
                        floorBank.getFloorsArray().get(i).giveVisitorToElevator(floorBank.getFloorsArray().get(i).getVisitorsArray().get(j));
                    }
                }
            }
        }
    }//moveOnToElevator

    public void moveOffOfElevator() {
        
        /*NEED TO FIND A BETTER WAY TO KEEP TRACK OF VISITORS*/
        /*RIGHT NOW THIS ONLY WORKS FOR 1 VISITOR AND 1 ELEVATOR*/
        
        //hard coded for only first elevator
        int elevator_floor = elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR;

        for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {

            int visitor_floor = floorBank.getFloorsArray().get(i).FLOOR_LEVEL;

            if ((elevatorBank.getElevatorsArray().get(0).getPassengers() > 0) && (elevatorBank.getElevatorsArray().get(0).getPassengers() <= 10)) {
                if (visitor_floor == elevator_floor) {
                    for (int j = 0; j < elevatorBank.getElevatorsArray().get(0).getPassengers(); ++j) {
                        floorBank.getFloorsArray().get(i).recieveVisitorFromElevator(elevatorBank.getElevatorsArray().get(0).PASSENGERS.get(j));
                        elevatorBank.getElevatorsArray().get(0).swipeVisitorOff(elevatorBank.getElevatorsArray().get(0).PASSENGERS.get(j));
                    }
                }
            }
        }
    }//moveOffOfElevator

    private void updateTable() {
        System.out.println("------------ STARTING TICK ------------");
        for (int i = 0; i < bigTable.getModel().getRowCount(); i++) {
            System.out.println("floor " + i + " contains " + floorBank.getFloorsArray().get(i).getVisitorCount() + " visitors");
        }
        for (int i = 0; i < bigTable.getModel().getRowCount(); i++) {
            bigTable.getModel().setValueAt(floorBank.getFloorsArray().get(i).getVisitorCount(), i, 0);
        }
        for (int i = 0; i < bigTable.getModel().getRowCount(); i++) {
            for (int j = 1; j < bigTable.getModel().getColumnCount(); j++) {
                //System.out.println(i + ", " + j + ", " + elevatorBank.getElevatorsArray().size());
                if (elevatorBank.getElevatorsArray().get(j - 1).getMyFloor() == i) {
                    bigTable.getModel().setValueAt(elevatorBank.getElevatorsArray().get(j - 1).getPassengers(), i, j);
                } else {
                    bigTable.getModel().setValueAt(null, i, j);
                }
            }
        }
        elevatorBank.tick();
    }//updateTable

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestWindow().setVisible(true);
            }
        });
    }//main

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bigTable;
    private javax.swing.JToggleButton btnAddVisitor;
    private javax.swing.JButton btnMoveElevator;
    private javax.swing.JButton btnMovePassenger;
    private javax.swing.JButton btnMoveVisitor;
    private javax.swing.JButton btnRunFullTick;
    private javax.swing.JButton btnUpdateTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblElevator;
    private javax.swing.JLabel lblFloor;
    private javax.swing.JTextField tfMoveElevator1;
    private javax.swing.JTextField tfMoveElevator2;
    // End of variables declaration//GEN-END:variables
}
