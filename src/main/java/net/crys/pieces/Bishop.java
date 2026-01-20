package net.crys.pieces;

import net.crys.GamePanel;
import net.crys.board.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Bishop extends ChessPiece{

    public Bishop(String pos,Color color,ChessPieceType type) {super(pos,color,type);  id= 3;}

    public Bishop(int x, int y, int width, int height, GamePanel gamePanel) {
        super(x, y, width, height,gamePanel);


    }

    @Override
    public void newPos(ChessPiece piece, ArrayList<ChessPiece>blackPieces, Map.Entry<String, Square> entry, String nextPos) {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,width,height,null);
    }

    @Override
    public void loadImg() {
        //Black Rook
        if(y == 0) {
            img = temp.getSubimage(2 * gamePanel.getDefaultTileSize(),
                    9 * gamePanel.getDefaultTileSize(),
                    gamePanel.getDefaultTileSize(),
                    gamePanel.getDefaultTileSize());
        }
        //White Rook
        else {
            img = temp.getSubimage(2 * gamePanel.getDefaultTileSize(),
                    8 * gamePanel.getDefaultTileSize(),
                    gamePanel.getDefaultTileSize(),
                    gamePanel.getDefaultTileSize());
        }
    }

    @Override
    public void setNextPos(String pos) {

    }

    @Override
    public void setAttackPositions(String pos) {

    }





    @Override
    public boolean canMove() {
        return false;
    }
}
