package net.crys.board;

import org.w3c.dom.css.Rect;

import java.awt.*;

public class Square{
    private int x,y,width,height;
    private Color color;
    private Color defaultColor;
    private Rectangle bounds;
    public Square(int x, int y, int width, int height,Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.defaultColor = color;
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(x,y,width,height);
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }


    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
