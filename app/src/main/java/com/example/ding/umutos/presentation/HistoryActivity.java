package com.example.ding.umutos.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ding.umutos.R;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.business.AccessOrders;
import com.example.ding.umutos.objects.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HistoryActivity extends AppCompatActivity {
    private ListView bookList;
    private AccessOrders accessOrderList;
    private List<Order> newOrderList;
    private int userType;
    private String userName, sellerName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_booklist);
        bookList=(ListView)findViewById(R.id.historyBookList);

        userName = getIntent().getStringExtra("userName");
        userType = getIntent().getIntExtra("userType",-1);

        accessOrderList=new AccessOrders();
        if(userType == 1)
            newOrderList = accessOrderList.buyerOrderHistory(userName);
        else
            newOrderList=accessOrderList.sellerOrderHistory(userName);
        loadList(newOrderList);



        TextView historyBar=(TextView)findViewById(R.id.historyBar);
        if (userType==0){
            historyBar.setText("Order history");

        }else{
            historyBar.setText( "Order History\n (Select an order to rate seller)" );
        }
    }

    private void loadList(List<Order> newOrderList){

        int size=newOrderList.size();
        ArrayList<HashMap<String, Object>> books = new ArrayList<HashMap<String, Object>>();
        AccessAccounts accounts=new AccessAccounts(  );

        for (int i = 0; i <size; i++) {
            HashMap<String, Object> book = new HashMap<String, Object>();
            book.put("account","Sold by: "+accounts.getAccountByUserName( newOrderList.get(i).getSellerName() ).getUserName()+"\nBought by: "+accounts.getAccountByUserName( newOrderList.get(i).getBuyerName() ).getUserName());
            book.put("title", newOrderList.get(i).getBookName());
            book.put("price", "$"+newOrderList.get(i).getPrice());
            book.put("address","Address: "+newOrderList.get(i).getAddress());
            book.put("seller", accounts.getAccountByUserName( newOrderList.get(i).getSellerName()).getUserName());
            books.add(book);

        }
        SimpleAdapter sItems = new SimpleAdapter(this,
                books,
                R.layout.activity_history_row,
                new String[] {"account", "title", "price","address" },
                new int[] { R.id.hisBookListByAccount, R.id.hisBookListTitle, R.id.hisBookListPrice , R.id.hisBookListAddress});
        bookList.setAdapter(sItems);

        if (userType==1){

            bookList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    HashMap<String,String> map=(HashMap<String,String>)bookList.getItemAtPosition(arg2);

                    sellerName=map.get("seller");
                    Intent intent = new Intent(HistoryActivity.this,RateActivity.class);
                    intent.putExtra("userName", userName);
                    intent.putExtra("userType", userType);
                    intent.putExtra("sellerName", sellerName);
                    HistoryActivity.this.startActivity(intent);

                }
            });
        }
    }

    public void buttonHistoryBack(View view) {
        Intent intent = new Intent(HistoryActivity.this, BookListActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("userName", userName);
        startActivity(intent);
    }


}
