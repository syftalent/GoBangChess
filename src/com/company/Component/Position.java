package com.company.Component;

import com.company.enums.Color;

/**
 * Created by yifeishen on 4/24/16.
 */
public class Position {
    public int x, y;
    public Color color;

    public Position(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public boolean hasLeft(){
        return x > 0;
    }

    public boolean hasRight(){
        return x < ChessBoard.CELL_NUM-1;
    }

    public boolean hasUp(){
        return y < ChessBoard.CELL_NUM-1;
    }

    public boolean hasDown(){
        return y > 0;
    }

    public boolean hasLeftUp(){
        return (x > 0 && y < ChessBoard.CELL_NUM-1);
    }

    public boolean hasLeftDown(){
        return (x > 0 && y > 0);
    }

    public boolean hasRightUp(){
        return (x < ChessBoard.CELL_NUM - 1 && y < ChessBoard.CELL_NUM -1);
    }

    public boolean hasRightDown(){
        return (x < ChessBoard.CELL_NUM -1 && y > 0);
    }

    public boolean moveLeft(){
        if(!hasLeft()){
            return false;
        }else{
            x--;
            updateColor();
            return true;
        }
    }

    public boolean moveRight(){
        if(!hasRight()){
            return false;
        }else{
            x++;
            updateColor();
            return true;
        }
    }

    public boolean moveUp(){
        if(!hasUp()){
            return false;
        }else{
            y++;
            updateColor();
            return true;
        }
    }

    public boolean moveDown(){
        if(!hasDown()){
            return false;
        }else{
            y--;
            updateColor();
            return true;
        }
    }

    public boolean moveLeftUp(){
        if(!hasLeftUp()){
            return false;
        }else{
            moveUp();
            moveLeft();
            updateColor();
            return true;
        }
    }

    public boolean moveRightDown(){
        if(!hasRightDown()){
            return false;
        }else{
            moveRight();
            moveDown();
            updateColor();
            return true;
        }
    }

    public boolean moveRightUp(){
        if(!hasRightUp()){
            return false;
        }else{
            moveRight();
            moveUp();
            updateColor();
            return true;
        }
    }

    public boolean moveLeftDown(){
        if(!hasLeftDown()){
            return false;
        }else{
            moveLeft();
            moveDown();
            updateColor();
            return true;
        }
    }

    private void updateColor(){
        color = Color.getColor(ChessBoard.getInstance().getBoardMatrix_horizontal()[x][y]);
    }
}
