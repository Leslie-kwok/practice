package com.qf.leslie.daylrulistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import bean.Adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Person> datas;
    MyAdapter adapter;

    String[] urls={
            "http://img2.duitang.com/uploads/item/201210/16/20121016190338_z3jFA.jpeg",
            "http://m.chanyouji.cn/tips/hanju.jpg",
            "http://m.chanyouji.cn/articles/580/217796828a84b8d45916eb763a98f192.jpg",
            "http://m.chanyouji.cn/articles/534/51b6a7a15a4c35948f09cfb4ec6bfe69.jpg",
            "http://m.chanyouji.cn/articles/578/50e0f1407caccc6e7379d5699df8582e.jpg",
            "http://m.chanyouji.cn/articles/587/e14f0f98ab6c0551a077a0acc63e1a55.jpg"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        initData();

        //创建适配器并设置给listview
        adapter=new MyAdapter(this,datas);
        listView.setAdapter(adapter);

    }

    private void initData() {
        //创建数据源
        datas=new ArrayList<Person>();
        Person person=null;
        for (int i = 0; i < 6; i++) {
            person=new Person("picture"+i,urls[i]);
            datas.add(person);
        }
    }


    private void initView() {
        listView= (ListView) findViewById(R.id.listviewId);
    }


}
