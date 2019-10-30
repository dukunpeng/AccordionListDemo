package com.mark.study.expendlistdemo.newdemo;

import android.content.Context;
import android.view.View;

/**
 * @author Mark
 * @Date on 2019/10/30
 **/
public interface HeaderProvider {

    View getHeader(Context context, int position);
}
