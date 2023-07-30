package com.crud.client.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class BaseCustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageModel> handleValidationException(MethodArgumentNotValidException ex , HttpServletRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<String> errorMessages = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            errorMessages.add(fieldError.getDefaultMessage());
        }

        ErrorMessageModel errorMessageModel = new ErrorMessageModel();
        errorMessageModel.setTimestamp(LocalDateTime.now());
        errorMessageModel.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessageModel.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorMessageModel.setMessage("Validation Error");
        errorMessageModel.setErrors(String.join(",", errorMessages));
        errorMessageModel.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageModel);
    }


    @ExceptionHandler(DuplicateResourceException.class)
    protected ResponseEntity<Object> handleDuplicateResourceException(DuplicateResourceException ex ,  HttpServletRequest request) {
        ErrorMessageModel errorMessageModel = new ErrorMessageModel();
        errorMessageModel.setTimestamp(LocalDateTime.now());
        errorMessageModel.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessageModel.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorMessageModel.setMessage("Conflict Error");


            if (ex.getMessage().contains("client_national_id_unique")) {
                errorMessageModel.setErrors("رقم القومي مسجل من قبل");
            }

        errorMessageModel.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageModel);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageModel> handleNotFoundException(NotFoundException e ,  HttpServletRequest request) {
        ErrorMessageModel errorMessageModel = new ErrorMessageModel();
        errorMessageModel.setTimestamp(LocalDateTime.now());
        errorMessageModel.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessageModel.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorMessageModel.setMessage("Not found Error");
        errorMessageModel.setErrors(e.getMessage());
        errorMessageModel.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageModel);

    }


    @ExceptionHandler(DataHasNotChangedException.class)
    public ResponseEntity<ErrorMessageModel> handleDataDidNotChangeException(DataHasNotChangedException e , HttpServletRequest request) {
        ErrorMessageModel errorMessageModel = new ErrorMessageModel();
        errorMessageModel.setTimestamp(LocalDateTime.now());
        errorMessageModel.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessageModel.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorMessageModel.setMessage("Data has not changed");
        errorMessageModel.setErrors(e.getMessage());
        errorMessageModel.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageModel);

    }

}