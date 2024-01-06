package org.coursesjava.glovojava.utilit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseHandler {
    public static ResponseEntity<?> response(String message, HttpStatus httpStatus) {
        Map<String, Object> responseStorage = new HashMap<>();
        responseStorage.put("message", message);
        responseStorage.put("status", httpStatus);
        return new ResponseEntity<>(responseStorage, httpStatus);
    }
}
