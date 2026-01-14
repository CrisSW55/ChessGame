package net.crys;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private GamePanel gamePanel;
    public GameWindow(){
        setTitle("Chess");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initClasses();
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initClasses(){
        gamePanel = new GamePanel();
    }

    public static void main(String[] args) {
        new GameWindow();
    }

}
