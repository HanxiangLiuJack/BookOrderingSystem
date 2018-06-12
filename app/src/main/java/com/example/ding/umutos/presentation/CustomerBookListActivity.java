package com.example.ding.umutos.presentation;
import com.example.ding.umutos.business.AccessBooks;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import com.example.ding.umutos.R;
import com.example.ding.umutos.objects.Book;

public class CustomerBookListActivity extends AppCompatActivity {

    private ListView bookList;
    private int bookID;
    private AccessBooks accessBookList;
    private List<Book> newBookList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_booklist);
        bookList=(ListView)findViewById(R.id.cusListView);

        accessBookList=new AccessBooks();
        newBookList=accessBookList.getBooks();

        int size=newBookList.size();

        int bookImg[]={R.mipmap.book0,R.mipmap.book1,R.mipmap.book2,R.mipmap.book3,R.mipmap.book4,R.mipmap.book5,R.mipmap.book6,R.mipmap.book7,R.mipmap.book8,R.mipmap.book9,R.mipmap.book10};

        ArrayList<HashMap<String, Object>> books = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i <size; i++) {
            HashMap<String, Object> book = new HashMap<String, Object>();
            book.put("id",""+newBookList.get(i).getBookID());
            book.put("img",bookImg[newBookList.get(i).getPicture()] );
            book.put("title", newBookList.get(i).getName());
            book.put("price","$"+newBookList.get(i).getPrice());
            books.add(book);
        }
        SimpleAdapter sItems = new SimpleAdapter(this,
                books,
                R.layout.activity_book_row,
                new String[] {"img", "title", "price","id" },
                new int[] { R.id.cusBookListImg, R.id.cusBookListTitle, R.id.cusBookListPrice , R.id.cusBookListID});


        bookList.setAdapter(sItems);

        bookList.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {

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
