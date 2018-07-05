package com.example.ding.umutos.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ding.umutos.R;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.business.AccessBooks;
import com.example.ding.umutos.business.AccessOrders;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.objects.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView bookList;
    private AccessOrders accessOrderList;
    private List<Order> newOrderList;
    int userType,userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_booklist);
        bookList=(ListView)findViewById(R.id.historyBookList);

        userID = getIntent().getIntExtra("userID",-1);
        userType=getIntent().getIntExtra("userType",-1);

        accessOrderList=new AccessOrders(  );
        newOrderList=accessOrderList.orderHistory( userID,userType );
        loadList(newOrderList);

        TextView historyBar=(TextView)findViewById(R.id.historyBar);
        historyBar.setText("Order history");
    }

    private void loadList(List<Order> newOrderList){

        int size=newOrderList.size();
        ArrayList<HashMap<String, Object>> books = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i <size; i++) {
            AccessAccounts accounts=new AccessAccounts(  );
            HashMap<String, Object> book = new HashMap<String, Object>();
            book.put("account","Sold by: "+accounts.getAccountByID( newOrderList.get(i).getSellerID() ).getUserName()+"\nBought by: "+accounts.getAccountByID( newOrderList.get(i).getBuyerID() ).getUserName());
            book.put("title", newOrderList.get(i).getBookName());
            book.put("price", "$"+newOrderList.get(i).getPrice());
            book.put("address","Address: "+newOrderList.get(i).getAddress());
            books.add(book);
        }
        SimpleAdapter sItems = new SimpleAdapter(this,
                books,
                R.layout.activity_history_row,
                new String[] {"account", "title", "price","address" },
                new int[] { R.id.hisBookListByAccount, R.id.hisBookListTitle, R.id.hisBookListPrice , R.id.hisBookListAddress});
        bookList.setAdapter(sItems);
    }

    public void buttonHistoryBack(View view) {
        finish();
    }

}
