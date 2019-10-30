package com.mark.study.expendlistdemo.newdemo;

import android.view.View;

/**
 * @author Mark
 * @Date on 2019/10/29
 **/
public interface AccordionItemClick {

    /**
     * 琴键的点击
     *
     * @param view
     * @param position
     */
    abstract public void onItemCLick(View view, int position);


}
