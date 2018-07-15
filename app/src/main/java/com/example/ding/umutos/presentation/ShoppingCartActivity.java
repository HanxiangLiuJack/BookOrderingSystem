package com.example.ding.umutos.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
    private TextView shopInfoBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        shopList=(ListView)findViewById(R.id.shoppingList);

        userName = getIntent().getStringExtra("userName");
        userType = getIntent().getIntExtra("userType",-1);

        accessShoppingCart=new AccessShoppingCart();
        newShoppingCart=accessShoppingCart.getUserShoppingCart(userName);
        loadList(newShoppingCart);


    }

    private void loadList(List<Item> newShoppingList){

        int size=newShoppingList.size();
        ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i <size; i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("title", newShoppingList.get(i).getName());
            item.put("price", "$"+newShoppingList.get(i).getPrice());
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
                shopInfoBar.setText("You selected: "+ map.get("title"));
            }
        });
    }

    public void buttonHistoryBack(View view) {
        finish();
    }

}
