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

    public Order(String bookName, int buyerID, int sellerID, double price )
    {
        this.bookName = bookName;
        this.buyerID = buyerID;
        this.sellerID = sellerID;
        this.price = price;
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
        return sellerID;
    }

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

    public void setFirstName(String buyerFirstName){
        this.buyerFirstName=buyerFirstName;
    }

    public void setLastName(String buyerLastName)
    {
        this.buyerLastName=buyerLastName;
    }

    public void setPostCode(String postCode)
    {
        this.postCode=postCode;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber=phoneNumber;
    }

    public void setAddress(String address)
    {
        this.address=address;
    }


}
