package com.example.ding.umutos.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.example.ding.umutos.application.Main;

public class TestUtils {
    private static final File DB_SRC = new File("src/main/assets/db/SC.script");
    private static final File DB_DST = new File("src/main/assets/tempdb/SC.script");
    private static final File DB_PROPERTY = new File("src/main/assets/tempdb/SC.properties");

    public static File copyDB() throws IOException {
        InputStream input;
        OutputStream output;
        input = new FileInputStream(DB_SRC);
        output =  new FileOutputStream(DB_DST);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        input.close();
        output.close();
        Main.setDBPathName("src/main/assets/tempdb/SC");
        return DB_DST;
    }



    public static void delete() {
        DB_DST.delete();
        DB_PROPERTY.delete();
    }


}
