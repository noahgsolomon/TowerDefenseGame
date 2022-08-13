package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel { //container we can draw to inside the window

    private Game game;
    private Dimension size;

    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private Render render;

    public GameScreen(Game game) {
        this.game = game;

        setPanelSize();

    }

    private void setPanelSize() {
        size = new Dimension(640, 640);
        setMinimumSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g); //allows us to paint in the frame

        game.getRender().render(g);

    }



}