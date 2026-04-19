package com.tushar.User.Service.Exceptiom;

import com.tushar.User.Service.Payload.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
   public ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserException ex , WebRequest request){
       ExceptionResponse response = new ExceptionResponse(
               ex.getMessage(),
               request.getDescription(false),
                LocalDateTime.now().toString()
       );
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
   }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
                .orElse("Validation failed");

        ExceptionResponse response = new ExceptionResponse(
                message,
                request.getDescription(false),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
   }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception ex , WebRequest request){
        ExceptionResponse response = new ExceptionResponse(
                "Internal server error",
                request.getDescription(false),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
   }
}