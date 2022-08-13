package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable{ //builds the window on the screen

    private GameScreen gameScreen; //making variable for the panel

    private BufferedImage img;//displays image

    private Thread gameThread;
    private final double FPS_SET = 120;
    private final double UPS_SET = 60;

    public Game(){

        importImg();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //game opens center of screen
        gameScreen = new GameScreen(img);
        add(gameScreen); //adds the panel to the frame

        pack(); //lets windowmanager set size for us given the values we put in for dimension
        setVisible(true);
    }

    private void importImg() {

        InputStream is = getClass().getResourceAsStream("/spriteatlas.png"); //reads image

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void start() {
        gameThread = new Thread(this);

        gameThread.start();
    }

    private void updateGame() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1_000_000_000.0 / FPS_SET;
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates = 0;

        long now;

        while (true){

            now = System.nanoTime();
            //render
        if (now - lastFrame >= timePerFrame) {
            repaint();
            lastFrame = now;
            frames++;
        }

        //update
        if (now - lastUpdate >= timePerUpdate){
            updateGame();
            lastUpdate = now;
            updates++;
        }

        //fps and ups
        if (System.currentTimeMillis() - lastTimeCheck >= 1000){
            System.out.println("FPS: " + frames + " | UPS: " + updates);
            frames = 0;
            updates = 0;
            lastTimeCheck = System.currentTimeMillis();
        }

        }
        
    }
}
