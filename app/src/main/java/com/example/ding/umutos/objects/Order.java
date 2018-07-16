package com.example.ding.umutos.objects;

public class Order {
    private String bookName;
    private String buyerName;
    private String sellerName;
    private double price;
    private OrderInfo orderInfo;

    public Order(String bookName, String buyerName, String sellerName, double price, OrderInfo orderInfo )
    {
        this.bookName = bookName;
        this.buyerName = buyerName;
        this.sellerName = sellerName;
        this.price = price;
        this.orderInfo = orderInfo;
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
        return orderInfo.getFirstName();
    }

    public String getBuyerLastName() {
        return orderInfo.getLastName();
    }

    public String getPostCode() {
        return orderInfo.getPostCode();
    }

    public String getPhoneNumber() {
        return orderInfo.getPhoneNumber();
    }

    public String getAddress() {
        return orderInfo.getAddress();
    }

    public void setFirstName(String buyerFirstName){
        this.orderInfo.setFirstName(buyerFirstName);;
    }

    public void setLastName(String buyerLastName)
    {
        this.orderInfo.setLastName(buyerLastName);;
    }

    public void setPostCode(String postCode)
    {
        this.orderInfo.setPostCode(postCode);;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.orderInfo.setPhoneNumber(phoneNumber);;
    }

    public void setAddress(String address)
    {
        this.orderInfo.setAddress(address);;
    }


}
