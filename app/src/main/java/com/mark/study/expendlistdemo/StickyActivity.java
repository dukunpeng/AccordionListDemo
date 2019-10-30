package com.mark.study.expendlistdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mark.study.expendlistdemo.newdemo.BaseRecycleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mark
 * @Date on 2019/10/29
 **/
public class StickyActivity extends AppCompatActivity implements StickyHeaderAdapter<BaseRecycleAdapter.MyViewHolder> {

    RecyclerView recyclerView0;
    BaseRecycleAdapter baseRecycleAdapter;
    private final String TAG = "StickyActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView0 = new RecyclerView(this);
        setContentView(recyclerView0);
        List<String> list = new ArrayList<>();
        for (int i = 0; i <20 ; i++) {

            list.add("第"+i+"个标题");
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView0.setLayoutManager(layoutManager);
        recyclerView0.addItemDecoration(new StickyHeaderDecoration(this));
        recyclerView0.setAdapter(baseRecycleAdapter=new BaseRecycleAdapter<String>(this,list,R.layout.group_layout,false) {
            @Override
            protected void setPositionClick(int position, String bean) {

            }

            @Override
            protected void initData(MyViewHolder holder, int position, String bean) {
                Log.e(TAG,"position="+position);
                holder.setText(R.id.group_title,bean);
                holder.setImageDrawable(R.id.aiv,getDrawable(R.drawable.aa));
            }
        });
    }

    @Override
    public long getHeaderId(int childAdapterPosition) {
      List list = baseRecycleAdapter.getList();
      if (list==null||list.size()<=0){
          return -1;
      }
        Log.e(TAG,"getHeaderId="+childAdapterPosition);
        return childAdapterPosition;
    }

    @Override
    public BaseRecycleAdapter.MyViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public BaseRecycleAdapter.MyViewHolder onCreateHeaderViewHolder(ViewGroup parent, int adapterPosition) {
       View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_recyclerview, parent, false);
        Log.e(TAG,"adapterPosition="+adapterPosition);



        return baseRecycleAdapter.new MyViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(BaseRecycleAdapter.MyViewHolder holder, final int childAdapterPosition) {
        Log.e(TAG,"childAdapterPosition="+childAdapterPosition);
        RecyclerView recyclerView = (RecyclerView) holder.getView(R.id.recylerview);
        List<String> list = new ArrayList<>();
        for (int i = 0; i <childAdapterPosition+1 ; i++) {
            list.add("第"+i+"个header");
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new BaseRecycleAdapter(StickyActivity.this,list,R.layout.simple_text,false) {
            @Override
            protected void setPositionClick(int position, Object bean) {
                recyclerView0.smoothScrollToPosition(childAdapterPosition);
            }

            @Override
            protected void initData(MyViewHolder holder, int position, Object bean) {

                ((TextView)holder.itemView).setText(bean.toString());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerView0.smoothScrollToPosition(childAdapterPosition);
                    }
                });
            }
        });
        //holder.setText(R.id.group_title,"第"+childAdapterPosition+"个header");
    }
}
