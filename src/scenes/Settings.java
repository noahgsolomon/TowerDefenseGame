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

import static main.GameStates.MENU;
import static main.GameStates.setGameStates;

public class Settings extends GameScene implements SceneMethods{
    private BufferedImage img;
    private InputStream is;
    private MyButton bMenu;
    private int[][] lvl;
    private TileManager tileManager;
    public Settings(Game game) {
        super(game);
        initButtons();
        importImg();
        lvl = LevelBuild.getSettingsLevelData();
        tileManager = new TileManager();
    }

    private void initButtons() {
            bMenu = new MyButton("Menu", 10, 20, 150, 75);
    }

    private void importImg() {
        is = getClass().getResourceAsStream("/turtle.png"); //reads image

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void render(Graphics g) {
        for (int y = 0; y < lvl.length; y++) {
            for (int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
            }
        }

        drawButtons(g);
    }
    private void drawButtons(Graphics g) {
        g.setFont(new Font("Verdana", Font.PLAIN, 24));
        bMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x,y)){
            setGameStates(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y)){
            bMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)){
            bMenu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
