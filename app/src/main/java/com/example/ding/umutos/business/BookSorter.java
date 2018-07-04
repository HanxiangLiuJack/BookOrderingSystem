package com.example.ding.umutos.business;


import java.util.List;
import java.util.ArrayList;

import com.example.ding.umutos.objects.Book;

public class BookSorter {


    public List<Book> LowPrice(List<Book> books){
        List<Book> book1=new ArrayList<>();
        for(int i=0;i<books.size();i++){
            book1.add(books.get(i));
        }

        for(int i=0;i<book1.size();i++){
            double price=book1.get(i).getPrice();
            Book key=book1.get(i);
            int j=i-1;
            while(j>=0 && book1.get(j).getPrice()>price){
                book1.set(j+1,book1.get(j));
                j=j-1;
            }
            book1.set(j+1,key);
        }
        return book1;
    }

    public List<Book> HighPrice(List<Book> books){
        List<Book> book1=new ArrayList<>();
        for(int i=0;i<books.size();i++){
            book1.add(books.get(i));
        }

        for(int i=0;i<book1.size();i++){
            double price=book1.get(i).getPrice();
            Book key=book1.get(i);
            int j=i-1;
            while(j>=0 && book1.get(j).getPrice()<price){
                book1.set(j+1,book1.get(j));
                j=j-1;
            }
            book1.set(j+1,key);
        }
        return book1;
    }
}