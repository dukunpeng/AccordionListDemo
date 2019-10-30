package com.mark.study.expendlistdemo;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author Mark
 * @Date on 2019/10/28
 **/
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    public String[] group_title_arry = new String[] { "第1", "第2","第3", "第4","第5", "第6","第7", "第8","第9" };

    private Context mContext;
    public ExpandableListAdapter(Context context) {
        mContext = context;
    }

    /**
     * 获取一级标签总数
     */
    @Override
    public int getGroupCount() {
        return group_title_arry.length;
    }

    /**
     * 获取一级标签内容
     */
    @Override
    public Object getGroup(int groupPosition) {
        return group_title_arry[groupPosition];
    }

    /**
     * 获取一级标签的ID
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获取一级标签下二级标签的总数
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    /**
     * 获取一级标签下二级标签的内容
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return "";
    }

    /**
     * 获取二级标签的ID
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 指定位置相应的组视图
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 对一级标签进行设置
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        convertView = (LinearLayout) LinearLayout.inflate(mContext,
                R.layout.group_layout, null);

        // 新建一个TextView对象，用来显示一级标签上的标题信息
        TextView group_title = (TextView) convertView
                .findViewById(R.id.group_title);
        // 设置标题上的文本信息
        group_title.setText(group_title_arry[groupPosition]);
        // 设置整体描述上的文本信息

        return convertView;
    }

    /**
     * 对一级标签下的二级标签进行设置
     */
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // 为视图对象指定布局
        AppCompatImageView avi;
        if (convertView==null){
            avi= new AppCompatImageView(mContext);
            avi.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            ViewGroup.LayoutParams layoutParams =avi.getLayoutParams();
            if (layoutParams==null){
                layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            avi.setLayoutParams(layoutParams);
        }else {
            avi = (AppCompatImageView) convertView;
        }

        avi.setImageDrawable(mContext.getDrawable(R.drawable.aa));

        return avi;
    }

    /**
     * 当选择子节点的时候，调用该方法
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}