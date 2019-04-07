package edu.bristolcc.TING;

public class Controller2 {
    
    private MainWindow window;
    Visitor visitor;
    Floor floor;
    Elevator2 elevator;

    public Controller2(Visitor visitor, Floor floor, Elevator2 elevator){
        this.visitor = visitor;
        this.floor = floor;
        this.elevator = elevator;
    }
    
    public void setWindow(MainWindow window) {
        this.window = window;
    }//setWindow
    
    public MainWindow getWindow() {
        return window;
    }//getWindow   
    
}


