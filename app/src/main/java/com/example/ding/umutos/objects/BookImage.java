package com.example.ding.umutos.objects;

import com.example.ding.umutos.R;

public class BookImage {
    private int[] bookImg;

    public BookImage(){
        bookImg =  new int[]{R.mipmap.book0, R.mipmap.book1, R.mipmap.book2, R.mipmap.book3, R.mipmap.book4, R.mipmap.book5, R.mipmap.book6, R.mipmap.book7, R.mipmap.book8, R.mipmap.book9, R.mipmap.book10};
    }

    public int getImageByBookID(int bookID){
        if(bookID>10)
            return bookImg[0];
        else
            return bookImg[bookID];
    }


}

