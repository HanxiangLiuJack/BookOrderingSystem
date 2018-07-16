package com.example.ding.umutos.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ding.umutos.R;
import com.example.ding.umutos.business.AccessShoppingCart;
import com.example.ding.umutos.objects.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    private ListView shopList;
    private AccessShoppingCart accessShoppingCart;
    private List<Item> newShoppingCart;
    private int userType, bookID;
    private String userName;
    private TextView priceBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        shopList=(ListView)findViewById(R.id.shoppingList);
        priceBar=(TextView)findViewById( R.id.priceBar );

        userName = getIntent().getStringExtra("userName");
        userType = getIntent().getIntExtra("userType",-1);

        accessShoppingCart=new AccessShoppingCart();
        newShoppingCart=accessShoppingCart.getUserShoppingCart(userName);
        loadList(newShoppingCart);
        priceBar.setText( "Total: $"+accessShoppingCart.getTotalPrice( userName ) );



    }

    private void loadList(List<Item> newShoppingList){

        int size=newShoppingList.size();
        ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i <size; i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("title", newShoppingList.get(i).getName());
            item.put("price", "$"+newShoppingList.get(i).getPrice());
            item.put("id", ""+newShoppingList.get(i).getBookID());
            items.add(item);
        }
        SimpleAdapter sItems = new SimpleAdapter(this,
                items,
                R.layout.activity_shopping_row,
                new String[] { "title", "price" },
                new int[] { R.id.shoppingItemTitle, R.id.shoppingItemPrice});
        shopList.setAdapter(sItems);

        shopList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                HashMap<String,String> map=(HashMap<String,String>)shopList.getItemAtPosition(arg2);
                String id=map.get("id");
                bookID=Integer.parseInt(id);

                Toast toast = Toast.makeText(ShoppingCartActivity.this, "You have selected:" +  map.get("title"), Toast.LENGTH_SHORT);
                toast.setGravity( Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

    }

    public void btnShopToCus(View view) {
        Intent intent = new Intent(ShoppingCartActivity.this, BookListActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userType", userType);
        startActivity(intent);
    }

    public void btnDeleteItem(View view) {
        if (bookID<1)
            showDeleteDialog();
        else
            showDeleteDialog("Success");
    }

    private void showDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert:")
                .setMessage("\n"+"Please select a book to delete.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {}
                })
                .show();
    }

    private void showDeleteDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+"Sure to delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        accessShoppingCart.deleteBookfromShoppingCart( bookID,userName );
                        Intent intent = new Intent(ShoppingCartActivity.this, ShoppingCartActivity.class);
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

    public void btnShoppingCartBuy(View view) {
        if (accessShoppingCart.getUserShoppingCart( userName ).size()==0){
            showFailDialog();
        }else{
            showDialog();
        }
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+"Sure to buy all these books? \nPress 'Yes' to the Delivery Info page.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(ShoppingCartActivity.this,AddressActivity.class);
                        intent.putExtra("userName", userName);
                        ShoppingCartActivity.this.startActivity(intent);                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                })
                .show();
    }

    private void showFailDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert:")
                .setMessage("\nShopping cart is empty!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(ShoppingCartActivity.this,AddressActivity.class);
                        intent.putExtra("userName", userName);
                        ShoppingCartActivity.this.startActivity(intent);                    }
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
