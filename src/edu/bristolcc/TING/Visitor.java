package edu.bristolcc.TING;

enum VisitorStatus {

    WAITING, BOARDING, RIDING, ARRIVED;
}

public class Visitor {

    public int MY_FLOOR;
    public int DESTINATION;

    public VisitorStatus MY_STATUS;
    public final String MY_NAME;

    public Visitor(int myFloor, int destination) {
        this.MY_FLOOR = myFloor;
        this.DESTINATION = destination;
        this.MY_NAME = this.toString().replace("edu.bristolcc.TING.Visitor@", "");

        this.MY_STATUS = VisitorStatus.WAITING;
        //System.out.println("A visitor on floor " + MY_FLOOR + "+1" + " ,wants to go to floor " + DESTINATION + "+1");
    }//Visitor

    public int getMY_FLOOR() {
        return MY_FLOOR;
    }//getMY_FLOOR

    public void setMY_FLOOR(int MY_FLOOR) {
        this.MY_FLOOR = MY_FLOOR;
    }//setMY_FLOOR

    public void tick() {

        if (MY_STATUS == VisitorStatus.WAITING) {
            System.out.println("Hello from " + MY_NAME + ". I am currently waiting and I would like to go to floor " + DESTINATION + ". my current floor is " + MY_FLOOR);
        }

        if (MY_STATUS == VisitorStatus.BOARDING) {
            System.out.println("Hello from " + MY_NAME + ". I am currently at my destination, floor " + DESTINATION);
        }

        if (MY_STATUS == VisitorStatus.RIDING) {
            System.out.println("Hello from " + MY_NAME + ". I am currently riding and I would like to go to floor " + DESTINATION + ". my current floor is " + MY_FLOOR);
        }

        if (MY_STATUS == VisitorStatus.ARRIVED) {
            System.out.println("Hello from " + MY_NAME + ". I am currently at my destination, floor " + DESTINATION);
        }

        //is there an elevator open?
        //am i on my destination floor?
        //am i going to get in an elevator?
        //am i waiting for an elevator?
    }//tick

}//Class Visitor
