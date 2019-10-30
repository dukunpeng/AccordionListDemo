package com.mark.study.expendlistdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.mark.study.expendlistdemo.newdemo.AccordionItemClick;
import com.mark.study.expendlistdemo.newdemo.AccordionLayout;
import com.mark.study.expendlistdemo.newdemo.OffsetLinearLayoutManager;
import com.mark.study.expendlistdemo.newdemo.RecyclerHeaderAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mark
 * @Date on 2019/10/28
 **/
public class AccordionActivity extends AppCompatActivity {

    private final String TAG = "ExpendTest3";

    private AccordionLayout buttonsLayout;

    private RecyclerView mRecyclerView;

    public Map<Integer, Integer> heightMap = new HashMap<>();

    private RecyclerHeaderAdapter recyclerHeaderAdapter;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_third);

        for (int i = 0; i < 20; i++) {

            list.add("第" + i + "个标题");
        }
        initViews();

    }

    private void initViews() {

        buttonsLayout = findViewById(R.id.linearLayout);
        mRecyclerView = findViewById(R.id.recyclerView);

        final OffsetLinearLayoutManager layoutManager = new OffsetLinearLayoutManager(this);

        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(recyclerHeaderAdapter=new RecyclerHeaderAdapter(this,list,R.layout.group_layout,false));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e(TAG,"newState="+newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 第一个可见位置
                int firstVisiablePosition = layoutManager.findFirstVisibleItemPosition();
                // 最后一个可见位置
                int lasVisiablePosition = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));

                //记录每个item高度
                if (heightMap.get(firstVisiablePosition)==null){
                    View firstVisiableView = layoutManager.findViewByPosition(firstVisiablePosition);
                    int height =  (firstVisiableView.getHeight());
                    heightMap.put(firstVisiablePosition, height);
                    Log.e(TAG,"i="+firstVisiablePosition + " height="+firstVisiablePosition);
                }


                Log.e(TAG," firstVisiablePosition="+firstVisiablePosition+" lasVisiablePosition="+lasVisiablePosition);

                if (firstVisiablePosition!=lasVisiablePosition){
                    View view = mRecyclerView.getChildAt(1);
                    if (view!=null){
                        int top = view.getTop();
                        Log.e(TAG,"top="+top+" buttonsLayout.getBottom()="+buttonsLayout.getBottom()+" dy="+dy);

                        //下拉
                        if (dy<0){
                            if (top-1<=buttonsLayout.getHeigthWithoutLastChild()){
                                int position = mRecyclerView.getChildLayoutPosition(view);
                                buttonsLayout.updateButtons(position);
                            }else {
                                int position = mRecyclerView.getChildLayoutPosition(view);
                                buttonsLayout.updateButtons(position-1);
                            }
                        }
                        //上滑
                        else if(dy>0){
                            if (top-1<=buttonsLayout.getBottom()){
                                int position = mRecyclerView.getChildLayoutPosition(view);
                                buttonsLayout.updateButtons(position);
                            }else {
                                int position = mRecyclerView.getChildLayoutPosition(view);
                                buttonsLayout.updateButtons(position-1);
                            }
                        }

                    }


                }
            }
        });
        buttonsLayout.setHeaderProvider(recyclerHeaderAdapter);
        buttonsLayout.setAccordionItemClick(new AccordionItemClick() {
            @Override
            public void onItemCLick(View view, int position) {
                // 第一个可见位置
                int firstVisiablePosition = layoutManager.findFirstVisibleItemPosition();

                View firstVisiableView = layoutManager.findViewByPosition(firstVisiablePosition);
                //第一个可见位置距顶部的高度
                int top = firstVisiableView.getTop();

                int dy = 0,height = 0;
                for (int i = 0; i < firstVisiablePosition-position; i++) {
                    Integer itemHeight = heightMap.get(firstVisiablePosition-i);
                    height += itemHeight==null?0:itemHeight;
                    Log.e(TAG,"position="+position+"height="+height);
                }
                height -= top;
                final int lHeight =  buttonsLayout.getBottomOnPosition(position);
                final int itemHeight =  buttonsLayout.getHeigthOnPosition(position);
                dy = height+lHeight-itemHeight;
                Log.e(TAG,"position="+position+" top="+top+ " dy="+dy+" firstVisiablePosition="+firstVisiablePosition);
                mRecyclerView.smoothScrollBy(0, -dy);
            }
        });
    }

}
