package com.example.ding.umutos.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;


import android.view.View;
import android.widget.AdapterView;



import com.example.ding.umutos.R;

public class CustomerBookListActivity extends AppCompatActivity {

    private ListView bookList;
    private int bookID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booklist);
        bookList=(ListView)findViewById(R.id.cusListView);
        int bookImg[]={R.mipmap.book1,R.mipmap.book2,R.mipmap.book3};

        ArrayList<HashMap<String, Object>> books = new ArrayList<HashMap<String, Object>>();
        for (int i = 1; i <= 3; i++) {
            HashMap<String, Object> book = new HashMap<String, Object>();
            book.put("id",""+i);
            book.put("img",bookImg[i-1] );
            book.put("title", "name(" + i+")");
            book.put("price",""+ i);
            books.add(book);
        }
        SimpleAdapter sItems = new SimpleAdapter(this,
                books,
                R.layout.activity_customer_book_row,//每一个user xml 相当ListView的一个组件
                new String[] {"img", "title", "price","id" },
                // 分别对应view 的id
                new int[] { R.id.cusBookListImg, R.id.cusBookListTitle, R.id.cusBookListPrice , R.id.cusBookListID});


        bookList.setAdapter(sItems);


        bookList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //获得选中项的HashMap对象
                HashMap<String,String> map=(HashMap<String,String>)bookList.getItemAtPosition(arg2);
                String id=map.get("id");
                bookID=Integer.parseInt(id);
                Intent intent = new Intent(CustomerBookListActivity.this,SingleBookActivity.class);
                intent.putExtra("bookID", bookID);
                CustomerBookListActivity.this.startActivity(intent);

            }

        });
    }



}
