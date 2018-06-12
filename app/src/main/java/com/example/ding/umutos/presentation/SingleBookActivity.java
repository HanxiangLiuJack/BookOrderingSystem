package com.example.ding.umutos.presentation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;


import com.example.ding.umutos.R;


public class SingleBookActivity extends AppCompatActivity {

    private int bookID;
    private TextView bookTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlebook);
        bookID = getIntent().getIntExtra("bookID",-1);


        bookTitle = (TextView)findViewById(R.id.singleBookTitle);
        bookTitle.setText(""+bookID);

    }



    public void buttonAdd(View view) {
        showDialog("a");
    }

    private void showDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+"Sure to buy "+title+"? Press 'Yes' to Delivery Info page.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(SingleBookActivity.this,AddressActivity.class);
                        intent.putExtra("bookID", bookID);
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
