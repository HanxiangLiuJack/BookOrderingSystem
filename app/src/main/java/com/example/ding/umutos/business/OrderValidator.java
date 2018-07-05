package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Order;
public class OrderValidator {

    public boolean validateOrder(Order order) {
        return validateFirstName(order.getBuyerFirstName()) && validateLastName(order.getBuyerLastName()) &&
                validatePostCode(order.getPostCode()) && validatePhoneNumber(order.getPhoneNumber()) &&
                validateAddress(order.getAddress());
    }

    public boolean validateLastName(String lastName) {
        return lastName.length() <= 20 && lastName.length() > 0;
    }

    public boolean validateFirstName(String firstName) {
        return firstName.length() <= 20 && firstName.length() > 0;
    }

    public  boolean validatePostCode(String postCode) {
        return postCode.length() <=7 && postCode.length() > 0;
    }

    public  boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.length() <=10 && phoneNumber.length() > 0;
    }

    public boolean validateAddress(String address) {
        return address.length() <=100 && address.length() > 0;
    }
}
