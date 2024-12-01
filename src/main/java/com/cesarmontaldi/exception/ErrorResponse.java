package com.cesarmontaldi.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse(String timeStamp, String message, int status, String path, List<ErrorField> errors) {

}
