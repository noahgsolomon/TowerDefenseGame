package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable{ //builds the window on the screen

    private GameScreen gameScreen; //making variable for the panel

    private Thread gameThread;
    private final double FPS_SET = 120;
    private final double UPS_SET =  60;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    //classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;




    public Game(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //game opens center of screen
        setResizable(false);

        initClasses();
        add(gameScreen); //adds the panel to the frame

        pack(); //lets windowmanager set size for us given the values we put in for dimension
        setVisible(true);
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
    }

    private void initInputs(){
         myMouseListener = new MyMouseListener();
         keyboardListener = new KeyboardListener();

         addMouseListener(myMouseListener);
         addMouseMotionListener(myMouseListener);
         addKeyListener(keyboardListener);

         requestFocus(); //makes sure no bugs come up. focus of all inputs
    }

    private void start() {
        gameThread = new Thread(this);

        gameThread.start();
    }

    private void updateGame() {
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.initInputs();
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

    //getters and setters

    public Render getRender(){
        return render;
    }

    public Menu getMenu() {
        return menu;
    }
    public Playing getPlaying() {
        return playing;
    }
    public Settings getSettings() {
        return settings;
    }
}