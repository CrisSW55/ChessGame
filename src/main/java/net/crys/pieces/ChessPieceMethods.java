package net.crys.pieces;

import net.crys.GamePanel;
import net.crys.board.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public interface ChessPieceMethods {
    public void  update();
    public void  draw(Graphics g);
    public void loadImg();
    public void setNextPos(String pos);
    public void setAttackPositions(String pos);
    public void newPos(ChessPiece piece, ArrayList<ChessPiece> blackPieces, Map.Entry<String, Square> entry, String nextPos);
    public boolean canMove();
}
