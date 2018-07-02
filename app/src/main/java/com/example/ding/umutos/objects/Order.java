package com.example.ding.umutos.objects;

public class Order {
    private Book orderedBook;
    private Account buyer;
    private Account seller;
    private String buyerFirstName;
    private String buyerLastName;
    private String postalCode;
    private String buyerPhoneNumber;
    private String address;

    public Order( Book orderedBook,
                  Account buyer,
                  Account seller,
                  String buyerFirstName,
                  String buyerLastName,
                  String postalCode,
                  String buyerPhoneNumber,
                  String address)
    {
      this.orderedBook = orderedBook;
      this.buyer = buyer;
      this.seller = seller;
      this.buyerFirstName = buyerFirstName;
      this.buyerLastName  = buyerLastName;
      this.postalCode = postalCode;
      this.buyerPhoneNumber = buyerPhoneNumber;
      this.address = address;

    }


    public Account getBuyer() {
        return buyer;
    }

    public Account getSeller() {
        return seller;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    public Book getOrderedBook() {
        return orderedBook;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }
   //setters
    public void setAddress(String address) {
        this.address = address;
    }

    public void setBuyer(Account buyer) {
        this.buyer = buyer;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }

    public void setOrderedBook(Book orderedBook) {
        this.orderedBook = orderedBook;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setSeller(Account seller) {
        this.seller = seller;
    }
}
