package ca.momoperes.curious.ui;

import ca.momoperes.curious.Curious;
import ca.momoperes.curious.Main;
import ca.momoperes.curious.game.Block;
import ca.momoperes.curious.game.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class GameRender extends JPanel {

    private GameFrame frame;
    private Curious game;

    public GameRender(GameFrame frame, Curious game) {
        this.frame = frame;
        this.game = game;
        setSize(frame.getSize());
        setVisible(true);
        setBackground(Color.GREEN);
        setLocation(0, 0);
        setFocusable(true);
        requestFocusInWindow();
        requestFocus();
        frame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g1) {
        super.paintComponent(g1);

        Graphics2D g = (Graphics2D) g1;
        if (game.getWorld() == null) {
            g.setFont(g.getFont().deriveFont(32F));
            g.drawString("Generating world...", getWidth() / 2 - g.getFontMetrics().stringWidth("Generating world...") / 2, getHeight() / 2 - g.getFontMetrics().getHeight() / 2);
            return;
        }

        g.setColor(Color.BLUE);
        g.drawImage(Textures.get("Grass.jpg"), 0, 0, getWidth(), getHeight(), null);

        for (Block block : game.getWorld().getBlocks()) {
            int x = (int) block.getLocation().getX() * 64 + (game.getCamera().x);
            int y = (int) block.getLocation().getY() * 64 + (game.getCamera().y);
            if (x < -100 || x > getWidth() + 100 || y < -100 || y > getHeight() + 100)
                continue;



            g.drawImage(Textures.get(block.getType().getTextureName()), x, y, 64, 64, null);
            //g.setColor(Color.GREEN);
            //g.fillRect(x, y, 64, 64);
            //g.drawString(block.getLocation().getX() + ", " + block.getLocation().getY(), x, y);
        }
        g.setColor(Color.GRAY);
        g.fillRect(getWidth() / 2 - g.getFontMetrics().stringWidth("MOMO") / 2 - 1, getHeight() / 2 - 30 - g.getFontMetrics().getHeight() / 2 - 2, g.getFontMetrics().stringWidth("MOMO") + 2, g.getFontMetrics().getHeight() / 2 + 4);

        g.setColor(Color.WHITE);
        g.fillRect(getWidth() / 2 - 8, getHeight() / 2 - 8, 16, 16);
        g.getFont().deriveFont(11f);
        g.drawString("MOMO", getWidth() / 2 - g.getFontMetrics().stringWidth("MOMO") / 2, getHeight() / 2 - 30);

        for (Block block : game.getWorld().getBlocks()) {
            int x = (int) block.getLocation().getX() * 64 + (game.getCamera().x);
            int y = (int) block.getLocation().getY() * 64 + (game.getCamera().y);
            if (x < -100 || x > getWidth() + 100 || y < -100 || y > getHeight() + 100)
                continue;
            if (block.getProp() != null) {
                Image prop = Textures.get(block.getProp().getType().getTextureName());

                int propH = prop.getHeight(null) * 2;
                int propW = prop.getWidth(null) * 2;
                g.drawImage(prop, x, y - propH, propW, propH, null);
                //g.drawRect(x, y - propH/2, propW, propH/2); // Draw hitbox
            }

        }


        //BufferedImage light = (BufferedImage) Textures.get("LightBeam.png");
        BufferedImage light = (BufferedImage) Textures.get("Light.png");
        int lightScale = 1;

        //int lightLevel = 0;
        g.setColor(new Color(0, 0, 0, 230));
        g.fillRect(0, 0, getWidth(), getHeight());
        Point p1 = new Point(0, getHeight() / 2);
        Point p2 = new Point(getWidth() / 2, getHeight() / 2);
        Point p3 = getMousePosition();

        if (p3 == null)
            return;


        AffineTransform at = new AffineTransform();
        //double angle = calculateAngle(p1, p2, p3);
        //System.out.println(angle);

        //at.translate(getWidth() / 2 + getAngleRotationX(angle), getHeight() / 2 + getAngleRotationY(angle));

        // 3. do the actual rotation
        //at.rotate(Math.toRadians(angle));

        at.scale(lightScale, lightScale);
        at.translate(getWidth() / 2 - light.getWidth(null) / 2, getHeight() / 2 - light.getHeight(null) / 2);


        g.drawImage(light,at, null);
        //g.drawRect(getWidth() / 2 - (light.getWidth(null) / 2)*lightScale, getHeight() / 2 - (light.getHeight(null) / 2)*lightScale - 190 * lightScale, light.getWidth(null)*lightScale, light.getHeight(null)*lightScale);
    }

    public double getAngleRotationX(double angle) {
        double x = 180 * Math.sin((angle)/60);
        return x;
    }

    public double calculateAngle(Point view, Point center, Point origin) {
        double a = distance(view, origin);
        double b = distance(view, center);
        double c = distance(origin, center);

        return Math.acos((Math.pow(a, 2) - Math.pow(b, 2) - Math.pow(c, 2)) / (-2 * b * c)) * -100;
    }

    public double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(Math.abs(a.getX() - b.getX()), 2) + Math.pow(Math.abs(a.getY() - b.getY()), 2));
    }

    public double getAngleRotationY(double angle) {
        double y = -180 * Math.sin((angle+90)/60);
        return y;
    }
}
