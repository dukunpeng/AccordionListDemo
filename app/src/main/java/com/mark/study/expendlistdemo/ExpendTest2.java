package com.mark.study.expendlistdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

/**
 * @author Mark
 * @Date on 2019/10/28
 **/
public class ExpendTest2 extends AppCompatActivity {

    private final String TAG = "ExpendTest";
    //设置为-1是为了第一个琴键展示早
    private int firstVisibleIndex = -1;

    private MyAdater adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testexpend);
        adapter = new MyAdater(this);
        initExpendList();
    }

    private void initExpendList(){
        final LinearLayout linearLayout = findViewById(R.id.linearLayout);
        final ListView expandableListView = (ListView) findViewById(R.id.listView);
        // 为列表绑定数据源
        expandableListView.setAdapter(adapter);

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


    }

    private void refreshViewOnScroll(final int firstVisibleItem, final LinearLayout linearLayout, final ListView expandableListView) {

        firstVisibleIndex=  firstVisibleItem-1;
        int lineCount = linearLayout.getChildCount();
        int d = firstVisibleItem - lineCount;
        Log.d(TAG,"firstVisibleItem=="+firstVisibleItem+
                "lineCount=="+lineCount
        );
        if (d>=0){
            for (int i = 0; i <d+1 ; i++){
                addView(firstVisibleItem, linearLayout, expandableListView, lineCount+i);
            }
        }else {
            for (int i = 0; i <-d-1 ; i++){
                linearLayout.removeViewAt(lineCount-i-1);
            }
        }
    }

    private void addView(final int firstVisibleItem, final LinearLayout linearLayout, final ListView expandableListView, int i) {
        final View view1 = adapter.getView(i,null,null);
        view1.setTag(i);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firstVisibleItem>-1){
                    int  duration = (firstVisibleItem-(Integer) v.getTag())*400;
                    duration = duration>2000?2000:(duration<500?500:duration);
                    int height = linearLayout.getHeight()/linearLayout.getChildCount();
                    expandableListView.smoothScrollToPositionFromTop((Integer) v.getTag(),height*firstVisibleItem,duration);
                }
            }
        });
        linearLayout.addView(view1,i);
        expandableListView.smoothScrollToPositionFromTop(firstVisibleItem,linearLayout.getHeight(),0);

    }
    public String[] group_title_arry = new String[] { "第1", "第2","第3", "第4","第5", "第6","第7", "第8","第9" };

    private class MyAdater extends BaseAdapter{

        private Context mContext;

        public MyAdater(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return group_title_arry.length;
        }

        @Override
        public Object getItem(int position) {
            return group_title_arry[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = (LinearLayout) LinearLayout.inflate(ExpendTest2.this,
                    R.layout.group_layout, null);

            // 新建一个TextView对象，用来显示一级标签上的标题信息
            TextView group_title = (TextView) convertView
                    .findViewById(R.id.group_title);
            // 设置标题上的文本信息
            group_title.setText(group_title_arry[position]);
            AppCompatImageView aiv = convertView.findViewById(R.id.aiv);
            aiv.setImageResource(R.drawable.aa);
            if (parent==null){
                aiv.setVisibility(View.GONE);
            }else{
                aiv.setVisibility(View.VISIBLE);
            }
            // 设置整体描述上的文本信息

            return convertView;}
    }
}