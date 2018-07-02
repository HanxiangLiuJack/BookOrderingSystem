package com.example.ding.umutos.objects;

public class Order {

    private Book orderBook;
    private Account buyer;
    private Account seller;
    private String buyerFirstName;
    private String buyerLastName;
    private String postCode;
    private String phoneNumber;
    private String address;

    public Order(Book orderBook, Account buyer, Account seller, String[] orderInfo)
    {
        this.orderBook = orderBook;
        this.buyer = buyer;
        this.seller = seller;
        buyerFirstName = orderInfo[0];
        buyerLastName = orderInfo[1];
        postCode = orderInfo[2];
        phoneNumber = orderInfo[3];
        address = orderInfo[4];
    }

    public Book getOrderBook()
    {
        return orderBook;
    }

    public Account getBuyer()
    {
        return buyer;
    }

    public Account getSeller()
    {
        return seller;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
