package ca.momoperes.curious;

import ca.momoperes.curious.game.Location;

import java.awt.*;

public class MovementThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int xModif=0, yModif=0;

            if (Curious.getInstance().KEY_W) {
                yModif+=6;
            }
            if (Curious.getInstance().KEY_A) {
                xModif+=6;
            }
            if (Curious.getInstance().KEY_S) {
                yModif-=6;
            }
            if (Curious.getInstance().KEY_D) {
                xModif-=6;
            }

            double newX = Curious.getInstance().getCamera().x + xModif;
            double newY = Curious.getInstance().getCamera().y + yModif;
            int blockX = (int)Math.floor(-(newX - Curious.getInstance().getRender().getWidth() / 2) / 64);
            int blockY = (int)Math.ceil(-(newY - Curious.getInstance().getRender().getHeight() / 2) / 64);

            Location location = new Location(blockX, blockY);

            if (Curious.getInstance().getWorld().getBlockAt(location).getProp() == null) {
                Curious.getInstance().getCamera().x += xModif;
                Curious.getInstance().getCamera().y += yModif;
            } else {

            }

        }
    }
}
