package ca.momoperes.curious;

import ca.momoperes.curious.game.World;
import ca.momoperes.curious.ui.GameFrame;
import ca.momoperes.curious.ui.GameRender;
import ca.momoperes.curious.ui.RenderThread;
import ca.momoperes.curious.ui.Textures;

import java.awt.*;

public class Curious {

    private GameFrame frame;
    private GameRender render;
    private World world;
    private Point camera;
    public double rotateAngle;

    public boolean KEY_W,KEY_A,KEY_S,KEY_D;

    private static Curious instance;

    public Curious() {
        this.frame = new GameFrame();
        this.camera = new Point(frame.getWidth() / 2, frame.getHeight() / 2);
        this.render = new GameRender(frame, this);
        this.world = new World();
        instance = this;

        Textures.load("Light.png");
        Textures.load("LightBeam.png");

        new RotateThread(this).start();
        new RenderThread().start();
        new MovementThread().start();
    }

    public Point getCamera() {
        return camera;
    }

    public World getWorld() {
        return world;
    }

    public GameRender getRender() {
        return render;
    }

    public static Curious getInstance() {
        return instance;
    }
}
