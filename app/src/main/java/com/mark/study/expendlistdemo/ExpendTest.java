package com.mark.study.expendlistdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

/**
 * @author Mark
 * @Date on 2019/10/28
 **/
public class ExpendTest extends AppCompatActivity {

    private final String TAG = "ExpendTest";
    //设置为-1是为了第一个琴键展示早
    private int firstVisibleIndex = -1;

    private ExpandableListAdapter adapter;

    private int linearHeight = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testexpend);
        adapter = new ExpandableListAdapter(this);
        initExpendList();
    }

    private void initExpendList(){
        final LinearLayout linearLayout = findViewById(R.id.linearLayout);
        final ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.list);
        // 设置默认图标为不显示状态
        expandableListView.setGroupIndicator(null);
        // 为列表绑定数据源
        expandableListView.setAdapter(adapter);
        // 设置一级item点击的监听器
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
                Log.d(TAG,"firstVisibleItem=="+firstVisibleItem+
                        "firstVisibleIndex=="+firstVisibleIndex
                );

                if (firstVisibleIndex!=firstVisibleItem){
                    refreshViewOnScroll(firstVisibleItem, linearLayout,expandableListView);
                }


            }
        });
        // 设置二级item点击的监听器
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });
        for (int i = 0; i <adapter.group_title_arry.length ; i++) {

            expandableListView.expandGroup(i);
        }
    }

    private void refreshViewOnScroll(final int firstVisibleItem, final LinearLayout linearLayout, final ExpandableListView expandableListView) {

        firstVisibleIndex=  firstVisibleItem;
        int lineCount = linearLayout.getChildCount();
        int d = firstVisibleItem - lineCount;
        Log.d(TAG,"firstVisibleItem=="+firstVisibleItem+
                "lineCount=="+lineCount
        );
        if (d>=0){
            for (int i = 0; i <d ; i++){
                addView(firstVisibleItem, linearLayout, expandableListView, lineCount+i);
            }
        }else {
            for (int i = 0; i <-d-1 ; i++){
                linearLayout.removeViewAt(lineCount-i-1);
            }
        }
    }

    private void addView(final int firstVisibleItem, final LinearLayout linearLayout, final ExpandableListView expandableListView, int i) {
        final View view1 = adapter.getGroupView(i,false,null,null);
        view1.setTag(i);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firstVisibleItem>-1){
                    int  duration = (firstVisibleItem-(Integer) v.getTag())*400;
                    duration = duration>2000?2000:(duration<500?500:duration);
                    expandableListView.smoothScrollToPositionFromTop((Integer) v.getTag(),-1,duration);
                }
            }
        });
        linearLayout.addView(view1,i);
    }

}
