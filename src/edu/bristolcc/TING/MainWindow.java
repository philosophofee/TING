package edu.bristolcc.TING;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class MainWindow extends javax.swing.JFrame {

    private Controller controller;
    public static File scenarioFile = new File("scenario.txt");
    private Object pnlStats;

    public MainWindow(Controller controller) {
        this.controller = controller;
        this.setLocationRelativeTo(null);
        
        initComponents();
        adjustTableColumns();
        pnlMain.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // This is only called when the user releases the mouse button.
                adjustTableColumns();
            }
        });
    }//MainWindow

    public void update() {
        int value = controller.getCurrentData();
        
        TableModel model = tblElevators.getModel();
        
        model.setValueAt(value, 0/*row*/, 0/*column*/);
        model.setValueAt(value + 1, 1/*row*/, 1/*column*/);
        model.setValueAt(value + 2, 2/*row*/, 2/*column*/);
        model.setValueAt(value + 3, 3/*row*/, 3/*column*/);

    }//update

    public Controller getController() {
        return controller;
    }//getController

    public void setController(Controller controller) {
        this.controller = controller;
    }//setController

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        pnlMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblElevators = new javax.swing.JTable();
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
        btnResetGrid = new javax.swing.JButton();
        pnlScenario = new javax.swing.JPanel();
        btnSaveScenario = new javax.swing.JButton();
        btnLoadScenario = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnViewStatistics = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TING Elevator Simulation");

        jSplitPane1.setDividerLocation(300);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblElevators.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tblElevators.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "A", "B", "C", "D"
            }
        ));
        tblElevators.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblElevators.setEnabled(false);
        tblElevators.setRowHeight(32);
        jScrollPane1.setViewportView(tblElevators);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(pnlMain);

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
            .addGroup(pnlSimulationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStartSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .addComponent(btnStopSimulation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlSimulationLayout.setVerticalGroup(
            pnlSimulationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSimulationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStartSimulation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStopSimulation)
                .addContainerGap(353, Short.MAX_VALUE))
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

        btnResetGrid.setText("Reset Table");
        btnResetGrid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetGridActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTableLayout.createSequentialGroup()
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFloors)
                            .addComponent(lblElevators))
                        .addGap(12, 12, 12)
                        .addGroup(pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFloors, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(txtElevators))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnResetGrid, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .addComponent(btnConfigureGrid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResetGrid)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlScenarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlScenarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaveScenario, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                    .addComponent(btnLoadScenario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlScenarioLayout.setVerticalGroup(
            pnlScenarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlScenarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSaveScenario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoadScenario)
                .addContainerGap(353, Short.MAX_VALUE))
        );

        tbdPaneMain.addTab("Change Scenario", pnlScenario);

        btnViewStatistics.setText("View Statistics");
        btnViewStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewStatisticsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnViewStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnViewStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(372, Short.MAX_VALUE))
        );

        tbdPaneMain.addTab("View Statistics", jPanel1);

        javax.swing.GroupLayout pnlTabbedPaneLayout = new javax.swing.GroupLayout(pnlTabbedPane);
        pnlTabbedPane.setLayout(pnlTabbedPaneLayout);
        pnlTabbedPaneLayout.setHorizontalGroup(
            pnlTabbedPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbdPaneMain, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlTabbedPaneLayout.setVerticalGroup(
            pnlTabbedPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbdPaneMain)
        );

        jSplitPane1.setRightComponent(pnlTabbedPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfigureGridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigureGridActionPerformed
        controller.resetAnimation();
        try {
            int floors = Integer.parseInt(txtFloors.getText());
            int elevators = Integer.parseInt(txtElevators.getText());

            // configure column names
            String[] columnNames = new String[elevators];
            char[] dummy = {'A'};
            for (int idx = 0; idx < elevators; ++idx) {
                dummy[0] = (char) (idx + 'A');
                columnNames[idx] = new String(dummy);
            }
            // populate 2-dimensional array of data
            Object[][] tableContent = new Object[floors/*rows*/][elevators/*columns*/];
            tblElevators.setModel(new javax.swing.table.DefaultTableModel(tableContent, columnNames));

            adjustTableColumns();
        } catch (NumberFormatException ex) {
            txtFloors.setText("INVALID!");
            txtElevators.setText("INVALID!");
            tblElevators.setModel(new javax.swing.table.DefaultTableModel(4, 4));
        }
    }//GEN-LAST:event_btnConfigureGridActionPerformed

    private void btnStartSimulationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartSimulationActionPerformed
        adjustTableColumns();
        controller.startAnimation();
    }//GEN-LAST:event_btnStartSimulationActionPerformed

    private void btnResetGridActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetGridActionPerformed
        controller.resetAnimation();
        tblElevators.setModel(new javax.swing.table.DefaultTableModel(4, 4));
    }//GEN-LAST:event_btnResetGridActionPerformed

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
        controller.saveScenario(tblElevators, new File(path));
    }//GEN-LAST:event_btnSaveScenarioActionPerformed

    private void btnLoadScenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadScenarioActionPerformed
        FilePicker picker = new FilePicker(null);
        File toLoad = picker.pickFile(".esf", "TING Elevator Scenario File (*.esf)", false);
        if (toLoad == null) {
            return;
        }
        controller.loadScenario(tblElevators, toLoad); //open ui to load scenario file into jTable
        adjustTableColumns();
    }//GEN-LAST:event_btnLoadScenarioActionPerformed

    private void btnViewStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewStatisticsActionPerformed
        //Statistics.createAndShowGui();
        List<Double> scores = new ArrayList<>();
        Random random = new Random();
        int maxDataPoints = 40;
        int maxScore = 10;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add((double) random.nextDouble() * maxScore);
        }
        pnlStats.setScores(scores);
    }//GEN-LAST:event_btnViewStatisticsActionPerformed

    public static void main(String args[]) {
        Controller controller = new Controller();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow window = new MainWindow(controller);
                controller.setWindow(window);
                window.setVisible(true);
            }
        });
    }//main

    public void adjustTableColumns() {
        // adjust column sizes
        TableColumn column = null;
        int columns = tblElevators.getColumnModel().getColumnCount();

        for (int idx = 0; idx < columns; ++idx) {
            column = tblElevators.getColumnModel().getColumn(idx);
            column.setPreferredWidth(pnlMain.getWidth() / columns/*pixels*/);
            column.setWidth(pnlMain.getWidth() / columns/*pixels*/);
        }
        
        tblElevators.setRowHeight(pnlMain.getHeight()/tblElevators.getRowCount()-5);
        tblElevators.getTableHeader().setReorderingAllowed(false);
        
        //this looks pretty
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i=0; i<tblElevators.getColumnCount(); i++) {
            tblElevators.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }//adjustTableColumns

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfigureGrid;
    private javax.swing.JButton btnLoadScenario;
    private javax.swing.JButton btnResetGrid;
    private javax.swing.JButton btnSaveScenario;
    private javax.swing.JButton btnStartSimulation;
    private javax.swing.JButton btnStopSimulation;
    private javax.swing.JButton btnViewStatistics;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblElevators;
    private javax.swing.JLabel lblFloors;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlScenario;
    private javax.swing.JPanel pnlSimulation;
    private javax.swing.JPanel pnlTabbedPane;
    private javax.swing.JPanel pnlTable;
    private javax.swing.JTabbedPane tbdPaneMain;
    private javax.swing.JTable tblElevators;
    private javax.swing.JTextField txtElevators;
    private javax.swing.JTextField txtFloors;
    // End of variables declaration//GEN-END:variables
}//class MainWindow