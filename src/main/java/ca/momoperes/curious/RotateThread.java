package ca.momoperes.curious;

public class RotateThread extends Thread {

    private Curious game;

    public RotateThread(Curious game) {
        this.game = game;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(100l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            game.rotateAngle += 1;
            if (game.rotateAngle >= 180)
                game.rotateAngle = -180;
        }
    }
}
