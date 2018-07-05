package com.example.ding.umutos.business;
import com.example.ding.umutos.objects.Account;
import java.util.List;

public class AccountValidator {

    public boolean validateUserName(String userName, List<Account> db) {
        for(int i = 0; i < db.size(); i++) {
            if(db.get(i).getUserName().equals(userName))
                return false;
        }
        return true;
    }

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
