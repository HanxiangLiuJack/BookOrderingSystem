package com.example.ding.umutos.presentation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.ding.umutos.R;
import com.example.ding.umutos.application.Main;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.objects.Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        copyDatabaseToDevice();
    }

    public void buttonLoginOnClick(View v) {
        EditText userNameText = (EditText)findViewById(R.id.loginUserName);
        EditText pswText = (EditText)findViewById(R.id.loginPassword);

        String userName=userNameText.getText().toString();
        String psw=pswText.getText().toString();

        AccessAccounts accounts = new AccessAccounts();

        Account newAcc = accounts.Login(userName,psw);

        if(newAcc==null){
            showDialog();
        }else{
            userName = newAcc.getUserName();
            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            homeIntent.putExtra("userName", userName);
            LoginActivity.this.startActivity(homeIntent);
        }
    }

    public void buttonRegisterOnClick(View v) {
        Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(regIntent);
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert:")
                .setMessage("\nPlease input valid user name or password!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                })
                .show();
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();
        try {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }
            copyAssetsToDirectory(assetNames, dataDirectory);
            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (final IOException ioe) {
            System.out.println("Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()){

                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }

            BufferedReader br = new BufferedReader(new FileReader(outFile));

            String st;
            while ((st = br.readLine()) != null)
                System.out.println(st);
        }
    }

}
