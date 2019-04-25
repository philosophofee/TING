package edu.bristolcc.TING;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Statistics extends JPanel implements Serializable {

    private JFrame frame;
    
    private ArrayList<Integer> yCoords;
    private int startX = 100;
    private int startY = 100;
    private int endX = 400;
    private int endY = 400;
    private int unitX = (endX - startX) / 10;
    private int unitY = (endY - startY) / 10;
    private int prevX = startX;
    private int prevY = endY;

    public ArrayList<Integer> elevator_floors = new ArrayList<>();
    //ArrayList<ArrayList<Integer>> elevator_floors = new ArrayList<ArrayList<Integer>>();
    //2-D Array Idea
    //int elevator;
    //int elevator_floors;
    //ArrayList[][] elevator_stats = new ArrayList[elevator][elevator_floors];
    
    public void updateElevatorInStats(int elevator, int floor){
        elevator_floors.add(floor);
        //elevator_floors.get(elevator).add(floor);
        createAndShowGui();
    }//updateElevatorInStats
    
    public void createAndShowGui() {

        Statistics drawer = new Statistics(elevator_floors);

        frame.add(drawer);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//createAndShowGui

    public Statistics(){
        frame = new JFrame(getClass().getSimpleName());
    }//Statistics
    
    public Statistics(ArrayList<Integer> yCoords) {
        this.yCoords = yCoords;
    }//Statistics

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //draw in the following loop the floors-axis horiztonal grid so it's visible
        g2d.setColor(Color.GRAY);
        for (int i = startY, j = 10; i <= endY; i += unitY, --j) {

            g2d.drawLine(startX, i, endX, i);
            
            g2d.drawString("F" + j, startX  - 35, i);
            
        }

        //draw the x and y axis here
        g2d.setColor(Color.BLACK);
        g2d.drawLine(startX, startY - 25, startX, endY);
        g2d.drawLine(startX, endY, endX + 25, endY);
        g2d.drawString("Floor", startX  - 35, startY - 20);
        g2d.drawString("Time", endX, endY + 20);

        //draw each of our coords in blue color
        g2d.setColor(Color.BLUE);
        for (int y : yCoords) {
            g2d.drawLine(prevX, prevY, prevX += unitX, prevY = endY - (y * unitY));
            
        }
    }//paintComponent

    public Dimension getPreferredSize() {
        return new Dimension(endX + 100, endY + 100);
    }//getPreferredSize
    
    /*private final int padding = 25;
    private final int labelPadding = 25;
    private final Color lineColor = new Color(230, 102, 44, 180);
    private final Color pointColor = new Color(100, 100, 100, 180);
    private final Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
    private final int pointWidth = 4;
    private final int numberYDivisions = 10;
    private List<Double> scores;

    public Statistics() {
        this.scores = new ArrayList<>();
    }//Statistics
    
    public Statistics(List<Double> scores) {
        this.scores = scores;
    }//Statistics

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

        List<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((getMaxScore() - scores.get(i)) * yScale + padding);
            graphPoints.add(new Point(x1, y1));
        }

        // draw white background
        g2.setColor(Color.WHITE);
        g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding, getHeight() - 2 * padding - labelPadding);
        g2.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            if (scores.size() > 0) {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100.0 + "";
                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // and for x axis
        for (int i = 0; i < scores.size(); i++) {
            if (scores.size() > 1) {
                int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
                int x1 = x0;
                int y0 = getHeight() - padding - labelPadding;
                int y1 = y0 - pointWidth;
                if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
                    g2.setColor(gridColor);
                    g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2.setColor(Color.BLACK);
                    String xLabel = i + "";
                    FontMetrics metrics = g2.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                g2.drawLine(x0, y0, x1, y1);
            }
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

    private double getMinScore() {
        double minScore = Double.MAX_VALUE;
        for (Double score : scores) {
            minScore = Math.min(minScore, score);
        }
        return minScore;
    }//getMinScore

    private double getMaxScore() {
        double maxScore = Double.MIN_VALUE;
        for (Double score : scores) {
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }//getMaxScore

    public void setScores(List<Double> scores) {
        this.scores = scores;
        invalidate();
        this.repaint();
    }//setScores

    public List<Double> getScores() {
        return scores;
    }//getScores*/

}//Class Statistics