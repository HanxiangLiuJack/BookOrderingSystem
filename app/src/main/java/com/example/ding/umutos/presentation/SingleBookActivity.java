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

    private TextView bookTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlebook);
//        int lv_item_id = intent.getIntExtra("lv_item_id", 0);
//        String aaa=""+lv_item_id;
//        Log.v("aaa",aaa);



//        bookTitle = (TextView)findViewById(R.id.singleBookTitle);
//        bookTitle.setText(lv_item_id);

    }



    public void buttonAdd(View view) {
        showDialog("a");



    }

    private void showDialog(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+title+" has been added.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
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
