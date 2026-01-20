package net.crys.board;

import net.crys.pieces.ChessPiece;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.ArrayList;

public class Square{
    private int x,y,width,height;
    private Color color;
    private Color defaultColor;
    private Rectangle bounds;
    //Is square filled and was color chessPiece
    private boolean filled;
    private Color chessPieceColor;
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


    public void setFilledAndChessPieceColor(ArrayList<ChessPiece> whitePieces, ArrayList<ChessPiece> blackPieces){
        for(ChessPiece whitePiece:whitePieces){
            if(whitePiece.getBounds().contains(bounds)){
                filled = true;
                chessPieceColor = Color.WHITE;
                return;
            }
        }

        for(ChessPiece blackPiece:blackPieces){
            if(blackPiece.getBounds().contains(bounds)){
                filled = true;
                chessPieceColor = Color.BLACK;
                return;
            }
        }

        filled = false;
    }

    public boolean isFilled(){return filled;}
    public Color getChessPieceColor(){return chessPieceColor;}
}
