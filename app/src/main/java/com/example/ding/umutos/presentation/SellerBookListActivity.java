package com.example.ding.umutos.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.ding.umutos.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SellerBookListActivity extends AppCompatActivity {

    private ListView bookList;
    private int bookID;
    private String bookTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_booklist);
        bookList=(ListView)findViewById(R.id.friends);
        int bookImg[]={R.mipmap.book3,R.mipmap.book3,R.mipmap.book3,R.mipmap.book3,R.mipmap.book3,R.mipmap.book3};
        bookID=-1;

        ArrayList<HashMap<String, Object>> books = new ArrayList<HashMap<String, Object>>();
        for (int i = 1; i <= 6; i++) {
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
                bookTitle=map.get("title");

            }

        });
    }

    public void buttonAddNewBook(View view) {
        openEditBookActivity();
    }

    public void buttonEditPostedBook(View view) {
        if (bookID<0)
            showEditDialog();
        else
            openEditBookActivity(bookID);
    }

    public void buttonDeletePostedBook(View view) {
        if (bookID<0)
            showDeleteDialog();
        else
            showDeleteDialog(bookTitle);
    }

    private void showDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert:")
                .setMessage("\n"+"Please select a book to delete.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                })
                .show();
    }

    private void showDeleteDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+"Sure to delete "+msg+"?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        finish();
                        Intent intent = new Intent(SellerBookListActivity.this, SellerBookListActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                })
                .show();
    }

    private void showEditDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert:")
                .setMessage("\n"+"Please select a book to edit.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                })

                .show();
    }

    private void openEditBookActivity(){
        Intent intent = new Intent(SellerBookListActivity.this,EditBookActivity.class);
        SellerBookListActivity.this.startActivity(intent);
    }

    private void openEditBookActivity(int bookID){
        Intent intent = new Intent(SellerBookListActivity.this,EditBookActivity.class);
        intent.putExtra("bookID", bookID);
        SellerBookListActivity.this.startActivity(intent);
    }



}
