package com.ttn.project.customermicroservice.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductIdNotFoundException extends RuntimeException{

    public ProductIdNotFoundException(String message) {
        super(message);
    }
}
