package com.example.ding.umutos.business;

import com.example.ding.umutos.objects.Order;
public class OrderValidator {

    public boolean validateOrder(Order order)
    {
        return validateFirstName(order.getBuyerFirstName()) && validateLastName(order.getBuyerLastName()) &&
                validatePostCode(order.getPostCode()) && validatePhoneNumber(order.getPhoneNumber()) &&
                validateAddress(order.getAddress());
    }

    private boolean validateLastName(String lastName)
    {
        return lastName.length() < 20 && lastName.length() > 0;
    }

    private boolean validateFirstName(String firstName)
    {
        return firstName.length() < 20 && firstName.length() > 0;
    }

    private boolean validatePostCode(String postCode)
    {
        return postCode.length() < 8 && postCode.length() > 0;
    }

    private boolean validatePhoneNumber(String phoneNumber)
    {
        return phoneNumber.length() < 16 && phoneNumber.length() > 0;
    }

    private boolean validateAddress(String address)
    {
        return address.length() < 30 && address.length() > 0;
    }
}
