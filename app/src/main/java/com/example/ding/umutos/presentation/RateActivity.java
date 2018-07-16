package com.example.ding.umutos.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.TextView;

import com.example.ding.umutos.R;
import com.example.ding.umutos.business.AccessAccounts;

public class RateActivity extends AppCompatActivity {


    private String userName,sellerName;
    private AccessAccounts accessAccounts;
    private int userType;
    private TextView rateInfo;
    private RatingBar rb_normal;
    private float rate=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        userName = getIntent().getStringExtra("userName");
        sellerName = getIntent().getStringExtra("sellerName");
        userType = getIntent().getIntExtra("userType",-1);
        accessAccounts=new AccessAccounts();
        rateInfo=(TextView)findViewById( R.id.rateInfo );
        rateInfo.setText( sellerName+"\nPlease rate the seller." );

        rb_normal = (RatingBar) findViewById(R.id.editRatingBar);
        rb_normal.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast toast = Toast.makeText(RateActivity.this, "Rating:" + String.valueOf(rating), Toast.LENGTH_SHORT);
                toast.setGravity( Gravity.CENTER, 0, 0);
                toast.show();
                rate=rating;
            }
        });
    }

    public void buttonEditRate(View view) {

        if (  rate<0 )
            showDialog();

        else
            showNewDialog();

    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert")
                .setMessage("\n Please select a star!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {}
                })
                .show();
    }

    private void showNewDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmation:")
                .setMessage("\n"+"Sure to rate the seller?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        accessAccounts.RateUser( sellerName,rate );
                        Intent intent = new Intent(RateActivity.this, HistoryActivity.class);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userName", userName);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        finish();
                    }
                })
                .show();
    }

    public void buttonRateCancel(View view) {
        finish();
    }



}
