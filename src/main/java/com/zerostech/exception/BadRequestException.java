package com.zerostech.exception;

/**
 * Created by gjason on 2017/3/2.
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

}
