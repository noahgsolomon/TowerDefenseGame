package scenes;

import helps.LevelBuild;
import main.Game;
import managers.TileManager;
import objects.Tile;
import ui.ButtonBar;
import ui.MyButton;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods{
    private int[][] lvl;
    private TileManager tileManager;
    private boolean mousePressed;
    private boolean mouseDragged;

    private ButtonBar buttonBar;
    private int mouseX, mouseY;
    private int lastTilex, lastTileY, lastTileId;
    private Tile selectedTile;
    private boolean drawSelect;
    public int i = 0;


    public Playing(Game game) {
        super(game);

        lvl = LevelBuild.getLevelData();
        tileManager = new TileManager();
        buttonBar = new ButtonBar(0, 640, 640, 100, this);

    }


    @Override
    public void render(Graphics g) {
        if (ButtonBar.isRemove()) {
            lvl = LevelBuild.getPlayingReset();
        }
        if (ButtonBar.isGenerate()){
            lvl = LevelBuild.generateMap();
        }
            for (int y = 0; y < lvl.length; y++) {
                for (int x = 0; x < lvl[y].length; x++) {
                    int id = lvl[y][x];
                    g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
                }
            }
        buttonBar.draw(g);
        drawSelectedTile(g);
    }


    private void drawSelectedTile(Graphics g) {
        if (selectedTile != null && drawSelect){
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY,  32, 32, null);
        }
        if ((isMouseDragged() || isMousePressed()) && selectedTile != null) {
            g.setColor(Color.yellow);
            g.drawRect(mouseX, mouseY, 32, 32);
        }
    }

    public void setSelectedTile(Tile tile){
         this.selectedTile = tile;
         drawSelect = true;
    }

    public TileManager getTileManager(){
        return tileManager;
    }

    @Override
    public void mouseClicked(int x, int y) {

        if (y >= 640){
            buttonBar.mouseClicked(x,y);
        }
        else {
                changeTile(mouseX, mouseY);
        }
    }

    private void changeTile(int x, int y) {
        if (selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;

            if (lastTilex == tileX && lastTileY == tileY && lastTileId == selectedTile.getId()) {
                return;
            }
            lastTilex = tileX;
            lastTileY = tileY;
            lastTileId = selectedTile.getId();
            lvl[tileY][tileX] = selectedTile.getId();

        }
    }

    @Override
    public void mouseMoved(int x, int y) {

        if (y >= 640){
            buttonBar.mouseMoved(x,y);
            drawSelect = false;
        }
        else {
            drawSelect = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {

        if (y >= 640){
            buttonBar.mousePressed(x,y);
        }
        setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
            buttonBar.mouseReleased(x,y);
            resetBooleans();

    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y>= 640){

        }
        else {
                changeTile(x, y);
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
        setMouseDragged(true);
    }
    public void setMouseDragged(boolean mouseDragged){
        this.mouseDragged = mouseDragged;
    }
    public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
    }
    public boolean isMousePressed(){
        return mousePressed;
    }
    public boolean isMouseDragged(){
        return mouseDragged;
    }

    public void resetBooleans(){
        this.mouseDragged = false;
        this.mousePressed = false;
    }
}
