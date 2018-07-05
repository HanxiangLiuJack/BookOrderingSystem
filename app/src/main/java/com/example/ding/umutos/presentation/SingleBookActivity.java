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
import com.example.ding.umutos.objects.BookImage;

public class SingleBookActivity extends AppCompatActivity {

    private int bookID, userID;
    private TextView bookTitle, bookAuthor, bookPrice, bookOwner, bookDecription, bookCategory;
    private ImageView bookImg;
    private Book newBook;
    private AccessBooks accessBookList;
    private AccessAccounts accessAccounts;
    private BookImage bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        bookImage = new BookImage();
        setContentView(R.layout.activity_singlebook);
        bookID = getIntent().getIntExtra("bookID",-1);
        userID = getIntent().getIntExtra("userID",-1);

        accessBookList=new AccessBooks();
        accessAccounts=new AccessAccounts();
        newBook=accessBookList.searchBook(bookID);

        bookTitle=(TextView)findViewById(R.id.singleBookTitle);
        bookAuthor=(TextView)findViewById(R.id.singleBookAuthor);
        bookPrice=(TextView)findViewById(R.id.singleBookPrice);
        bookOwner=(TextView)findViewById(R.id.singleBookSellerName);
        bookDecription=(TextView)findViewById(R.id.singleBookDes);
        bookImg=(ImageView)findViewById(R.id.singleBookImg);
        bookCategory=(TextView)findViewById(R.id.singleBookCategory);

        bookTitle.setText(newBook.getName());
        bookAuthor.setText("by "+newBook.getAuthor());
        bookPrice.setText("$"+newBook.getPrice());
        System.out.println(newBook.getOwner());
        bookOwner.setText("Sold by "+accessAccounts.getAccountByID(newBook.getOwner()).getUserName());
        bookDecription.setText(newBook.getDescription());
        bookImg.setImageResource(bookImage.getImageByBookID(bookID));
        bookCategory.setText("Category: "+newBook.getCategory());

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
                        intent.putExtra("userID", userID);
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
