package com.example.ding.umutos.presentation;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.ding.umutos.R;
import com.example.ding.umutos.application.Main;
import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.business.AccessBooks;
import com.example.ding.umutos.objects.Book;
import com.example.ding.umutos.objects.Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        copyDatabaseToDevice();
    }


    public void buttonLoginOnClick(View v) {
        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
        LoginActivity.this.startActivity(homeIntent);
    }

    public void buttonRegisterOnClick(View v) {
        Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(regIntent);
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();
        Log.i("azc",dataDirectory.toString());
        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
                Log.i("azz",assetNames[i]);
            }


            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());
            Log.e("GAGAGA", "Loading db done!!!! " );
            AccessBooks books = new AccessBooks();
            List<Book> bookList = books.getBooks();
            for (int i=0;i<bookList.size();i++){
                System.out.println(bookList.get(i).getDescription());
            }

        } catch (final IOException ioe) {
            Log.v("GAGAGA", "Unable to access application data: " + ioe.getMessage());
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


            outFile.createNewFile();
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
    }
}
