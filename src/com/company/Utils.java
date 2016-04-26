package com.company;

import com.company.Component.ChessBoard;
import com.company.Component.Position;

/**
 * Created by yifeishen on 4/24/16.
 */
public class Utils {
    public static boolean isValidPosition(int x, int y){
        if(x < 0 || x >= ChessBoard.CELL_NUM || y < 0 || y >= ChessBoard.CELL_NUM){
            return false;
        }else{
            return true;
        }
    }

//    public static Position moveToLeftMostWithSameColor(Position posi){
//        int[][] board = ChessBoard.getInstance().getBoardMatrix_horizontal();
//        if(posi.hasLeft() && board[posi.x -1][posi.y] == posi.color.getVal()){
//            posi.moveLeft();
//            return moveToLeftMostWithSameColor(posi);
//        }else{
//            return posi;
//        }
//    }
//
//    public static Position moveToUpMostWithSameColor(Position posi){
//        int[][] board = ChessBoard.getInstance().getBoardMatrix_horizontal();
//        if(posi.hasUp() && board[posi.x][posi.y+1] == posi.color.getVal()){
//            posi.moveUp();
//            return moveToUpMostWithSameColor(posi);
//        }else{
//            return posi;
//        }
//    }
//
//    public static Position moveToLeftUpMostWithSameColor(Position posi){
//        int[][] board = ChessBoard.getInstance().getBoardMatrix_horizontal();
//        if(posi.hasLeftUp() && board[posi.x-1][posi.y+1] == posi.color.getVal()){
//            posi.moveLeftUp();
//            return moveToLeftUpMostWithSameColor(posi);
//        }else{
//            return posi;
//        }
//    }
//
//    public static Position moveToRightUpMostWithSameColor(Position posi){
//        int[][] board = ChessBoard.getInstance().getBoardMatrix_horizontal();
//        if(posi.hasRightUp() && board[posi.x+1][posi.y+1] == posi.color.getVal()){
//            posi.moveRightUp();
//            return moveToRightUpMostWithSameColor(posi);
//        }else{
//            return posi;
//        }
//    }

}
