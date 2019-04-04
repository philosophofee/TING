package edu.bristolcc.TING;

public class ThreadAnimation implements Runnable {

    private static long ANIMATION_TIMEOUT_MS = 500; // half second
    private Controller controller;
    public int count;

    public ThreadAnimation(Controller controller) {
        this.controller = controller;
    }//ThreadAnimation

    public void setSpeed(long speed) {
        this.ANIMATION_TIMEOUT_MS = speed;
    }
    
    @Override
    public void run() {

        for (;;) {
            if (controller.simulationStatus == true) {

                try {
                    Thread.sleep(ANIMATION_TIMEOUT_MS);
                } catch (InterruptedException ex) {
                    return;
                }
                controller.animate();
                ++count;
            } else {
                //do nothing
            }
        }
    }//run

    public int getCount() {
        return count;
    }//getCount

    public void setCount(int count) {
        this.count = count;
    }//setCount

}//class ThreadAnimation