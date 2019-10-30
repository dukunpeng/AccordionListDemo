package com.mark.study.expendlistdemo.newdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mark
 * @Date on 2019/10/29
 **/
public class AccordionLayout extends LinearLayout implements IAccordion {

    private final String TAG = "ButtonsLayout";

    private Context mContext;

    private HeaderProvider headerProvider;

    private AccordionItemClick accordionItemClick;

    public int oldBottom;

    /***
     * 记录每个header的高度
     */
    public Map<Integer, Integer> heightMap = new HashMap<>();

    public AccordionLayout(Context context) {
        super(context);
        init(context);
    }

    public AccordionLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AccordionLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AccordionLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    public void updateButtons(int maxPosition) {
        if (headerProvider == null) {
            return;
        }
        if (maxPosition < 0) {
            return;
        }
        final int childCount = getChildCount();
        int d = maxPosition - childCount;
        if (d >= 0) {
            for (int i = 0; i < d + 1; i++) {
                Log.e(TAG, "addview=" + childCount + i);
                View view = headerProvider.getHeader(mContext, childCount + i);

                view.setTag(i);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (accordionItemClick != null) {
                            accordionItemClick.onItemCLick(v, childCount + (Integer) v.getTag());
                        }
                    }
                });
                addView(view, childCount + i);
                heightMap.put(childCount + i, view.getHeight());
            }
        } else {
            for (int i = 0; i < -d - 1; i++) {
                Log.e(TAG, "removeViewAt=" + (childCount - i - 1));
                removeViewAt(childCount - i - 1);
            }
        }
    }

    @Override
    public void removeViewAt(int index) {
        oldBottom = getBottom();
        super.removeViewAt(index);
    }

    @Override
    public void init(Context context) {

        mContext = context;
        setOrientation(LinearLayout.VERTICAL);

    }

    @Override
    public void hideLastChild() {
        if (getChildCount() >= 1) {

            getChildAt(getChildCount() - 1).setVisibility(INVISIBLE);
        }
    }

    public void setHeaderProvider(HeaderProvider headerProvider) {
        this.headerProvider = headerProvider;
    }

    public void setAccordionItemClick(AccordionItemClick accordionItemClick) {
        this.accordionItemClick = accordionItemClick;
    }

    @Override
    public int getBottomOnPosition(int position) {
        if (position < getChildCount()) {
            final int bottom = getChildAt(position).getBottom();
            Log.e(TAG, "position=" + position + " bottom=" + bottom);
            return bottom;
        }
        return 0;
    }

    @Override
    public int getHeigthOnPosition(int position) {
        if (position == -1) {
            return 0;
        }
        if (position < getChildCount()) {
            final int height = getChildAt(position).getHeight();
            Log.e(TAG, "position=" + position + " height=" + height);
            return height;
        }
        return 0;
    }

    @Override
    public int getHeigthWithoutLastChild() {
        return getHeight() - getHeigthOnPosition(getChildCount() - 1);
    }
}
