package ui;

import main.GameStates;

import java.awt.*;

public class MyButton {
    public int x, y, width, height, id;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver;
    private boolean mousePressed;
    private boolean mouseReleased;

    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = -1;

        initBounds();
    }

    //for tile buttons
    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;

        initBounds();
    }

    private void initBounds(){
        this.bounds = new Rectangle(x,y,width,height);
    }

    public void draw(Graphics g){

        //body
        drawBody(g);
        //border
        drawBorder(g);

        //text
        drawText(g);

    }

    private void drawBorder(Graphics g) {
        g.setColor(new Color(73, 43, 29));
        if (GameStates.gameStates == GameStates.SETTINGS) g.setColor(new Color(54, 97, 174));
        if (mousePressed) g.setColor(Color.white);
        g.drawRect(x, y, width, height);
        g.drawRect(x+1, y+1, width-2, height-2);
        if (mousePressed){
            g.drawRect(x+1, y+1, width - 2, height - 2);
            g.drawRect(x+2, y+2, width - 4, height - 4);
        }
    }

    private void drawBody(Graphics g) {
        if (mouseOver && !mousePressed) g.setColor(Color.GRAY);
        else if (mousePressed) g.setColor(new Color(18, 76, 18));
        else g.setColor(new Color(163, 95, 63));
        drawSettingsBody(g);
        drawPlayingBody(g);
        g.fillRect(x, y, width, height);
    }

    private void drawPlayingBody(Graphics g) {
        if (GameStates.gameStates == GameStates.PLAYING){
            g.setColor(new Color(117, 47, 19));
            if (mouseOver && !mousePressed) g.setColor(Color.DARK_GRAY);
            if (mousePressed) g.setColor(new Color(93, 47, 11));
        }
    }

    private void drawSettingsBody(Graphics g) {
        if (GameStates.gameStates == GameStates.SETTINGS){
            g.setColor(new Color(99, 155, 255));
            if (mouseOver && !mousePressed) g.setColor(Color.GRAY);
            if (mousePressed) g.setColor(new Color(54, 97, 174));
        }
    }


    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.setColor(Color.white);
        g.drawString(text,  x - w/2 + width/2, y + h/4 + height / 2);
    }

    public void resetBooleans(){
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
    }
    public void setMouseClicked(boolean mouseReleased){
        this.mouseReleased = mouseReleased;
    }
    public boolean isMouseClicked(){
        return mouseReleased;
    }

    public boolean isMouseOver(){
        return mouseOver;
    }

    public boolean isMousePressed(){
        return mousePressed;
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }
    public Rectangle getBounds(){
        return bounds;
    }

    public int getId(){
        return id;
    }
}
