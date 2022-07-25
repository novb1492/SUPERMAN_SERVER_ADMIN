package com.kimcompany.jangbogbackendver2.Exception;

import com.kimcompany.jangbogbackendver2.Exception.Exceptions.TokenException;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<?> tokenException(TokenException exception) {
        log.info("tokenException");
        JSONObject response = new JSONObject();
        response.put("message", exception.getDetail());
        return ResponseEntity.status(exception.getHttpStatus().value()).body(response);
    }

}
