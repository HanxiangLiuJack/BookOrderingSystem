package com.example.ding.umutos.presentation;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.ding.umutos.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
