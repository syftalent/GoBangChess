package com.company.AI_Judge;

import com.company.Component.ChessBoard;
import com.company.Component.Position;
import com.company.enums.ChessType;
import com.company.enums.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yifeishen on 4/24/16.
 */
public class AI_Evaluation {
    private final static int SEARCH_DEPTH = 4;
    private final static int BOTTOM_LEVEL = 1;

    public static Position getMovePosition(Color self){
        AI_MindChessBoard boardInMind = new AI_MindChessBoard(ChessBoard.getInstance());
        CandidateMove candidateMove = minMaxHelper(boardInMind, self, SEARCH_DEPTH);
        Random random = new Random();
        return candidateMove.candidatePosi.get(random.nextInt(candidateMove.candidatePosi.size()));
    }

    private static CandidateMove minMaxHelper(AI_MindChessBoard boardInMind, Color self, int level){
        Color opponent = (self == Color.BLACK) ? Color.WHITE: Color.BLACK;
        CandidateMove candidateMove = new CandidateMove(Integer.MIN_VALUE);
        for(int i=Math.max(0, boardInMind.minX-2); i<=Math.min(ChessBoard.CELL_NUM-1, boardInMind.maxX+2); i++){
            for(int j=Math.max(0, boardInMind.minY-2); j<Math.min(ChessBoard.CELL_NUM-1, boardInMind.maxY+2); j++){
                if(!boardInMind.isValidMove(i, j)){
                    continue;
                }
                int selfScore = getEvaluationScore(boardInMind, i, j, self);
                boardInMind.makeMove(i,j, self);
                int opponentScore = (level == BOTTOM_LEVEL) ? 0 : minMaxHelper(boardInMind, opponent, level - 1).score;
                int finalScore = selfScore - opponentScore;
                if(finalScore == candidateMove.score){
                    candidateMove.candidatePosi.add(new Position(i, j, self));
                }else if(finalScore > candidateMove.score){
                    candidateMove.score = finalScore;
                    candidateMove.candidatePosi.clear();
                    candidateMove.candidatePosi.add(new Position(i, j, self));
                }
                boardInMind.cancelMove(i,j, self);
            }
        }
        return candidateMove;
    }

    private static class CandidateMove{
        List<Position> candidatePosi;
        int score;

        CandidateMove(int maxScore){
            candidatePosi = new ArrayList<Position>();
            this.score = maxScore;
        }
    }



    public static int getEvaluationScore(AI_MindChessBoard boardInMind, int x, int y, Color self){
        List<ChessType> chessTypes = new ArrayList<ChessType>();
        chessTypes.add(chessTypeHelper(boardInMind.getHorizontalArray(x, y), boardInMind.getHorizontalArrayIndex(x, y), self));
        chessTypes.add(chessTypeHelper(boardInMind.getVerticalArray(x, y), boardInMind.getVerticalArrayIndex(x, y), self));
        chessTypes.add(chessTypeHelper(boardInMind.getLeftDiagonalArray(x, y), boardInMind.getLeftDiagonalArrayIndex(x, y), self));
        chessTypes.add(chessTypeHelper(boardInMind.getRightDiagonalArray(x, y), boardInMind.getRightDiagonalArrayIndex(x, y), self));

        while(chessTypes.contains(ChessType.FOUR_1_BLOCK) && chessTypes.contains(ChessType.THREE_0_BLOCK)){
            chessTypes.add(ChessType.FOUR_1_BLOCK_THREE_0_BLOCK);
            chessTypes.remove(ChessType.FOUR_1_BLOCK);
            chessTypes.remove(ChessType.THREE_0_BLOCK);
        }

        if(chessTypes.contains(ChessType.FOUR_1_BLOCK)){
            chessTypes.remove(ChessType.FOUR_1_BLOCK);
            if(chessTypes.contains(ChessType.FOUR_1_BLOCK)){
                chessTypes.remove(ChessType.FOUR_1_BLOCK);
                chessTypes.add(ChessType.TWO_FOUR_1_BLOCK);
            }else{
                chessTypes.add(ChessType.FOUR_1_BLOCK);
            }
        }

        if(chessTypes.contains(ChessType.THREE_0_BLOCK)){
            chessTypes.remove(ChessType.THREE_0_BLOCK);
            if(chessTypes.contains(ChessType.THREE_0_BLOCK)){
                chessTypes.remove(ChessType.THREE_0_BLOCK);
                chessTypes.add(ChessType.TWO_THREE_0_BLOCK);
            }else{
                chessTypes.add(ChessType.THREE_0_BLOCK);
            }
        }

        int score = 0;
        for(ChessType chessType : chessTypes){
            score += chessType.getVal();
        }
        return score;
    }

    private static ChessType chessTypeHelper(int[] line, int index, Color self){
        int count = 1;
        boolean isLeftBlocked = true;
        boolean isRightBlocked = true;

        //search left
        for(int i = index-1; i>= 0; i--){
            if(line[i] == self.getVal()){
                count++;
            }else if(line[i] == Color.NULL.getVal()){
                isLeftBlocked = false;
                break;
            }else{
                break;
            }
        }

        //search right
        for(int i = index+1; i< line.length; i++){
            if(line[i] == self.getVal()){
                count++;
            }else if(line[i] == Color.NULL.getVal()){
                isRightBlocked = false;
                break;
            }else{
                break;
            }
        }

        switch (count){
            case 1:
                if(isLeftBlocked || isRightBlocked){
                    return ChessType.SINGLE_1_BLOCK;
                }else{
                    return ChessType.SINGLE_0_BLOCK;
                }
            case 2:
                if(isLeftBlocked && isRightBlocked){
                    return ChessType.TWO_2_BLOCK;
                }else if(isLeftBlocked || isRightBlocked){
                    return ChessType.TWO_1_BLOCK;
                }else{
                    return ChessType.TWO_0_BLOCK;
                }
            case 3:
                if(isLeftBlocked && isRightBlocked){
                    return ChessType.THREE_2_BLOCK;
                }else if(isLeftBlocked || isRightBlocked){
                    return ChessType.THREE_1_BLOCK;
                }else{
                    return ChessType.THREE_0_BLOCK;
                }
            case 4:
                if(isLeftBlocked && isRightBlocked){
                    return ChessType.FOUR_2_BLOCK;
                }else if(isLeftBlocked || isRightBlocked){
                    return ChessType.FOUR_1_BLOCK;
                }else{
                    return ChessType.FOUR_0_BLOCK;
                }
            default:
                if(count >= 5){
                    return ChessType.FIVE;
                }
                break;

        }
        return ChessType.SINGLE_0_BLOCK;
    }

}
