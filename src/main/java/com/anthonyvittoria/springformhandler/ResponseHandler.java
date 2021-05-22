package com.anthonyvittoria.springformhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(
            HttpStatus status,
            boolean success,
            String message,
            Object responseObject) {

        Map<String, Object> map = new HashMap<>();
        try {
            map.put("timestamp", new Date());
            map.put("status", status.value());
            map.put("success", success);
            map.put("message", message);
            map.put("data", responseObject);
            return new ResponseEntity<>(map, status);
        } catch (Exception e) {
            map.clear();
            map.put("timestamp", new Date());
            map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            map.put("success", false);
            map.put("message", "Failed to process form (internal server error)");
            map.put("data", null);
            return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
        }
    }
}
