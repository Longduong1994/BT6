package bt6.advice;

import bt6.exception.ConfirmException;
import bt6.exception.ExistException;
import bt6.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> invalidRegister(MethodArgumentNotValidException ex) {
        Map<String, String> error = new HashMap();
        ex.getBindingResult().getFieldErrors().forEach(c -> {
            error.put(c.getField(), c.getDefaultMessage());
        });
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFound(NotFoundException notFoundException) {
        return new ResponseEntity<>(notFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ExistException.class)
    public ResponseEntity<String> notFound(ExistException existException) {
        return new ResponseEntity<>(existException.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConfirmException.class)
    public ResponseEntity<String> notFound(ConfirmException confirmException) {
        return new ResponseEntity<>(confirmException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
