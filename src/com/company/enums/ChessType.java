package com.company.enums;

/**
 * Created by yifeishen on 4/24/16.
 */
public enum ChessType {
    FIVE(10000),
    FOUR_0_BLOCK(9000),
    TWO_FOUR_1_BLOCK(8000),
    FOUR_1_BLOCK_THREE_0_BLOCK(8000),
    TWO_THREE_0_BLOCK(300),
    FOUR_1_BLOCK(300),
    THREE_0_BLOCK(60),
    TWO_0_BLOCK(50),
    THREE_1_BLOCK(40),
    TWO_1_BLOCK(20),
    SINGLE_0_BLOCK(5),
    SINGLE_1_BLOCK(1),
    FOUR_2_BLOCK(0),
    THREE_2_BLOCK(0),
    TWO_2_BLOCK(0);


    private int chess_style_val;

    ChessType(int val){
        chess_style_val = val;
    }

    public int getVal(){
        return chess_style_val;
    }
}
