package com.example.ding.umutos.presentation;
import java.util.List;
import java.util.ArrayList;

import com.example.ding.umutos.business.AccessAccounts;
import com.example.ding.umutos.objects.Account;
import com.example.ding.umutos.objects.Book;

public class BookSorter {
    AccessAccounts accessAccounts = new AccessAccounts(  );

    public List<Book> LowPrice(List<Book> books){

        for(int i=0;i<books.size();i++){
            Double price = books.get(i).getPrice();
            Book key=books.get(i);
            int j=i-1;
            while(j>=0 && books.get(j).getPrice()>price){
                books.set(j+1,books.get(j));
                j=j-1;
            }
            books.set(j+1,key);
        }
        return books;
    }

    public List<Book> HighPrice(List<Book> books){


        for(int i=0;i<books.size();i++){
            Double price=books.get(i).getPrice();
            Book key=books.get(i);
            int j=i-1;
            while(j>=0 && books.get(j).getPrice()<price){
                books.set(j+1,books.get(j));
                j=j-1;
            }
            books.set(j+1,key);
        }
        return books;
    }

    public List<Book> HighRate(List<Book> books){
        List<Account> accounts=accessAccounts.getAccounts();

        for(int i=0;i<books.size();i++){
            double rate1=0;
            double rate2 = 0;
            int j=i-1;
            for (int z=0;z<accounts.size();z++){
                if(accounts.get( z ).getUserName().equals( books.get( i ).getOwner() ))
                {
                    rate1 = accounts.get( z ).getRate();
                    break;
                }
            }
            Book key=books.get(i);

            while(j>=0){
                 for(int a = 0; a < accounts.size();a++)
                 {
                     if(accounts.get( a ).getUserName().equals( books.get(j).getOwner() )){
                         rate2 = accounts.get( a ).getRate();
                         break;
                     }
                 }

                if(rate2<rate1){
                    books.set( j + 1, books.get( j ) );
                    j = j - 1;
                }else{
                    break;
                }
            }
            books.set(j+1,key);
        }
        return books;
    }

    public List<Book> LowRate(List<Book> books){
        List<Account> accounts=accessAccounts.getAccounts();

        for(int i=0;i<books.size();i++){
            double rate1=0;
            double rate2 = 0;
            int j=i-1;
            for (int z=0;z<accounts.size();z++){
                if(accounts.get( z ).getUserName().equals( books.get( i ).getOwner() ))
                {
                    rate1 = accounts.get( z ).getRate();
                    break;
                }
            }
            Book key=books.get(i);

            while(j>=0){
                for(int a = 0; a < accounts.size();a++)
                {
                    if(accounts.get( a ).getUserName().equals( books.get(j).getOwner() )){
                        rate2 = accounts.get( a ).getRate();
                        break;
                    }
                }

                if(rate2>rate1){
                    books.set( j + 1, books.get( j ) );
                    j = j - 1;
                }else{
                    break;
                }
            }
            books.set(j+1,key);
        }
        return books;
    }
}