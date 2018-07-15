package com.example.ding.umutos.objects;

public class Order {
    private String bookName;
    private String buyerName;
    private String sellerName;
    private double price;
    private String buyerFirstName;
    private String buyerLastName;
    private String postCode;
    private String phoneNumber;
    private String address;

    public Order(String bookName, String buyerName, String sellerName, double price )
    {
        this.bookName = bookName;
        this.buyerName = buyerName;
        this.sellerName = sellerName;
        this.price = price;
    }

    public String getBookName()
    {
        return bookName;
    }

    public String getBuyerName()
    {
        return buyerName;
    }

    public String  getSellerName() {
        return sellerName;
    }

    public double getPrice(){
        return price;
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
