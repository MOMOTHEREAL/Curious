package ca.momoperes.curious.ui;

import ca.momoperes.curious.Curious;

public class RenderThread extends Thread {

    @Override
    public void run() {
        while (true) {
            Curious.getInstance().getRender().repaint();


        }
    }
}
