package com.datastax.demo.yadi.s3.web;

import com.datastax.demo.yadi.s3.dto.ErrorResponse;
import com.datastax.demo.yadi.s3.exception.UploadFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    private static final int NUMBER_FORMAT_ERROR = 10;
    private static final int ILLEGAL_ARGUMENT_ERROR = 20;
    private static final int UPLOADED_FAILED_ERROR = 30;

    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse numberFormatExceptionHandler(NumberFormatException exception) {
        return new ErrorResponse(NUMBER_FORMAT_ERROR, "Please input a number argument");
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return new ErrorResponse(ILLEGAL_ARGUMENT_ERROR, "Please input a valid argument");
    }

    @ResponseBody
    @ExceptionHandler(UploadFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse uploadFailedExceptionHandler(UploadFailedException exception) {
        return new ErrorResponse(UPLOADED_FAILED_ERROR, "Asset uploaded failed");
    }
}