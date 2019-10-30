package com.mark.study.expendlistdemo.newdemo;

import android.content.Context;

/**
 * @author Mark
 * @Date on 2019/10/29
 **/
public interface IAccordion {

    /**
     * 更新琴键
     * @param maxPosition 最后一个琴键的位置
     */
    void updateButtons(int maxPosition);

    void init(Context context);

    void hideLastChild();

    /**
     * 获取指定位置到琴顶部的高度
     * @param position
     * @return
     */
    int getBottomOnPosition(int position);

    /**
     * 获取指定位置琴键的高度
     * @param position
     * @return
     */
    int getHeigthOnPosition(int position);

    /**
     * 获取不带最后一个琴键的高度
     * @return
     */
    int getHeigthWithoutLastChild();


}
