package net.crys.inputs;

import net.crys.GamePanel;
import net.crys.board.Square;
import net.crys.pieces.ChessPiece;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;

public class MouseHandler implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;

    public MouseHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Checking all pieces positions with mouseEvent and making it true if pressed
        //within the bounds or else piece.selected = false
        gamePanel.mousePressed(e);

    }




    @Override
    public void mouseReleased(MouseEvent e) {
        //Checking all pieces positions with mouseEvent and making it true if pressed
        //within the bounds or else piece.selected = false

        gamePanel.mouseReleased(e);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
