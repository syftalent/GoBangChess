package com.company.player;

import Ynu.Sei.cpLibrary.BASIC.cpDraw;
import com.company.AI_Judge.AI_Evaluation;
import com.company.Component.ChessBoard;
import com.company.callbacks.OnMoveMadeCallBack;
import com.company.enums.Color;

/**
 * Created by yifeishen on 4/19/16.
 */
public class HumanPlayer implements Player{
    private Color color;
    private ChessBoard mChessBoard;

    public HumanPlayer(Color color){
        this.color = color;
        mChessBoard = ChessBoard.getInstance();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName() {

    }

    @Override
    public void makeMove(OnMoveMadeCallBack onMoveMadeCallBack) {
        double exact_x, exact_y;
        while(true) {
            if (cpDraw.mouseX() > -0.7 && cpDraw.mouseX() < 20.7 && cpDraw.mouseY() > -0.7 && cpDraw.mouseY() < 20.7 && cpDraw.mouseClicked()) {
                cpDraw.setmouseClickedFalse();
                int x = (int) ((cpDraw.mouseX() + ChessBoard.GRID_SPAN / 2) / ChessBoard.GRID_SPAN);
                int y = (int) ((cpDraw.mouseY() + ChessBoard.GRID_SPAN / 2) / ChessBoard.GRID_SPAN);
                if (mChessBoard.isValidMove(x, y)) {
                    onMoveMadeCallBack.makeMoveComplete(mChessBoard.makeMove(x, y, color));
                }
            }
        }
    }
}
