package com.example.ding.umutos.objects;

public class OrderInfo {

    private String firstName;
    private String lastName;
    private String postCode;
    private String phoneNumber;
    private String address;

    public OrderInfo(String firstName,String lastName,String postCode,String phoneNumber,String address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
