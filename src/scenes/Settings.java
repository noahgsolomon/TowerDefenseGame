package scenes;

import main.Game;
import main.GameStates;
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
    public Settings(Game game) {
        super(game);
        initButtons();
        importImg();
    }

    private void initButtons() {
            bMenu = new MyButton("Menu", 2, 2, 100, 30);
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
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 6; y++) {
                g.drawImage(img, x*121, y*121, null);
            }
        }

        drawButtons(g);
    }
    private void drawButtons(Graphics g) {
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
}
