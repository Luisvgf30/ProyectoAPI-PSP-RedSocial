package com.example.redsocial.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdvice {
  
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public String handleRequestBodyTypeMismatchExceptions(HttpMessageNotReadableException ex) {
    return ex.getMessage();
  }

  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public String handleExceptions(Exception ex) {
    return ex.getMessage();
  }

  /*@ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public String handleParamTypeMismatchExceptions(MethodArgumentTypeMismatchException ex) {
    String respuesta = ex.getPropertyName() + " debe ser del tipo " + ex.getRequiredType();
    return respuesta;
  }

  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleRequestBodyValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }*/

}