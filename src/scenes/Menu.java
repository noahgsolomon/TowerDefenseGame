package scenes;

import helps.LevelBuild;
import main.Game;
import managers.TileManager;
import ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static main.GameStates.*;

import static main.GameStates.PLAYING;

public class Menu extends GameScene implements SceneMethods {

    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();

    private MyButton bPlaying, bSettings, bQuit, bCycle, bOff;
    private int[][] lvl;
    private int[][] lvl2;
    private int[][] lvlWater;
    private TileManager tileManager;

    public Menu(Game game) {
        super(game);
        importImg();
        loadSprites();
        initButtons();
        lvl = LevelBuild.getMenuLevelData();
        lvl2 = LevelBuild.getMenuLevelData();
        lvlWater = LevelBuild.getMenuOff();
        tileManager = new TileManager();
    }

    private void initButtons() {

        int w = 150;
        int h = w/3;
        int x = 640 / 2 - w/2;
        int y = 250;
        int yOffset = 100;


        bPlaying = new MyButton("Play", x, y, w, h);
        bSettings = new MyButton("Settings", x, y+yOffset, w, h);
        bQuit = new MyButton("Quit", x, y+yOffset*2, w, h);
        bCycle = new MyButton("Cycle", x, y+yOffset*2 + 65, w/2, h/2);
        bOff =   new MyButton("Off", x+w-w/4, y+yOffset*2 + 65, w/4, h/2);

    }


    @Override
    public void render(Graphics g) {

        levelCycle(g);
        drawButtons(g);
    }

    private void levelCycle(Graphics g) {
        if (bCycle.isMouseClicked()) {
            for (int y = 0; y < lvl2.length; y++) {
                for (int x = 0; x < lvl2[y].length; x++) {
                    int id = lvl2[y][x];
                    g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
                }
            }
        }
        if (!bCycle.isMouseClicked()) {
            for (int y = 0; y < lvl.length; y++) {
                for (int x = 0; x < lvl[y].length; x++) {
                    int id = lvl[y][x];
                    g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
                }
            }
        }
        if (bOff.isMouseClicked())
            for (int y = 0; y < lvlWater.length; y++) {
                for (int x = 0; x < lvlWater[y].length; x++) {
                    int id = lvlWater[y][x];
                    g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
                }
            }
    }

    private void drawButtons(Graphics g) {
        g.setFont(new Font("Verdana", Font.PLAIN, 20));
        bPlaying.draw(g);
        bSettings.draw(g);
        bQuit.draw(g);
        bCycle.draw(g);
        bOff.draw(g);
    }

    private void importImg() {

        InputStream is = getClass().getResourceAsStream("/spriteatlas.png"); //reads image

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void loadSprites() {

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }

    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bPlaying.getBounds().contains(x, y)) {
            setGameStates(PLAYING);
        }
        if (bSettings.getBounds().contains(x, y)){
            setGameStates(SETTINGS);
        }
        if (bQuit.getBounds().contains(x, y)){
            System.exit(0);
        }
        if (bCycle.getBounds().contains(x, y)) {
            bCycle.setMouseClicked(true);
        }
        if (bOff.getBounds().contains(x, y)) {
                bOff.setMouseClicked(true);
                bCycle.setMouseClicked(false);
        }

    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bSettings.setMouseOver(false);
        bQuit.setMouseOver(false);
        bCycle.setMouseOver(false);
        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMouseOver(true);
        }
        else if (bSettings.getBounds().contains(x, y)) {
            bSettings.setMouseOver(true);
        }
        else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
        else if (bCycle.getBounds().contains(x, y)) {
            bCycle.setMouseOver(true);
        }
        else if (bOff.getBounds().contains(x, y)) {
            bOff.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {

        if (bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        }
        else if (bSettings.getBounds().contains(x, y)){
            bSettings.setMousePressed(true);
        }
        else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }
        else if (bCycle.getBounds().contains(x, y)) {
            bCycle.setMousePressed(true);
        }
        else if (bOff.getBounds().contains(x, y)) {
            bOff.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();

    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bSettings.resetBooleans();
        bQuit.resetBooleans();
        bCycle.resetBooleans();
        bOff.resetBooleans();
    }
}
