package com.company;

import Ynu.Sei.cpLibrary.BASIC.cpDraw;
import com.company.Component.ChessBoard;
import com.company.callbacks.OnMoveMadeCallBack;
import com.company.enums.Color;
import com.company.player.AIPlayer;
import com.company.player.HumanPlayer;
import com.company.player.Player;

/**
 * Created by yifeishen on 4/19/16.
 */
public class Game {
    ChessBoard mChessBoard;
    Player mPlayer1, mPlayer2;

    public static void main(String[] args) {
        Game game = new Game();
        game.initializeGame();
    }

    private void initializeGame(){
        cpDraw.setX(0, 21, 0.1);
        cpDraw.setY(0, 21, 0.1);
        while(true){
            if(cpDraw.mouseClicked()){
                cpDraw.setmouseClickedFalse();
            }
            if(cpDraw.mouseX()>=4.4 && cpDraw.mouseX()<=15.8 && cpDraw.mouseY()>=9.4 && cpDraw.mouseY()<=12.2){
                cpDraw.Picture(9.99, 9.99, "images/cover_1.png");
                if (cpDraw.mouseClicked()) {
                    cpDraw.setmouseClickedFalse();
                    mPlayer1 = new HumanPlayer(Color.BLACK);
                    mPlayer2 = new HumanPlayer(Color.WHITE);
                    startNewGame();
                    break;
                }
            } else if(cpDraw.mouseX()>=4.4 && cpDraw.mouseX()<=15.8 && cpDraw.mouseY()>=5.1 && cpDraw.mouseY()<=7.9){
                cpDraw.Picture(9.99, 9.99, "images/cover_2.png");
                if (cpDraw.mouseClicked()){
                    cpDraw.setmouseClickedFalse();
                    mPlayer1 = new HumanPlayer(Color.BLACK);
                    mPlayer2 = new AIPlayer(Color.WHITE);
                    startNewGame();
                }
            } else{
                cpDraw.Picture(9.99, 9.99, "images/cover.png");
            }
        }
    }

    public void startNewGame(){
        mChessBoard = ChessBoard.getInstance();
        mChessBoard.resetBoard();
        cpDraw.Picture(9.99, 9.99, "images/board.png");

        makeMove(mPlayer1);
    }

    public void makeMove(final Player player){
        player.makeMove(new OnMoveMadeCallBack() {
            @Override
            public void makeMoveComplete(boolean hasWon) {
                if(hasWon){
                    endGame(player);
                }else{
                    Player nextPlayer = (player == mPlayer1)? mPlayer2:mPlayer1;
                    makeMove(nextPlayer);
                }
            }
        });
    }

    public void endGame(Player player){
        cpDraw.Picture(10.2, 11.5, "images/win.png");
        while (true){
            if(cpDraw.mouseX()>=4 && cpDraw.mouseX()<=9 && cpDraw.mouseY()>=8 && cpDraw.mouseY()<=10){
                cpDraw.Picture(6.5, 9, "images/menu_hover.png");
                if (cpDraw.mouseClicked()){
                    cpDraw.setmouseClickedFalse();
                    initializeGame();
                    break;
                }
            }
            else if(cpDraw.mouseX()>=11 && cpDraw.mouseX()<=16 && cpDraw.mouseY()>=8 && cpDraw.mouseY()<=10){
                cpDraw.Picture(13.5, 9, "images/again_hover.png");
                if (cpDraw.mouseClicked()){
                    cpDraw.setmouseClickedFalse();
                    startNewGame();
                    break;
                }
            }
            else{
                cpDraw.Picture(6.5, 9, "images/menu.png");
                cpDraw.Picture(13.5, 9, "images/again.png");
            }
        }
    }
}
