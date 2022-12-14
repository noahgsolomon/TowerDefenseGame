package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;

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

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public GameScreen(Game game) {
        this.game = game;

        setPanelSize();
    }

    public void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus(); //makes sure no bugs come up. focus of all inputs
    }

    private void setPanelSize() {
        size = new Dimension(640, 740);
        setMinimumSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g); //allows us to paint in the frame

        game.getRender().render(g);

    }



}