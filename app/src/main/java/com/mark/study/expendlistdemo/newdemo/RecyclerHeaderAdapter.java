package com.mark.study.expendlistdemo.newdemo;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.mark.study.expendlistdemo.R;

import java.util.List;

/**
 * @author Mark
 * @Date on 2019/10/30
 **/
public class RecyclerHeaderAdapter extends BaseRecycleAdapter implements HeaderProvider {

    private final String TAG = "RecyclerHeader";

    public RecyclerHeaderAdapter(Context context, List list, int itemLayout, boolean isNeedMore) {
        super(context, list, itemLayout, isNeedMore);
    }

    @Override
    protected void setPositionClick(int position, Object bean) {

    }

    @Override
    protected void initData(MyViewHolder holder, int position, Object bean) {
        Log.e(TAG, "position=" + position);
        holder.setText(R.id.group_title, bean.toString());
        holder.setImageDrawable(R.id.aiv, context.getDrawable(R.drawable.aa));
    }


    @Override
    public View getHeader(Context context, int position) {
        TextView textView = new TextView(context);
        textView.setText(getList().get(position).toString());
        textView.setBackgroundColor(Color.WHITE);
        int padding = ScreenUtil.dp2px(context, 15);
        textView.setPadding(padding, padding, padding, padding);
        textView.setTextSize(18f);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
