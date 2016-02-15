package ca.momoperes.curious.ui;

import ca.momoperes.curious.Curious;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements KeyListener {

    public GameFrame() throws HeadlessException {
        setExtendedState(MAXIMIZED_BOTH);
        //setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        setUndecorated(true);
        setVisible(true);
        setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);


        this.addKeyListener(this);
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        char i = e.getKeyChar();
        if (i == 'a') {
            Curious.getInstance().KEY_A = true;
        } else if (i == 'w') {
            Curious.getInstance().KEY_W = true;
        } else if (i == 'd') {
            Curious.getInstance().KEY_D = true;
        } else if (i == 's') {
            Curious.getInstance().KEY_S = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        char i = e.getKeyChar();
        if (i == 'a') {
            Curious.getInstance().KEY_A = false;
        } else if (i == 'w') {
            Curious.getInstance().KEY_W = false;
        } else if (i == 'd') {
            Curious.getInstance().KEY_D = false;
        } else if (i == 's') {
            Curious.getInstance().KEY_S = false;
        }
    }
}
