package net.crys.pieces;

import net.crys.GamePanel;
import net.crys.board.Square;
import net.crys.tools.LoadAsset;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static net.crys.pieces.ChessPieceType.PAWN;

public abstract class ChessPiece implements ChessPieceMethods{
    protected int x,y,width,height;
    protected final BufferedImage temp = LoadAsset.LoadAtlas(LoadAsset.CHESS_ATLAS);
    protected Color color;
    protected BufferedImage img;
    protected GamePanel gamePanel;
    protected Rectangle bounds;
    protected String pos,initialPos;
    protected String nextPos;
    protected String nextPos2;
    protected int id;
    public boolean selected;
    //Directions
    protected boolean left,right,up,down;
    //Left / Right movement
    protected String letters = "abcdefgh";
    protected String[] attackPositions;
    public ChessPieceType type;

    public ChessPiece(String pos,Color color,ChessPieceType type){
        this.pos = pos;
        this.color = color;
        this.initialPos = pos;
        this.type = type;
    }
    public ChessPiece(int x, int y, int width, int height,GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gamePanel = gamePanel;
        //initBounds();
    }

    public void initBounds(int x, int y, int width, int height){
        bounds = new Rectangle(x,y,width,height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getTemp() {
        return temp;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getNextPos() {
        return nextPos;
    }

    public String getNextPos2() {
        return nextPos2;
    }

    public void setNextPos2(String nextPos2) {
        this.nextPos2 = nextPos2;
    }

    public boolean isInitPos(){
        return pos.equals(initialPos);
    }

    public void setDirections(String pos){
        char letter = pos.charAt(0);
        int num = Character.getNumericValue(pos.charAt(1));
        if(letter == 'a'){left = false; right = true;}
        else if(letter == 'h'){left = true; right = false;}
        else{left = true; right = true;}

        if(num == 1){down = false; up = true;}
        else if( num == 8){down = true; up = false;}
        else{down = true; up = true;}
    }

    //can white piece attack the black piece if the attackPos is the same as blackPiece.getPos();
    public String confirmedAttackPos(ArrayList<ChessPiece> whitePieces,ArrayList<ChessPiece> blackPieces){
        for(ChessPiece whitePiece:whitePieces){
            for (int i = 0; i < blackPieces.size(); i++) {
                // Optional: You can add logic here to only set specific objects to null
                // For this example, we set all of them to null
                if(blackPieces.get(i) == null){continue;}
                switch (whitePiece.type){
                    case PAWN:
                        if(whitePiece.attackPositions[0] != null){
                            //if (i == whitePiece.attackPositions.length){break;}
                            if(whitePiece.attackPositions[0].equals(blackPieces.get(i).getPos())){
                                blackPieces.set(i, null);
                                return whitePiece.attackPositions[0];
                            }
                        }
                        if(whitePiece.attackPositions[1] != null){
                            //if (i == whitePiece.attackPositions.length){break;}
                            if(whitePiece.attackPositions[1].equals(blackPieces.get(i).getPos())){
                                blackPieces.set(i, null);
                                return whitePiece.attackPositions[1];
                            }
                        }

                        break;
                    case ROOK:break;
                    case KNIGHT:break;
                    case BISHOP:break;
                    case QUEEN:break;
                    case KING:break;
                }

            }


        }


        return "";
    }

}
