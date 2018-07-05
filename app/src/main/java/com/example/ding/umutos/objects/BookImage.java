package com.example.ding.umutos.objects;

import com.example.ding.umutos.R;

public class BookImage {
    private int[] bookImg;

    public BookImage(){
        bookImg =  new int[]{R.mipmap.book0, R.mipmap.book1, R.mipmap.book2, R.mipmap.book3, R.mipmap.book4, R.mipmap.book5, R.mipmap.book6, R.mipmap.book7, R.mipmap.book8, R.mipmap.book9, R.mipmap.book10, R.mipmap.book11, R.mipmap.book12, R.mipmap.book13, R.mipmap.book14, R.mipmap.book15, R.mipmap.book16, R.mipmap.book17, R.mipmap.book18, R.mipmap.book19, R.mipmap.book20};
    }

    public int getImageByBookID(int bookID){
        if(bookID>20)
            return bookImg[0];
        else
            return bookImg[bookID];
    }


}

