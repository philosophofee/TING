package edu.bristolcc.TING;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Statistics extends JPanel implements Serializable {

    private final int padding = 25;
    private final int labelPadding = 25;
    private final Color lineColor = Color.ORANGE;
    private final Color pointColor = Color.DARK_GRAY;
    private final Color gridColor = Color.LIGHT_GRAY;
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private final int pointWidth = 4;
    private int numberYDivisions;
    private int counter;
    public ArrayList<Double> floor;

    public Statistics() {
        this.floor = new ArrayList<>();
    }//Statistics

    public void printElevators(int current_floor) {
        floor.add((double) current_floor);
        // working with first elevator floors
        for (int i = 0; i < floor.size(); ++i) {
            System.out.println("Elevator 0 has been or is on floor: " + floor.get(i));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        // create y axis floor labels and hatch marks
        int numberYDivisions2 = numberYDivisions + 1;
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding) - padding;
            int y1 = y0;
            if (floor.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
            }
            g2.drawLine(x0, y0, x1, y1);

            String yLabel = "F" + String.valueOf(numberYDivisions2);
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            if (yLabel.equalsIgnoreCase("F1")) {
                g2.drawString("L", x0 - 20, y0);
            } else {
                g2.drawString(yLabel, x0 - labelWidth - 15, y0);
            }
            --numberYDivisions2;
        }
        
        // create y axis floor label
        int x2 = 10;
        int y2 = 10;
        g2.drawString("Floor", x2, y2);

        // create x axis time label
        int x4 = getWidth() - padding - labelPadding;
        int y4 = getHeight() - padding;
        g2.drawString("Time", x4, y4);

        //draw test lines for stats
        for (int i = 0; i < numberYDivisions; ++i) {
            int x0 = padding + labelPadding;
            int y0 = getHeight() - padding * 2;
            int x1 = getWidth() / 2;
            int y1 = ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding) - padding;
            g2.setColor(Color.GRAY);
            g2.drawLine(x0, y0, x1, y1);
        }

        //IDEK this is for 1 elevators stats being displayed on line graph
        /*double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (floor.size() - 1);
         double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxFloor() - getMinFloor());
         ArrayList<Point> graphPoints = new ArrayList<>();
         for (int i = 0; i < floor.size(); i++) {
         int x1 = (int) (i * xScale + padding + labelPadding);
         int y1 = (int) ((getMaxFloor() - floor.get(i)) * yScale + padding);
         graphPoints.add(new Point(x1, y1));
         }
        
         Stroke oldStroke = g2.getStroke();
         g2.setColor(lineColor);
         g2.setStroke(GRAPH_STROKE);
         for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x3 = graphPoints.get(i + 1).x;
         int y3 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x3, y3);
         }

         g2.setStroke(oldStroke);
         g2.setColor(pointColor);
         for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - pointWidth / 2;
         int y = graphPoints.get(i).y - pointWidth / 2;
         int ovalW = pointWidth;
         int ovalH = pointWidth;
         g2.fillOval(x, y, ovalW, ovalH);
         }*/
    }//paintComponent

    private double getMinFloor() {
        double minFloor = Double.MAX_VALUE;
        for (Double floor : floor) {
            minFloor = Math.min(minFloor, floor);
        }
        return minFloor;
    }//getMinFloor

    private double getMaxFloor() {
        double maxFloor = Double.MIN_VALUE;
        for (Double floor : floor) {
            maxFloor = Math.max(maxFloor, floor);
        }
        return maxFloor;
    }//getMaxFloor

    public void setElevatorFloors(ArrayList<Double> floors) {
        this.floor = floors;
        invalidate();
        this.repaint();
    }//setElevatorFloors

    public ArrayList<Double> getElevatorFloors() {
        return floor;
    }//getElevatorFloors

    public void setNumberOfFloors(int floors) {
        this.numberYDivisions = floors - 1;
        this.repaint();
    }//setNumberYDivisions

    public void setCounter(int counter) {
        this.counter = counter;
    }//setCounter

}//Class Statistics
