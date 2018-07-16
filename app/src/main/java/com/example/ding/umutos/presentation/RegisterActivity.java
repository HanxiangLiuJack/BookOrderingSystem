package com.example.ding.umutos.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.ding.umutos.R;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.objects.Account;

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
        EditText userNameText = (EditText)findViewById(R.id.registerName);
        EditText pswText = (EditText)findViewById(R.id.registerPsw);

        String userName=userNameText.getText().toString();
        String psw=pswText.getText().toString();

        AccessAccounts accounts = new AccessAccounts();

        Account newAcc = accounts.register(userName,psw);
        if (newAcc==null){
            showDialog();
        }
        else{
            userName=newAcc.getUserName();
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            intent.putExtra("userName", userName);
            RegisterActivity.this.startActivity(intent);
        }
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert:")
                .setMessage("\nPlease input valid user name or password!\n Password should have at least one upper, lower letter and a number.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                })
                .show();
    }
}
