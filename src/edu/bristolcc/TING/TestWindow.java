package edu.bristolcc.TING;

import java.util.ArrayList;
import java.util.Random;

public class TestWindow extends javax.swing.JFrame {

    FloorBank floorBank = new FloorBank();
    ElevatorBank elevatorBank;
    Controller2 controller;

    public TestWindow() {
        initComponents();

        floorBank.instantiate(bigTable.getModel().getRowCount());
        elevatorBank = new ElevatorBank(bigTable.getModel().getColumnCount() - 1, bigTable.getModel().getRowCount()); //because the floor column doesn't count
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        bigTable = new javax.swing.JTable();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        tfMoveElevator1 = new javax.swing.JTextField();
        tfMoveElevator2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

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

        jToggleButton1.setText("Add Visitor to Random Floor");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Update Table");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Move [Elevator] by [Floor]");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tfMoveElevator1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMoveElevator1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Floor: ");

        jLabel2.setText("Elevator: ");

        jButton4.setText("Remove Visitor from Floor and Add to Elevator");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Add Visitor to Floor and Remove from Elevator");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
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
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfMoveElevator1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfMoveElevator2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton2)
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
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMoveElevator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfMoveElevator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(97, 97, 97))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        updateTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        elevatorBank.moveElevator(Integer.parseInt(tfMoveElevator1.getText()), (-Integer.parseInt(tfMoveElevator2.getText())));
        updateTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tfMoveElevator1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMoveElevator1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMoveElevator1ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        //generates random number for floor a visitor gets added to
        Random rand = new Random();
        int random_integer = rand.nextInt(bigTable.getModel().getRowCount() - 0) + 0;

        floorBank.getFloorsArray().get(random_integer).addVisitorToFloor();
        updateTable();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        //hard coded for only first elevator
        int elevator_floor = elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR;

        /*for (int j = 0; j < elevatorBank.getElevatorsArray().size(); ++j) {
            if (elevatorBank.getElevatorsArray().get(j).getPassengers() == 10) {
                elevator_floor = elevatorBank.getElevatorsArray().get(++j).CURRENT_FLOOR;
                System.out.println("elevator " + elevatorBank.getElevatorsArray().get(++j).MY_IDENTIFIER + " is on floor " + elevator_floor);
            }
        }*/
        
        for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {
            int visitor_floor = floorBank.getFloorsArray().get(i).FLOOR_LEVEL;

            if ((floorBank.getFloorsArray().get(i).getVisitorCount() > 0) && (elevatorBank.getElevatorsArray().get(0).getPassengers() < 10)) {
                if (visitor_floor == elevator_floor) {
                    floorBank.getFloorsArray().get(i).giveVisitorToElevator(floorBank.getFloorsArray().get(i).getVisitorsArray().get(0));
                    elevatorBank.getElevatorsArray().get(0).swipeVisitorOn(floorBank.getFloorsArray().get(0).getVisitorsArray().get(0));
                }
            }
            updateTable();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        //hard coded for only first elevator
        int elevator_floor = elevatorBank.getElevatorsArray().get(0).CURRENT_FLOOR;

        for (int i = 0; i < bigTable.getModel().getRowCount(); ++i) {
            int visitor_floor = floorBank.getFloorsArray().get(i).FLOOR_LEVEL;
            if ((elevatorBank.getElevatorsArray().get(0).getPassengers() > 0) && (elevatorBank.getElevatorsArray().get(0).getPassengers() <= 10)) {
                if (visitor_floor == elevator_floor) {
                    floorBank.getFloorsArray().get(i).addVisitorToFloor();
                    elevatorBank.getElevatorsArray().get(0).swipeVisitorOff(elevatorBank.getElevatorsArray().get(0).PASSENGERS.get(0));
                }
            }
        }
        updateTable();
    }//GEN-LAST:event_jButton6ActionPerformed

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
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bigTable;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField tfMoveElevator1;
    private javax.swing.JTextField tfMoveElevator2;
    // End of variables declaration//GEN-END:variables
}
