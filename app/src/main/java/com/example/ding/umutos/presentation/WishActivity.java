package com.example.ding.umutos.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ding.umutos.R;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.business.AccessOrders;
import com.example.ding.umutos.business.AccessWishlists;
import com.example.ding.umutos.objects.Order;
import com.example.ding.umutos.objects.Wish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WishActivity extends AppCompatActivity {
    private ListView wishList;
    private AccessWishlists accessWishList;
    private List<Wish> newWishList;
    private TextView infoBar;
    private int userType;
    private String userName;
    private int bookID=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName = getIntent().getStringExtra("userName");
        userType = getIntent().getIntExtra("userType",-1);

        accessWishList=new AccessWishlists();
        if(userType == 1){
            setContentView(R.layout.activity_cuswishlist );
            wishList=(ListView)findViewById(R.id.wishList);
            newWishList = accessWishList.getUserWishLists( userName );
            infoBar=(TextView)findViewById(R.id.wishListBar);
        }
        else{
            setContentView(R.layout.activity_sellerwishlist );
            wishList=(ListView)findViewById(R.id.wishList);
            newWishList=accessWishList.getWishList();

        }

        loadList(newWishList);

    }

    private void loadList(List<Wish> newWishList){

        int size=newWishList.size();
        ArrayList<HashMap<String, Object>> wishes = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i <size; i++) {
            HashMap<String, Object> wish = new HashMap<String, Object>();
            wish.put("title", newWishList.get( i ).getName());
            wish.put("author","Author: "+newWishList.get(i).getAuthorName());
            wish.put("user","Wished by: "+newWishList.get(i).getUserName());
            wish.put("id",""+newWishList.get(i).getBookID());
            wishes.add(wish);
        }
        SimpleAdapter sItems = new SimpleAdapter(this,
                wishes,
                R.layout.activity_wishlist_row,
                new String[] {"title", "author", "user" },
                new int[] { R.id.wishListTitle, R.id.wishListByAuthor, R.id.wisher});
        wishList.setAdapter(sItems);

        if (userType==1){
            wishList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    HashMap<String,String> map=(HashMap<String,String>)wishList.getItemAtPosition(arg2);
                    String id=map.get("id");
                    bookID=Integer.parseInt(id);
                    infoBar.setTextSize( 2,15 );
                    infoBar.setText("You selected wish: "+ map.get("title"));
                }
            });
        }
    }


    public void btnWishBack(View view){
        Intent intent = new Intent(WishActivity.this, BookListActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }

    public void btnOpenNewWish(View view){
        Intent intent = new Intent(WishActivity.this, NewWishActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }


    public void btnDeleteWish(View view) {
        if (bookID<1)
            showDeleteDialog();
        else
            showDeleteDialog("Success");
    }

    private void showDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert:")
                .setMessage("\n"+"Please select a wish to delete.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {}
                })
                .show();
    }

    private void showDeleteDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+"Sure to delete this wish?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        accessWishList.deleteWishList( bookID,userName );
                        Intent intent = new Intent(WishActivity.this, WishActivity.class);
                        intent.putExtra("userName", userName);
                        intent.putExtra("userType", userType);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {}
                })
                .show();
    }

}
