package com.ttn.project.customermicroservice.Constants;

public interface pattern
{
    static final String EMAIL="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    static final String NAME="[a-zA-Z][a-zA-Z ]*";
    static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$";
    static final String PHONE="^[0-9]{10}$";
    static final String GST="^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$";
    static final String IMG="([^\\s]+(\\.(?i)(jpg|png|jpeg))$)";
}
