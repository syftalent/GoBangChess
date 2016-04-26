package com.company.player;

import com.company.callbacks.OnMoveMadeCallBack;

/**
 * Created by yifeishen on 4/19/16.
 */
public interface Player {

    public void makeMove(OnMoveMadeCallBack onMoveMadeCallBack);

    public String getName();

    public void setName();
}
