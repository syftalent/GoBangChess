package com.company.AI_Judge;

import com.company.Component.ChessBoard;
import com.company.enums.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yifeishen on 4/26/16.
 */
public class AI_MindChessBoard {
    private int cellNum;

    private int[][] boardMatrix_horizontal;
    private int[][] boardMatrix_vertical;
    private List<int[]> boardMatrix_leftDiagonal;
    private List<int[]> boardMatrix_rightDiagonal;

    public int minX, maxX, minY, maxY;

    AI_MindChessBoard(ChessBoard chessBoard){
        this.cellNum = ChessBoard.CELL_NUM;
        this.boardMatrix_horizontal = chessBoard.getBoardMatrix_horizontal().clone();
        this.boardMatrix_vertical = chessBoard.getBoardMatrix_vertical().clone();
        this.boardMatrix_leftDiagonal = new ArrayList<>(chessBoard.getBoardMatrix_leftDiagonal());
        this.boardMatrix_rightDiagonal = new ArrayList<>(chessBoard.getBoardMatrix_rightDiagonal());
        this.minX = chessBoard.minX;
        this.maxX = chessBoard.maxX;
        this.minY = chessBoard.minY;
        this.maxY = chessBoard.maxY;
    }

    public void makeMove(int x, int y, Color color){
        boardMatrix_vertical[x][y] = color.getVal();
        boardMatrix_horizontal[y][x] = color.getVal();
        int[] leftDiagonal = getLeftDiagonalArray(x, y);
        int[] rightDiagonal = getRightDiagonalArray(x, y);

        leftDiagonal[getLeftDiagonalArrayIndex(x, y)] = color.getVal();
        rightDiagonal[getRightDiagonalArrayIndex(x, y)] = color.getVal();

        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
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

        if(minX == x){
            while(ifNoChessOnTheLine(boardMatrix_vertical[minX])){
                minX ++;
            }
        }
        if(maxX == x){
            while(ifNoChessOnTheLine(boardMatrix_vertical[maxX])){
                maxX--;
            }
        }
        if(minY == y){
            while(ifNoChessOnTheLine(boardMatrix_horizontal[minY])){
                minY++;
            }
        }
        if(maxY == y){
            while(ifNoChessOnTheLine(boardMatrix_horizontal[maxY])){
                maxY--;
            }
        }
    }

    private boolean ifNoChessOnTheLine(int[] line){
        for(int i: line){
            if(i != Color.NULL.getVal()){
                return false;
            }
        }
        return true;
    }

    public boolean isValidMove(int x, int y){
        if(x < 0 || x >= cellNum || y < 0 || y >= cellNum) {
            return false;
        }
        return boardMatrix_vertical[x][y] == 0;
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
        return boardMatrix_rightDiagonal.get(x-y+cellNum-1);
    }

    public int getVerticalArrayIndex(int x, int y){
        return y;
    }

    public int getHorizontalArrayIndex(int x, int y){
        return x;
    }

    public int getLeftDiagonalArrayIndex(int x, int y){
        if(x + y < cellNum){
            return x;
        }else{
            return -(y-cellNum+1);
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
