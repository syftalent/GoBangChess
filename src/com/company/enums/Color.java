package com.company.enums;

/**
 * Created by yifeishen on 4/21/16.
 */
public enum Color {
    NULL(0),
    BLACK (1),
    WHITE (2);

    int colorVal;

    Color(int val){
        this.colorVal = val;
    }

    public int getVal(){
        return colorVal;
    }

    public static Color getColor(int color){
        switch (color){
            case 1:
                return BLACK;
            case 2:
                return WHITE;
            case 0:
                return NULL;
            default:
                return null;
        }
    }
}
