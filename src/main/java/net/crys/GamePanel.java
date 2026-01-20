package net.crys;

import net.crys.board.Square;
import net.crys.inputs.MouseHandler;
import net.crys.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel implements ActionListener {
    //ChessAtlas Dimensions("Obsolete")
//    private static final int chessAtlasW = 360;
//    private static final int chessAtlasH = 450;
//    private static final int chessAtlasTilesW = 8;
//    private static final int chessAtlasTilesH = 10;
//    private static final int DEFAULT_TSW = chessAtlasW/chessAtlasTilesW; //45
//    private static final int DEFAULT_TSH = chessAtlasH/chessAtlasTilesH; //45
    //GamePanel Variables -> Scale,TileSize, etc..
    private static final int GRID_COL = 8;
    private static final int DEFAULT_TS = 45;
    private static final float SCALE = 1.5f;
    private static final int TS = (int)(DEFAULT_TS * SCALE);//67.5 -> (int) 67
    private static final int GAME_WIDTH = TS*GRID_COL;
    private static final int GAME_HEIGHT = TS*GRID_COL;
    private int FPS = 60;
    private String confirmedPos,priorChessPiecePos = "";
    public boolean startGame;

    //Chess Pieces
//    private ArrayList<Pawn> bPList = new ArrayList<>();
//    private ArrayList<Pawn> wPList = new ArrayList<>();
    private ArrayList<ChessPiece> whitePieces,blackPieces;
    private Pawn bP1,bP2,bP3,bP4,bP5,bP6,bP7,bP8
            ,wP1,wP2,wP3,wP4,wP5,wP6,wP7,wP8;
    private Rook bR1,bR2,wR1,wR2;
    private Knight bK1,bK2,wK1,wK2;
    private Bishop bB1,bB2,wB1,wB2;
    private Queen bQ,wQ;
    private King bK,wK;

    //Board / Squares
    HashMap<String,Square> squares;

    //MouseHandler
    private MouseHandler mH;

    public GamePanel() {
        setPreferredSize(new Dimension(GAME_WIDTH,GAME_HEIGHT));
        initPiecesAndBoard();
        initMouseHandler();
        initTimer();
    }



    private void initPiecesAndBoard(){
        initBoard();
        initPieces();
        addPieces();
        applyPosDim();}
    private void initTimer() {
        Timer timer = new Timer(1000/FPS,this);
        timer.start();
    }
    private void initBoard() {
        //Declare HashMap squares,put new square into it, and begin board setup
        squares = new HashMap<>();
        squares.put("a8",new Square(0,0,TS,TS,Color.GRAY));
        squares.put("b8",new Square(TS,0,TS,TS,Color.GREEN));
        squares.put("c8",new Square(2*TS,0,TS,TS,Color.GRAY));
        squares.put("d8",new Square(3*TS,0,TS,TS,Color.GREEN));
        squares.put("e8",new Square(4*TS,0,TS,TS,Color.GRAY));
        squares.put("f8",new Square(5*TS,0,TS,TS,Color.GREEN));
        squares.put("g8",new Square(6*TS,0,TS,TS,Color.GRAY));
        squares.put("h8",new Square(7*TS,0,TS,TS,Color.GREEN));

        squares.put("a7",new Square(0,TS,TS,TS,Color.GREEN));
        squares.put("b7",new Square(TS,TS,TS,TS,Color.GRAY));
        squares.put("c7",new Square(2*TS,TS,TS,TS,Color.GREEN));
        squares.put("d7",new Square(3*TS,TS,TS,TS,Color.GRAY));
        squares.put("e7",new Square(4*TS,TS,TS,TS,Color.GREEN));
        squares.put("f7",new Square(5*TS,TS,TS,TS,Color.GRAY));
        squares.put("g7",new Square(6*TS,TS,TS,TS,Color.GREEN));
        squares.put("h7",new Square(7*TS,TS,TS,TS,Color.GRAY));

        squares.put("a6",new Square(0,2*TS,TS,TS,Color.GRAY));
        squares.put("b6",new Square(TS,2*TS,TS,TS,Color.GREEN));
        squares.put("c6",new Square(2*TS,2*TS,TS,TS,Color.GRAY));
        squares.put("d6",new Square(3*TS,2*TS,TS,TS,Color.GREEN));
        squares.put("e6",new Square(4*TS,2*TS,TS,TS,Color.GRAY));
        squares.put("f6",new Square(5*TS,2*TS,TS,TS,Color.GREEN));
        squares.put("g6",new Square(6*TS,2*TS,TS,TS,Color.GRAY));
        squares.put("h6",new Square(7*TS,2*TS,TS,TS,Color.GREEN));

        squares.put("a5",new Square(0,3*TS,TS,TS,Color.GREEN));
        squares.put("b5",new Square(TS,3*TS,TS,TS,Color.GRAY));
        squares.put("c5",new Square(2*TS,3*TS,TS,TS,Color.GREEN));
        squares.put("d5",new Square(3*TS,3*TS,TS,TS,Color.GRAY));
        squares.put("e5",new Square(4*TS,3*TS,TS,TS,Color.GREEN));
        squares.put("f5",new Square(5*TS,3*TS,TS,TS,Color.GRAY));
        squares.put("g5",new Square(6*TS,3*TS,TS,TS,Color.GREEN));
        squares.put("h5",new Square(7*TS,3*TS,TS,TS,Color.GRAY));

        squares.put("a4",new Square(0,4*TS,TS,TS,Color.GRAY));
        squares.put("b4",new Square(TS,4*TS,TS,TS,Color.GREEN));
        squares.put("c4",new Square(2*TS,4*TS,TS,TS,Color.GRAY));
        squares.put("d4",new Square(3*TS,4*TS,TS,TS,Color.GREEN));
        squares.put("e4",new Square(4*TS,4*TS,TS,TS,Color.GRAY));
        squares.put("f4",new Square(5*TS,4*TS,TS,TS,Color.GREEN));
        squares.put("g4",new Square(6*TS,4*TS,TS,TS,Color.GRAY));
        squares.put("h4",new Square(7*TS,4*TS,TS,TS,Color.GREEN));

        squares.put("a3",new Square(0,5*TS,TS,TS,Color.GREEN));
        squares.put("b3",new Square(TS,5*TS,TS,TS,Color.GRAY));
        squares.put("c3",new Square(2*TS,5*TS,TS,TS,Color.GREEN));
        squares.put("d3",new Square(3*TS,5*TS,TS,TS,Color.GRAY));
        squares.put("e3",new Square(4*TS,5*TS,TS,TS,Color.GREEN));
        squares.put("f3",new Square(5*TS,5*TS,TS,TS,Color.GRAY));
        squares.put("g3",new Square(6*TS,5*TS,TS,TS,Color.GREEN));
        squares.put("h3",new Square(7*TS,5*TS,TS,TS,Color.GRAY));

        squares.put("a2",new Square(0,6*TS,TS,TS,Color.GRAY));
        squares.put("b2",new Square(TS,6*TS,TS,TS,Color.GREEN));
        squares.put("c2",new Square(2*TS,6*TS,TS,TS,Color.GRAY));
        squares.put("d2",new Square(3*TS,6*TS,TS,TS,Color.GREEN));
        squares.put("e2",new Square(4*TS,6*TS,TS,TS,Color.GRAY));
        squares.put("f2",new Square(5*TS,6*TS,TS,TS,Color.GREEN));
        squares.put("g2",new Square(6*TS,6*TS,TS,TS,Color.GRAY));
        squares.put("h2",new Square(7*TS,6*TS,TS,TS,Color.GREEN));

        squares.put("a1",new Square(0,7*TS,TS,TS,Color.GREEN));
        squares.put("b1",new Square(TS,7*TS,TS,TS,Color.GRAY));
        squares.put("c1",new Square(2*TS,7*TS,TS,TS,Color.GREEN));
        squares.put("d1",new Square(3*TS,7*TS,TS,TS,Color.GRAY));
        squares.put("e1",new Square(4*TS,7*TS,TS,TS,Color.GREEN));
        squares.put("f1",new Square(5*TS,7*TS,TS,TS,Color.GRAY));
        squares.put("g1",new Square(6*TS,7*TS,TS,TS,Color.GREEN));
        squares.put("h1",new Square(7*TS,7*TS,TS,TS,Color.GRAY));
    }
    private void initPieces() {
        //Assign string position into each chess piece instance
        //Black ChessPieces
        bR1 = new Rook("a8",Color.BLACK,ChessPieceType.ROOK);
        bK1 = new Knight("b8",Color.BLACK,ChessPieceType.KNIGHT);
        bB1 = new Bishop("c8",Color.BLACK,ChessPieceType.BISHOP);
        bQ = new Queen("d8",Color.BLACK,ChessPieceType.QUEEN);
        bK = new King("e8",Color.BLACK,ChessPieceType.KING);
        bR2 = new Rook("f8",Color.BLACK,ChessPieceType.ROOK);
        bK2 = new Knight("g8",Color.BLACK,ChessPieceType.KNIGHT);
        bB2 = new Bishop("h8",Color.BLACK,ChessPieceType.BISHOP);
        //"a7" Testing if whitePawn takes out blackPawn while on whitePawn's attackPos
        bP1 = new Pawn("a3",Color.BLACK,ChessPieceType.PAWN,this);
        bP2 = new Pawn("b4",Color.BLACK,ChessPieceType.PAWN,this);
        bP3 = new Pawn("c3",Color.BLACK,ChessPieceType.PAWN,this);
        bP4 = new Pawn("d3",Color.BLACK,ChessPieceType.PAWN,this);
        bP5 = new Pawn("e3",Color.BLACK,ChessPieceType.PAWN,this);
        bP6 = new Pawn("f3",Color.BLACK,ChessPieceType.PAWN,this);
        bP7 = new Pawn("g3",Color.BLACK,ChessPieceType.PAWN,this);
        bP8 = new Pawn("h3",Color.BLACK,ChessPieceType.PAWN,this);

        //White ChessPieces
        wP1 = new Pawn("a2",Color.WHITE,ChessPieceType.PAWN,this);
        wP2 = new Pawn("b2",Color.WHITE,ChessPieceType.PAWN,this);
        wP3 = new Pawn("c2",Color.WHITE,ChessPieceType.PAWN,this);
        wP4 = new Pawn("d2",Color.WHITE,ChessPieceType.PAWN,this);
        wP5 = new Pawn("e2",Color.WHITE,ChessPieceType.PAWN,this);
        wP6 = new Pawn("f2",Color.WHITE,ChessPieceType.PAWN,this);
        wP7 = new Pawn("g2",Color.WHITE,ChessPieceType.PAWN,this);
        wP8 = new Pawn("h2",Color.WHITE,ChessPieceType.PAWN,this);

        wR1 = new Rook("a1",Color.WHITE,ChessPieceType.ROOK);
        wK1 = new Knight("b1",Color.WHITE,ChessPieceType.KNIGHT);
        wB1 = new Bishop("c1",Color.WHITE,ChessPieceType.BISHOP);
        wQ = new Queen("d1",Color.WHITE,ChessPieceType.QUEEN);
        wK = new King("e1",Color.WHITE,ChessPieceType.KING);
        wR2 = new Rook("f1",Color.WHITE,ChessPieceType.ROOK);
        wK2 = new Knight("g1",Color.WHITE,ChessPieceType.KNIGHT);
        wB2 = new Bishop("h1",Color.WHITE,ChessPieceType.BISHOP);
    }
    private void addPieces(){
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        //Adding black ChessPieces first
        blackPieces.add(bR1);blackPieces.add(bK1);blackPieces.add(bB1);
        blackPieces.add(bQ);blackPieces.add(bK);
        blackPieces.add(bB2);blackPieces.add(bK2);blackPieces.add(bR2);
        blackPieces.add(bP1);blackPieces.add(bP2);blackPieces.add(bP3);blackPieces.add(bP4);
        blackPieces.add(bP5);blackPieces.add(bP6);blackPieces.add(bP7);blackPieces.add(bP8);

        //Next, adding white ChessPieces second
        whitePieces.add(wR1);whitePieces.add(wK1);whitePieces.add(wB1);
        whitePieces.add(wQ);whitePieces.add(wK);
        whitePieces.add(wB2);whitePieces.add(wK2);whitePieces.add(wR2);
        whitePieces.add(wP1);whitePieces.add(wP2);whitePieces.add(wP3);whitePieces.add(wP4);
        whitePieces.add(wP5);whitePieces.add(wP6);whitePieces.add(wP7);whitePieces.add(wP8);
    }
    private void applyPosDim(){
        //Apply x,y,width,height,gamePanel to
        //each of the 8 pieces in whitePieces and blackPieces ArrayLists,
        //Applied black dimensions
        for (ChessPiece p:blackPieces) {
            if(p == null){continue;}
            for (Map.Entry<String, Square> entry : squares.entrySet()) {
                if (entry.getKey().equals(p.getPos())) {
                    p.setX(entry.getValue().getX());
                    p.setY(entry.getValue().getY());
                    p.setWidth(entry.getValue().getWidth());
                    p.setHeight(entry.getValue().getHeight());
                    p.setGamePanel(this);
                    p.initBounds(p.getX(), p.getY(), p.getWidth(), p.getHeight());
                    p.loadImg();
                }
            }
        }
        //Applied white dimensions
        for (ChessPiece p:whitePieces){
            for (Map.Entry<String, Square> entry : squares.entrySet()) {
                if(entry.getKey().equals(p.getPos())){
                    p.setX(entry.getValue().getX());
                    p.setY(entry.getValue().getY());
                    p.setWidth(entry.getValue().getWidth());
                    p.setHeight(entry.getValue().getHeight());
                    p.setGamePanel(this);
                    p.initBounds(p.getX(),p.getY(),p.getWidth(),p.getHeight());
                    p.loadImg();
                }
            }
        }
    }
    private void initMouseHandler(){
        mH = new MouseHandler(this);
        addMouseListener(mH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        //DrawChessBoard
        drawChessBoard(g);
        //Draw all blackPieces onto board
        for(ChessPiece p:blackPieces){
            if(p != null){
                p.draw(g);
            }

        }
        //Draw all whitePieces onto board
        for(ChessPiece p:whitePieces){
            p.draw(g);
        }
    }

    public void update() {
        showSelectedPiece();
        applyFilledSquares();
    }


    private void applyFilledSquares(){
        for (Map.Entry<String, Square> entry : squares.entrySet()) {
            entry.getValue().setFilledAndChessPieceColor(whitePieces,blackPieces);
        }
    }


    private void showSelectedPiece(){
        for (ChessPiece piece : whitePieces) {
            if (piece.selected) {
                for (Map.Entry<String, Square> entry : squares.entrySet()) {
                    if (entry.getKey().equals(piece.getPos())) {
                        entry.getValue().setColor(Color.YELLOW);
                    }
                }
            }

        }
    }
public void resetSquaresDefaultColor(){
    for (ChessPiece piece : whitePieces) {
        for (Map.Entry<String, Square> entry : squares.entrySet()) {
            if (entry.getKey().equals(piece.getPos())) {
                entry.getValue().setColor(entry.getValue().getDefaultColor());
            }
        }
    }
}

private void drawChessBoard(Graphics g){
    for (Map.Entry<String, Square> entry : squares.entrySet()) {
        entry.getValue().draw(g);
    }
}
@Override
public void actionPerformed(ActionEvent e) {
    update();
    repaint();
}

//Getter / Setter Methods
public int getDefaultTileSize(){return DEFAULT_TS;}
public int getTileSize(){return TS;}
public ArrayList<ChessPiece> getWhitePieces() {return whitePieces;}
    public ArrayList<ChessPiece> getBlackPieces() {return blackPieces;}

    public void setConfirmedPos(String chessPiecePos) {
    confirmedPos = chessPiecePos;
}

public String getPriorChessPiecePos() {
    return priorChessPiecePos;
}

public void setPriorChessPiecePos(String priorChessPiecePos) {
    this.priorChessPiecePos = priorChessPiecePos;
}
public String getConfirmedPiecePos(){return confirmedPos;}
public HashMap<String,Square> getSquares(){return squares;}


    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for (ChessPiece whitePiece : whitePieces) {
            if (whitePiece.selected) {
                for (Map.Entry<String, Square> entry : squares.entrySet()) {
                    switch (whitePiece.type) {
                        case PAWN:
                            if(entry.getValue().getBounds().contains(x,y)){
                                //White Pawn attack UpLeft blackPiece, removing it from board
                                if(entry.getKey().equals(whitePiece.getAttackPositions()[0])
                                        && entry.getValue().getChessPieceColor() == Color.BLACK) {
                                    for (int i = 0; i < blackPieces.size(); i++) {
                                        if (blackPieces.get(i).getPos().equals(entry.getKey())) {
                                            System.out.println("blackPiecePos: " + blackPieces.get(i).getPos());
                                            blackPieces.remove(i);
                                            whitePiece.setPos(whitePiece.getAttackPositions()[0]);
                                            whitePiece.setNextPos(whitePiece.getPos());
                                            whitePiece.setDirections(whitePiece.getPos());
                                            whitePiece.setAttackPositions(whitePiece.getPos());
                                            whitePiece.setX(entry.getValue().getX());
                                            whitePiece.setY(entry.getValue().getY());
                                            whitePiece.getBounds().x = whitePiece.getX();
                                            whitePiece.getBounds().y = whitePiece.getY();
                                            whitePiece.selected = false;
                                            //System.out.println("blackPiecePos: " + blackPieces.get(i).getPos());
                                        }
                                    }
                                }
                                //White Pawn attack UpRight blackPiece, removing it from board
                                if(entry.getKey().equals(whitePiece.getAttackPositions()[1])
                                        && entry.getValue().getChessPieceColor() == Color.BLACK) {
                                    for (int i = 0; i < blackPieces.size(); i++) {
                                        if (blackPieces.get(i).getPos().equals(entry.getKey())) {
                                            System.out.println("blackPiecePos: " + blackPieces.get(i).getPos());
                                            blackPieces.remove(i);
                                            whitePiece.setPos(whitePiece.getAttackPositions()[1]);
                                            whitePiece.setNextPos(whitePiece.getPos());
                                            whitePiece.setDirections(whitePiece.getPos());
                                            whitePiece.setAttackPositions(whitePiece.getPos());
                                            whitePiece.setX(entry.getValue().getX());
                                            whitePiece.setY(entry.getValue().getY());
                                            whitePiece.getBounds().x = whitePiece.getX();
                                            whitePiece.getBounds().y = whitePiece.getY();
                                            whitePiece.selected = false;
                                            //System.out.println("blackPiecePos: " + blackPieces.get(i).getPos());
                                        }
                                    }
                                }

                                //Initial Pos either forward move once or twice
                                if(whitePiece.isInitPos()){
                                    if(entry.getKey().equals(whitePiece.getNextPos())
                                    && entry.getValue().getChessPieceColor() != Color.BLACK){
                                        whitePiece.setPos(whitePiece.getNextPos());
                                        whitePiece.setNextPos(whitePiece.getPos());
                                        whitePiece.setDirections(whitePiece.getPos());
                                        whitePiece.setAttackPositions(whitePiece.getPos());
                                        whitePiece.setX(entry.getValue().getX());
                                        whitePiece.setY(entry.getValue().getY());
                                        whitePiece.getBounds().x = whitePiece.getX();
                                        whitePiece.getBounds().y = whitePiece.getY();
                                        whitePiece.selected = false;
                                    }
                                    if(entry.getKey().equals(whitePiece.getNextPos2()) && entry.getValue().getChessPieceColor() != Color.BLACK){
                                        String initialNextPos = String.valueOf(entry.getKey().charAt(0)) +
                                                String.valueOf(Character.getNumericValue(entry.getKey().charAt(1)) - 1);
                                        System.out.println("InitialNextPos: " + initialNextPos);
                                        //Checks the to see if blackPiece at initialNextPos of whitePawn, if so do not move up two
                                        if(getSquares().get(initialNextPos).getChessPieceColor() != Color.BLACK) {
                                            whitePiece.setPos(whitePiece.getNextPos2());
                                            whitePiece.setNextPos(whitePiece.getPos());
                                            whitePiece.setDirections(whitePiece.getPos());
                                            whitePiece.setAttackPositions(whitePiece.getPos());
                                            whitePiece.setX(entry.getValue().getX());
                                            whitePiece.setY(entry.getValue().getY());
                                            whitePiece.getBounds().x = whitePiece.getX();
                                            whitePiece.getBounds().y = whitePiece.getY();
                                            whitePiece.selected = false;
                                        }
                                    }
                                }


                                //Moving forward once
                                if(entry.getKey().equals(whitePiece.getNextPos())
                                        && entry.getValue().getChessPieceColor() != Color.BLACK){
                                    whitePiece.setPos(whitePiece.getNextPos());
                                    whitePiece.setNextPos(whitePiece.getPos());
                                    whitePiece.setDirections(whitePiece.getPos());
                                    whitePiece.setAttackPositions(whitePiece.getPos());
                                    whitePiece.setX(entry.getValue().getX());
                                    whitePiece.setY(entry.getValue().getY());
                                    whitePiece.getBounds().x = whitePiece.getX();
                                    whitePiece.getBounds().y = whitePiece.getY();
                                    whitePiece.selected = false;
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
            else{resetSquaresDefaultColor();}
        }
    }


    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        for (ChessPiece whitePiece : getWhitePieces()) {
            if(whitePiece.getBounds().contains(x,y)){
                whitePiece.selected=true;
                System.out.println("current selected whitePiece: " + whitePiece.getPos() + " " +whitePiece.selected);
                System.out.println("currentPos: " + whitePiece.getPos());
                System.out.println("nextPos: " + whitePiece.getNextPos());
                System.out.println("nextPos2: " + whitePiece.getNextPos2());
                System.out.println("upLeftAttackPos: " + whitePiece.getAttackPositions()[0]);
                System.out.println("upRightAttackPos: " + whitePiece.getAttackPositions()[1]);
            }
            else{
                whitePiece.selected = false;
                System.out.println("non selected whitePiece: " + whitePiece.getPos()+ " " +whitePiece.selected);
            }
        }

        for (ChessPiece piece : getWhitePieces()) {
            if(!piece.selected){
                resetSquaresDefaultColor();
            }

        }
    }
}

