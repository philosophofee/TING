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
    private final Color lineColor = new Color(230, 102, 44, 180);
    private final Color pointColor = new Color(100, 100, 100, 180);
    private final Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private final int pointWidth = 4;
    private int numberYDivisions = 10;
    private int counter;
    public ArrayList<Double> elevator_floor;

    public Statistics() {
        this.elevator_floor = new ArrayList<>();
    }//Statistics

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (elevator_floor.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxFloor() - getMinFloor());

        ArrayList<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < elevator_floor.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxFloor() - elevator_floor.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis floors.
        for (int i = 0; i < numberYDivisions + 1; i++) {

            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (elevator_floor.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                /*String yLabel = "F" + numberYDivisions;
                 FontMetrics metrics = g2.getFontMetrics();
                 int labelWidth = metrics.stringWidth(yLabel);
                 g2.drawString(yLabel, x0 - labelWidth - 15, y0 + (metrics.getHeight() / 2) - 3);*/

            }
            g2.drawLine(x0, y0, x1, y1);
            int x2 = 10;
            int y2 = 10;
            g2.drawString("Floor", x2, y2);
            String yLabel = "F" + (numberYDivisions + 1);
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2.drawString(yLabel, x0 - labelWidth - 15, y0 + (metrics.getHeight() / 2) - 3);

        }

        // for x axis time
        for (int i = 0; i < counter+1/*elevator_floor.size()*/; i++) {
            /*if (elevator_floor.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (elevator_floor.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((elevator_floor.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }*/
            int x2 = getWidth() - padding - labelPadding;
            int y2 = getHeight() - 15;
            g2.drawString("Time", x2, y2);
        }

        // create x and y axes 
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

        Stroke oldStroke = g2.getStroke();
        g2.setColor(lineColor);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }//paintComponent

    private double getMinFloor() {
        double minFloor = Double.MAX_VALUE;
        for (Double floor : elevator_floor) {
            minFloor = Math.min(minFloor, floor);
        }
        return minFloor;
    }//getMinFloor

    private double getMaxFloor() {
        double maxFloor = Double.MIN_VALUE;
        for (Double floor : elevator_floor) {
            maxFloor = Math.max(maxFloor, floor);
        }
        return maxFloor;
    }//getMaxFloor

    public void setElevatorFloors(ArrayList<Double> scores) {
        this.elevator_floor = scores;
        invalidate();
        this.repaint();
    }//setElevatorFloors

    public ArrayList<Double> getElevatorFloors() {
        return elevator_floor;
    }//getElevatorFloors

    public void setNumberOfFloors(int floors) {
        this.numberYDivisions = floors - 1;
        this.repaint();
    }//setNumberYDivisions

    public void setCounter(int counter) {
        this.counter = counter;
    }//setCounter
    
}//Class Statistics
