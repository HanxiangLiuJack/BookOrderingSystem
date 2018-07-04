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
import com.example.ding.umutos.objects.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView bookList;
    private AccessBooks accessBookList;
    private List<Book> newBookList;
    int userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_booklist);
        bookList=(ListView)findViewById(R.id.historyBookList);
        accessBookList=new AccessBooks();
        newBookList=accessBookList.getBooks();
        int size=newBookList.size();
        ArrayList<HashMap<String, Object>> books = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i <size; i++) {
            HashMap<String, Object> book = new HashMap<String, Object>();
            book.put("id",""+newBookList.get(i).getBookID());
            book.put("account",newBookList.get(i).getOwner());
            book.put("title", newBookList.get(i).getName());
            book.put("address",newBookList.get(i).getPrice());
            books.add(book);
        }
        SimpleAdapter sItems = new SimpleAdapter(this,
                books,
                R.layout.activity_book_row,
                new String[] {"account", "title", "price","address" },
                new int[] { R.id.hisBookListByAccount, R.id.hisBookListTitle, R.id.hisBookListPrice , R.id.hisBookListAddress});
        bookList.setAdapter(sItems);

        TextView historyBar=(TextView)findViewById(R.id.historyBar);
        historyBar.setText("Order history");
    }
}
