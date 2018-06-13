package com.example.ding.umutos.presentation;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.business.AccessBooks;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import com.example.ding.umutos.R;
import com.example.ding.umutos.objects.Book;

public class SingleBookActivity extends AppCompatActivity {

    private int bookID;
    private TextView bookTitle, bookAuthor, bookPrice, bookOwner, bookDecription;
    private ImageView bookImg;
    private Book newBook;
    private AccessBooks accessBookList;
    private AccessAccounts accessAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_singlebook);
        bookID = getIntent().getIntExtra("bookID",-1);
        int bookImgArr[]={R.mipmap.book0,R.mipmap.book1,R.mipmap.book2,R.mipmap.book3,R.mipmap.book4,R.mipmap.book5,R.mipmap.book6,R.mipmap.book7,R.mipmap.book8,R.mipmap.book9,R.mipmap.book10};

        accessBookList=new AccessBooks();
        accessAccounts=new AccessAccounts();
        newBook=accessBookList.searchBook(bookID);

        bookTitle=(TextView)findViewById(R.id.singleBookTitle);
        bookAuthor=(TextView)findViewById(R.id.singleBookAuthor);
        bookPrice=(TextView)findViewById(R.id.singleBookPrice);
        bookOwner=(TextView)findViewById(R.id.singleBookSellerName);
        bookDecription=(TextView)findViewById(R.id.singleBookDes);
        bookImg=(ImageView)findViewById(R.id.singleBookImg);

        bookTitle.setText(newBook.getName());
        bookAuthor.setText("by "+newBook.getAuthor());
        bookPrice.setText("$"+newBook.getPrice());
        bookOwner.setText("Sold by "+accessAccounts.getAccountByID(newBook.getOwner()).getUserName());
        bookDecription.setText(newBook.getDescription());
        bookImg.setImageResource(bookImgArr[newBook.getPicture()]);

    }


    public void buttonAdd(View view) {
        showDialog(newBook.getName());
    }

    private void showDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+"Sure to buy "+title+"? \nPress 'Yes' to the Delivery Info page.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(SingleBookActivity.this,AddressActivity.class);
                        intent.putExtra("bookID", bookID);
                        SingleBookActivity.this.startActivity(intent);
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
}
