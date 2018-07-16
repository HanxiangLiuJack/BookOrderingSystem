package com.example.ding.umutos.business;

public class AccountValidator {

    public boolean validatePassword(String password) {
        boolean hasUpperCaseChar = false;
        boolean hasNumericValue = false;
        boolean hasLowerCaseChar = false;
        for(int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if( Character.isDigit(ch)) {
                hasNumericValue = true;
            } else if (Character.isUpperCase(ch)) {
                hasUpperCaseChar = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowerCaseChar= true;
            }
        }
        return hasUpperCaseChar && hasNumericValue && hasLowerCaseChar;
    }
}
