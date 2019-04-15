package edu.bristolcc.TING;

public class ThreadAnimation2 implements Runnable {

    private static long ANIMATION_TIMEOUT_MS = 1000; // half second
    private Controller2 controller;
    public int count;

    public ThreadAnimation2(Controller2 controller) {
        this.controller = controller;
    }//ThreadAnimation2

    public void setSpeed(long speed) {
        this.ANIMATION_TIMEOUT_MS = speed;
    }//setSpeed

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

}//class ThreadAnimation2
