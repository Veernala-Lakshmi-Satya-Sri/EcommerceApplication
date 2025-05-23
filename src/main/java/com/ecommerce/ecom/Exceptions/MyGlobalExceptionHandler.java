package com.ecommerce.ecom.Exceptions;

import com.ecommerce.ecom.Payload.APIResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        HashMap<String,String> response= new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err -> {  //  e.getBindingResult() retrieve list of all error that are caught during the validation of method parameters
            String fieldName= ((FieldError)err).getField();
            String message=err.getDefaultMessage();
            response.put(fieldName, message);
        });
        return new ResponseEntity<Map<String,String>>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundException(ResourceNotFoundException e) {
        String message= e.getMessage();
        APIResponse apiResponse= new APIResponse(message,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> myAPIExceptionException(APIException e) {
        String message= e.getMessage();

        APIResponse apiResponse= new APIResponse(message,false);

        return new ResponseEntity<APIResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
