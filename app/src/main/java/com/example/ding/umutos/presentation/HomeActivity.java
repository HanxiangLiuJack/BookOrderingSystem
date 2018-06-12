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
        Intent sellIntent = new Intent(HomeActivity.this, SellerBookListActivity.class);
        HomeActivity.this.startActivity(sellIntent);
    }

    public void buttonBuyOnClick(View v) {
        Intent buyIntent = new Intent(HomeActivity.this, CustomerBookListActivity.class);
        HomeActivity.this.startActivity(buyIntent);
    }
}
