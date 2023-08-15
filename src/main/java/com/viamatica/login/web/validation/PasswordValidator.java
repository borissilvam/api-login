package com.viamatica.login.web.validation;

import java.util.regex.Pattern;

public class PasswordValidator {
    public boolean isValidPassword(String password) {

        if (password.length() < 8) {
            return false;
        }


        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return false;
        }


        if (password.contains(" ")) {
            return false;
        }


        if (!Pattern.compile("[!@#$%^&*()-_=+{}\\[\\]|;:'\",.<>/?]").matcher(password).find()) {
            return false;
        }

        return true;
    }
}
