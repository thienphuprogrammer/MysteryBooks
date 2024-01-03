package com.example.mysterybook.utils;

public class ValidationUtil {
    public static boolean isEmailValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
    public static boolean isPasswordValid(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    public static boolean isUsernameValid(String name) {
        String regex = "^[a-zA-Z\\s]*$";
        return name.matches(regex);
    }

    public static boolean isPhoneValid(String phone) {
        String regex = "^[0-9]{10}$";
        return phone.matches(regex);
    }

    public static boolean isAddressValid(String address) {
        String regex = "^[a-zA-Z0-9\\s]*$";
        return address.matches(regex);
    }
}
