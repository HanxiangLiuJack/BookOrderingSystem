package com.example.ding.umutos.presentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ding.umutos.R;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.objects.Account;

public class HomeActivity extends AppCompatActivity {
    private int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userID = getIntent().getIntExtra("userID",-1);
        AccessAccounts accounts=new AccessAccounts();
        Account newAcc=accounts.getAccountByID(userID);
        TextView welcome =(TextView)findViewById(R.id.welcomeMessage);
        String name=newAcc.getUserName().split(" ")[0];
        welcome.setText("Hi, "+name);


    }


    public void buttonSellOnClick(View v) {
        int userType=0;
        Intent sellIntent = new Intent(HomeActivity.this, BookListActivity.class);
        sellIntent.putExtra("userType", userType);
        HomeActivity.this.startActivity(sellIntent);
    }

    public void buttonBuyOnClick(View v) {
        int userType=1;
        Intent buyIntent = new Intent(HomeActivity.this, BookListActivity.class);
        buyIntent.putExtra("userType", userType);
        HomeActivity.this.startActivity(buyIntent);
    }
}
