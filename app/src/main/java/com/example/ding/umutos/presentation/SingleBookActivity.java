package com.example.ding.umutos.presentation;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.business.AccessBooks;
import com.example.ding.umutos.business.AccessShoppingCart;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ding.umutos.R;
import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.objects.Item;
import java.util.List;

public class SingleBookActivity extends AppCompatActivity {

    private int bookID, userType;
    private String userName;
    private TextView bookTitle, bookAuthor, bookPrice, bookOwner, bookDecription, bookCategory;
    private ImageView bookImg;
    private Book newBook;
    private AccessBooks accessBookList;
    private AccessAccounts accessAccounts;
    private AccessShoppingCart accessShoppingCart;
    private RatingBar rb_normal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        accessShoppingCart=new AccessShoppingCart(  );

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_singlebook);
        bookID = getIntent().getIntExtra("bookID",-1);
        userName = getIntent().getStringExtra("userName");
        userType = getIntent().getIntExtra("userType",-1);


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
        rb_normal=(RatingBar)findViewById( R.id.ratingBar ) ;

        bookTitle.setText(newBook.getName());
        bookAuthor.setText("by "+newBook.getAuthor());
        bookPrice.setText("$"+newBook.getPrice());
        String sellerName;
        double rate;
        List<Account> a = accessAccounts.getAccounts();
        rate = accessAccounts.getAccountRate(newBook.getOwner(),a);
        sellerName = accessBookList.searchBook(bookID).getOwner();
        bookOwner.setText("Sold by "+sellerName);
        bookDecription.setText(newBook.getDescription());
        bookImg.setImageResource(newBook.getPicture());
        bookCategory.setText("Category: "+newBook.getCategory());
        rb_normal.setRating( (float)rate);

    }


    public void buttonAdd(View view) {
        showDialog(newBook.getName());
    }

    private void showDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+"Sure to add "+title+" to your Shopping Cart? \nPress 'Yes' to the Delivery Info page.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Item aItem=new Item(userName,newBook.getBookID(),newBook.getName(),newBook.getPrice());

                        if(!accessShoppingCart.insertShoppingCart( aItem )){
                            showOverDialog();
                        }
                        else{
                            Toast.makeText(SingleBookActivity.this, "Book added!",
                                    Toast.LENGTH_LONG).show();

                        }
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

    private void showOverDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert:")
                .setMessage("This book already existed in the shopping cart!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                })

                .show();
    }



    public void btnSingleToShoppingCart(View view) {
        Intent intent = new Intent(SingleBookActivity.this, ShoppingCartActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }
}
