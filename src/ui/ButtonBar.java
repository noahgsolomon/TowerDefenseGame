package ui;

import objects.Tile;
import scenes.Playing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameStates.MENU;
import static main.GameStates.setGameStates;

public class ButtonBar {

    private int x, y, width, height;
    private Playing playing;
    private MyButton bMenu;
    public static MyButton bRemove = new MyButton("Remove", 10, 700, 48, 24);

    private Tile selectedTile;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();

    public ButtonBar(int x, int y, int width, int height, Playing playing) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.playing = playing;
        initButtons();
    }

    private void initButtons() {
        bMenu = new MyButton("Menu", 5, 650, 96, 48);
        bRemove = new MyButton("Remove", 5+96/2-24, 705, 48, 24);

        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (w * 1.1);
        int i = 0;

        for (Tile tile : playing.getTileManager().tiles) {
            tileButtons.add(new MyButton(tile.getName(), xStart + xOffset * i, yStart, w, h, i));
            i++;
        }

    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        bRemove.draw(g);

        drawTileButtons(g);
        drawSelectedTile(g);

    }

    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null){
            g.drawImage(selectedTile.getSprite(), 550, 650, 50, 50, null);
            g.setColor(Color.yellow);
                g.drawRect(550,650, 50, 50);

        }


    }

    private void drawTileButtons(Graphics g) {
        for (MyButton b : tileButtons){

            //sprite
            g.drawImage(getButtImg(b.getId()), b.x, b.y, b.width, b.height, null);

            //mouseover
            if (b.isMouseOver()){
                g.setColor(Color.white);
            } else {
                g.setColor(Color.BLACK);
            }

            //Border
            g.drawRect(b.x, b.y, b.width, b.height);

        //mousepressed
            if (b.isMousePressed()) {
                g.drawRect(b.x + 1, b.y + 1, b.width - 2, b.height - 2);
                g.drawRect(b.x + 2, b.y + 2, b.width - 4, b.height - 4);
            }
        }
    }

    public BufferedImage getButtImg(int id){
        return playing.getTileManager().getSprite(id);
    }

    public void draw(Graphics g){
        g.setColor(new Color(99, 56, 15));
        g.fillRect(x, y, width, height);
        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x,y)){
            setGameStates(MENU);
        }
        if (bRemove.getBounds().contains(x, y)){
            selectedTile = null;
        }
        else {
            for (MyButton b : tileButtons){
                if (b.getBounds().contains(x, y)){
                    selectedTile = playing.getTileManager().getTile(b.getId());
                    playing.setSelectedTile(selectedTile);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)){
            bMenu.setMousePressed(true);
        }
        if (bRemove.getBounds().contains(x, y)){
            bRemove.setMousePressed(true);
        }
        else {
            for (MyButton b : tileButtons) {
                if (b.getBounds().contains(x, y)) {

                    b.setMousePressed(true);
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bRemove.setMouseOver(false);

        for (MyButton b : tileButtons)
            b.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y)){
            bMenu.setMouseOver(true);
        }
        if (bRemove.getBounds().contains(x, y)){
            bRemove.setMouseOver(true);
        }
        else {
            for (MyButton b : tileButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bRemove.resetBooleans();
        for (MyButton b : tileButtons) {
            b.resetBooleans();
        }
    }
}
