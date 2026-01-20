package net.crys.pieces;

import net.crys.GamePanel;
import net.crys.board.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Pawn extends ChessPiece {
    private GamePanel gamePanel;

    public Pawn(String pos,Color color,ChessPieceType type,GamePanel gamePanel) {
        super(pos,color,type);
        this.gamePanel = gamePanel;
        if(color == Color.WHITE){
            setNextPos(pos);
            setDirections(pos);
            attackPositions = new String[2];
            setAttackPositions(pos);
            System.out.println("Current pos: " + pos);
            System.out.println("Attack pos: " + attackPositions[0] + "," + attackPositions[1]);
        }



        id = 0;
    }


    public Pawn(int x, int y, int width, int height, GamePanel gamePanel) {
        super(x, y, width, height, gamePanel);
        loadImg();

    }

    @Override
    public void loadImg() {
        //Black Pawn
        if (color  == Color.BLACK) {
            img = temp.getSubimage(5 * gamePanel.getDefaultTileSize(),
                    9 * gamePanel.getDefaultTileSize(),
                    gamePanel.getDefaultTileSize(),
                    gamePanel.getDefaultTileSize());
        }
        //White Pawn
        else if(color  == Color.WHITE){
            img = temp.getSubimage(5 * gamePanel.getDefaultTileSize(),
                    8 * gamePanel.getDefaultTileSize(),
                    gamePanel.getDefaultTileSize(),
                    gamePanel.getDefaultTileSize());
        }
    }

    @Override
     public void newPos(ChessPiece whitePiece, ArrayList<ChessPiece> blackPieces, Map.Entry<String,Square> entry, String nextPos){
                    whitePiece.setPos(nextPos);
                    whitePiece.setNextPos(whitePiece.getPos());
                    whitePiece.setAttackPositions(whitePiece.getPos());
                    whitePiece.setX(entry.getValue().getX());
                    whitePiece.setY(entry.getValue().getY());
                    whitePiece.getBounds().x = whitePiece.getX();
                    whitePiece.getBounds().y = whitePiece.getY();
                    whitePiece.selected = false;
                    //displayPos(whitePiece);
    }




    public boolean isSameNextPos(ChessPiece whitePiece, ArrayList<ChessPiece> blackPieces){
        for(ChessPiece blackPiece : blackPieces){
            if(blackPiece.getPos().equals(whitePiece.getNextPos())){
                return true;
            }
        }
        return false;
    }

    public boolean isSameNextPos2(ChessPiece whitePiece, ArrayList<ChessPiece> blackPieces){
        for(ChessPiece blackPiece : blackPieces){
            if(blackPiece.getPos().equals(whitePiece.getNextPos2())){
                return true;
            }
        }
        return false;
    }

    private void displayPos(ChessPiece piece){
        System.out.println("CurrentPos: " + piece.getPos());
        System.out.println("NextPos: " + piece.getNextPos());
        System.out.println("xPos: " + piece.getX());
        System.out.println("yPos: " + piece.getY());
        System.out.println("isSelected: " + piece.selected);
    }




    @Override
    public void setNextPos(String pos) {
        if(isInitPos()){
            //If the pawn is at initPos can move 1 or 2 squares upwards
            int num = Character.getNumericValue(pos.charAt(1));
            this.nextPos = pos.charAt(0) + String.valueOf(num + 1);
            this.nextPos2 = pos.charAt(0) + String.valueOf(num + 2);
        }
        else {//Or else the pawn can only move 1 square at a time
            int num = Character.getNumericValue(pos.charAt(1));
            int nextNum = num + 1;
            this.nextPos = pos.charAt(0) + String.valueOf(nextNum);
            this.nextPos2 = "";
        }
    }

    @Override
    public void setAttackPositions(String pos) {
        if(!up){
            attackPositions[0] = null;
            attackPositions[1] = null;
            return;
        }
        if(!left){attackPositions[0]= null;}
        if(!right){attackPositions[1]= null;}

        if(left & up){
            char letter = pos.charAt(0);
            int num = Character.getNumericValue(pos.charAt(1));
            num = num +1;
            int indexL  = letters.indexOf(letter);
            attackPositions[0] = String.valueOf(letters.charAt(indexL-1)) + String.valueOf(num);
        }
        if(right & up){
            char letter = pos.charAt(0);
            int num = Character.getNumericValue(pos.charAt(1));
            num = num + 1;
            int indexL  = letters.indexOf(letter);
            attackPositions[1] = letters.charAt(indexL+1) + String.valueOf(num);
        }
    }

    @Override
    public boolean canMove() {
      return false;
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(img,x,y,width,height,null);
    }
    @Override
    public void update(){

    }


}
