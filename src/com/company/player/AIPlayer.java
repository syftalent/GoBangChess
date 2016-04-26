package com.company.player;

import com.company.AI_Judge.AI_Evaluation;
import com.company.Component.ChessBoard;
import com.company.Component.Position;
import com.company.callbacks.OnMoveMadeCallBack;
import com.company.enums.Color;

/**
 * Created by yifeishen on 4/24/16.
 */
public class AIPlayer implements Player{
    private Color color;
    private ChessBoard mChessBoard;

    public AIPlayer(Color color){
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
        Position nextMove = AI_Evaluation.getMovePosition(color);
        onMoveMadeCallBack.makeMoveComplete(mChessBoard.makeMove(nextMove.x, nextMove.y, color));
    }
}
