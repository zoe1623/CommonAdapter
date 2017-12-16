package com.zoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.zoe.adapter.CommonAdapter;
import com.zoe.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv, lv2;
    private List<User> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        lv = (ListView)findViewById(R.id.lv);
        lv2 = (ListView)findViewById(R.id.lv2);
        //CommonAdapter 使用案例
        lv.setAdapter(new CommonAdapter<User>(this, mList, R.layout.item_user) {
            @Override
            public void bindData(User data) {
                ((TextView)findViewById(R.id.name)).setText(data.name);
                ((TextView)findViewById(R.id.sex)).setText(data.sex);
            }
        });
        //ViewHolder 使用案例
        lv2.setAdapter(new TestViewHolderAdapter());
    }

    private void initData() {
        for(int i = 0; i < 500; i++){
            mList.add(new User("zz"+i,i%3==0? "男" : "女"));
        }
    }
    //ViewHolder 使用案例
    class TestViewHolderAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public User getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = View.inflate(getApplicationContext(), R.layout.item_user,null);
            }
            User user = mList.get(position);
            //ViewHolder 使用案例
            ((TextView) ViewHolder.get(convertView, R.id.name)).setText(user.name);
            ((TextView) ViewHolder.get(convertView, R.id.sex)).setText(user.sex);
            return convertView;
        }
    }
}
