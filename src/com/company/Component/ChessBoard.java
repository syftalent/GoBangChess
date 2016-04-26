package com.company.Component;


import Ynu.Sei.cpLibrary.BASIC.cpDraw;
import com.company.AI_Judge.AI_Evaluation;
import com.company.enums.Color;

import java.util.*;

public class ChessBoard {
    public final static int CELL_NUM = 15;
    public final static double GRID_SPAN=10/7.0;

    private static ChessBoard mInstance;

    private int[][] boardMatrix_horizontal;
    private int[][] boardMatrix_vertical;
    private List<int[]> boardMatrix_leftDiagonal;
    private List<int[]> boardMatrix_rightDiagonal;

    public int minX, maxX, minY, maxY;

    public ChessBoard(){
        resetBoard();
    }

    public static ChessBoard getInstance(){
        if(mInstance == null){
            mInstance =  new ChessBoard();
        }
        return mInstance;
    }

    public void resetBoard(){
        boardMatrix_horizontal = new int[CELL_NUM][CELL_NUM];
        boardMatrix_vertical = new int[CELL_NUM][CELL_NUM];
        boardMatrix_leftDiagonal = new ArrayList<int[]>();
        boardMatrix_rightDiagonal = new ArrayList<int[]>();

        for(int i = 1; i<= CELL_NUM; i++){
            boardMatrix_leftDiagonal.add(new int[i]);
            boardMatrix_rightDiagonal.add(new int[i]);
        }
        for(int i = CELL_NUM-1; i>=1; i--){
            boardMatrix_leftDiagonal.add(new int[i]);
            boardMatrix_rightDiagonal.add(new int[i]);
        }
        minX = Integer.MAX_VALUE; maxX = Integer.MIN_VALUE;
        minY = Integer.MAX_VALUE; maxY = Integer.MIN_VALUE;
    }

    public boolean isValidMove(int x, int y){
        if(x < 0 || x >= CELL_NUM || y < 0 || y >= CELL_NUM) {
            return false;
        }
        return boardMatrix_vertical[x][y] == 0;
    }

    //must call after isValidMove();
    public boolean makeMove(int x, int y, Color color){
//        System.out.println("vertical " + getVerticalArrayIndex(x, y));
//        System.out.println("horizontal " + getHorizontalArrayIndex(x, y));
//        System.out.println("left Diagonal " + getLeftDiagonalArrayIndex(x, y));
//        System.out.println("right Diagonal " + getRightDiagonalArrayIndex(x, y));

        boardMatrix_vertical[x][y] = color.getVal();
        boardMatrix_horizontal[y][x] = color.getVal();
        int[] leftDiagonal = getLeftDiagonalArray(x, y);
        int[] rightDiagonal = getRightDiagonalArray(x, y);

        leftDiagonal[getLeftDiagonalArrayIndex(x, y)] = color.getVal();
        rightDiagonal[getRightDiagonalArrayIndex(x, y)] = color.getVal();

        minX = Math.max(0, Math.min(x, minX));
        maxX = Math.min(CELL_NUM - 1, Math.max(x, maxX));
        minY = Math.max(0, Math.min(y, minY));
        maxY = Math.min(CELL_NUM - 1, Math.max(y, maxY));
        drawChessPiese(x, y, color);
        return checkWinning(x, y, color);
    }

    public void cancelMove(int x, int y, Color color){
        if(boardMatrix_vertical[x][y] != color.getVal()){
            return;
        }
        boardMatrix_vertical[x][y] = 0;
        boardMatrix_horizontal[y][x] = 0;
        int[] leftDiagonal = getLeftDiagonalArray(x, y);
        int[] rightDiagonal = getRightDiagonalArray(x, y);

        leftDiagonal[getLeftDiagonalArrayIndex(x, y)] = 0;
        rightDiagonal[getRightDiagonalArrayIndex(x, y)] = 0;
    }

    public void drawChessPiese(double x, double y, Color color){
        x = x * GRID_SPAN;
        y = y * GRID_SPAN;
        if(color == Color.BLACK){
            cpDraw.Picture(x, y, "images/black.png");
        }else if(color == Color.WHITE){
            cpDraw.Picture(x, y, "images/white.png");
        }
    }

    private boolean checkWinning(int x, int y, Color color){
        int bufX = x, bufY = y, count = 0;
        //check vertical
        while(bufY > 0 && boardMatrix_vertical[x][bufY-1] == color.getVal()){
            bufY --;
        }
        while(bufY < CELL_NUM && boardMatrix_vertical[x][bufY] == color.getVal()){
            bufY ++; count++;
        }
        if(count >= 5)  return true;
        else    count = 0;

        //check horizon
        while(bufX > 0 && boardMatrix_vertical[bufX-1][y] == color.getVal()){
            bufX --;
        }
        while(bufX < CELL_NUM && boardMatrix_vertical[bufX][y] == color.getVal()){
            bufX ++; count++;
        }
        if(count >= 5)  return true;
        else    count = 0;

        //right-up corner diagonal
        bufX = x; bufY = y;
        while(bufX > 0 && bufY > 0 && boardMatrix_vertical[bufX-1][bufY-1] == color.getVal()){
            bufX --; bufY--;
        }
        while(bufX < CELL_NUM && bufY < CELL_NUM && boardMatrix_vertical[bufX][bufY] == color.getVal()){
            bufX++; bufY++; count++;
        }
        if(count >= 5)  return true;
        else count = 0;

        //left-up corner diagnoal
        bufX = x; bufY = y;
        while(bufX < CELL_NUM-1 && bufY > 0 && boardMatrix_vertical[bufX + 1][bufY -1] == color.getVal()){
            bufX ++; bufY--;
        }
        while(bufX >=0 && bufY < CELL_NUM && boardMatrix_vertical[bufX][bufY] == color.getVal()){
            bufX --; bufY ++; count++;
        }
        if(count>= 5)   return true;
        else    return false;
    }

    //Get Matrix Functions, four directions
    public int[][] getBoardMatrix_horizontal(){
        return boardMatrix_horizontal;
    }

    public int[][] getBoardMatrix_vertical(){
        return boardMatrix_vertical;
    }

    public List<int[]> getBoardMatrix_leftDiagonal(){
        return boardMatrix_leftDiagonal;
    }

    public List<int[]> getBoardMatrix_rightDiagonal(){
        return boardMatrix_rightDiagonal;
    }


    //Get Array in four directions
    public int[] getVerticalArray(int x, int y){
        return boardMatrix_vertical[x];
    }

    public int[] getHorizontalArray(int x, int y){
        return boardMatrix_horizontal[y];
    }

    public int[] getLeftDiagonalArray(int x, int y){
        return boardMatrix_leftDiagonal.get(x + y);
    }

    public int[] getRightDiagonalArray(int x, int y){
        return boardMatrix_rightDiagonal.get(x-y+CELL_NUM-1);
    }

    public int getVerticalArrayIndex(int x, int y){
        return y;
    }

    public int getHorizontalArrayIndex(int x, int y){
        return x;
    }

    public int getLeftDiagonalArrayIndex(int x, int y){
        if(x + y < CELL_NUM){
            return x;
        }else{
            return -(y-CELL_NUM+1);
        }
    }

    public int getRightDiagonalArrayIndex(int x, int y){
        if(y-x >= 0){
            return x;
        }else{
            return y;
        }
    }
}
