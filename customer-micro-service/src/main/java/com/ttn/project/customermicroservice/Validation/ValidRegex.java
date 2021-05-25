package com.ttn.project.customermicroservice.Validation;

public interface ValidRegex {
    final String EMAIL = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    final String GST = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$";
    static final String PHONE="^[0-9]{10}$";
    final String IMAGE= "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)";
}
