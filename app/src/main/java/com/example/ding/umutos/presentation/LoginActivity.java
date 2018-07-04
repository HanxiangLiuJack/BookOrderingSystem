package com.example.ding.umutos.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ding.umutos.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void buttonLoginOnClick(View v) {
        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
        LoginActivity.this.startActivity(homeIntent);
    }

    public void buttonRegisterOnClick(View v) {
        Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(regIntent);
    }
}
