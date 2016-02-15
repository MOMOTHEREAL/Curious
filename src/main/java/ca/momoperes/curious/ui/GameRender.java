package ca.momoperes.curious.ui;

import ca.momoperes.curious.Curious;
import ca.momoperes.curious.game.Block;
import ca.momoperes.curious.game.Location;

import javax.swing.*;
import java.awt.*;

public class GameRender extends JPanel {

    private GameFrame frame;
    private Curious game;

    public GameRender(GameFrame frame, Curious game) {
        this.frame = frame;
        this.game = game;
        setSize(frame.getSize());
        setVisible(true);
        setBackground(Color.WHITE);
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
        for (Block block : game.getWorld().getBlocks()) {
            int x = (int) block.getLocation().getX() * 64 + (game.getCamera().x);
            int y = (int) block.getLocation().getY() * 64 + (game.getCamera().y);
            if (x < -100 || x > getWidth() + 100 || y < -100 || y > getHeight() + 100)
                continue;
            g.drawImage(Textures.get(block.getType().getTextureName()), x, y, 64, 64, null);
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
                //g.drawRect(x, y - propH, propW, propH);
            }
        }

        for (Block block : game.getWorld().getBlocks()) {

            int x = (int) block.getLocation().getX() * 64 + (game.getCamera().x);
            int y = (int) block.getLocation().getY() * 64 + (game.getCamera().y);

            if (x < -100 || x > getWidth() + 100 || y < -100 || y > getHeight() + 100)
                continue;

            int cameraX = (game.getCamera().x);
            int cameraY = (game.getCamera().y);

            int playerX = (int) Math.floor(-(cameraX - Curious.getInstance().getRender().getWidth() / 2) / 64);
            int playerY = (int) Math.ceil(-(cameraY - Curious.getInstance().getRender().getHeight() / 2) / 64);

            Location player = new Location(playerX, playerY);
            Location blockLocation = block.getLocation();
            int bright = 0;
            int distance = (int) Math.floor(blockLocation.distance(player));

            if (distance < 5) {

                if (distance <= 1) {
                    bright = 25 - 5;
                } else {
                    bright = 25 - distance * 5;
                }

            }

            g.setColor(new Color(0, 0, 0, 255 - bright));
            g.fillRect(x - 32, y - 32, 64, 64);
        }
    }
}
