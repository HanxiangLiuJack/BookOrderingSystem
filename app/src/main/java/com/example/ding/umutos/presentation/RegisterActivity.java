package com.example.ding.umutos.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ding.umutos.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void buttonRegCancel(View v) {
        finish();
    }

    public void buttonRegSubmit(View v) {
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        RegisterActivity.this.startActivity(intent);
    }
}
