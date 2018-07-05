package com.example.ding.umutos.objects;

public class Order {
    private String bookName;
    private int buyerID;
    private int sellerID;
    private double price;
    private String buyerFirstName;
    private String buyerLastName;
    private String postCode;
    private String phoneNumber;
    private String address;

    public Order(String bookName, int buyerID, int sellerID, double price,  String[] orderInfo)
    {
        this.bookName = bookName;
        this.buyerID = buyerID;
        this.sellerID = sellerID;
        this.price = price;
        buyerFirstName = orderInfo[0];
        buyerLastName = orderInfo[1];
        postCode = orderInfo[2];
        phoneNumber = orderInfo[3];
        address = orderInfo[4];
    }

    public String getBookName()
    {
        return bookName;
    }

    public int getBuyerID()
    {
        return buyerID;
    }

    public int getSellerID() {
        return sellerID; }

    public double getPrice(){
        return price; }

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
