package br.com.martins.messengerMicrosservice.response;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(
            HttpStatus httpStatus, Object responseObject) {
        Map<String, Object> response = new HashMap<>();
        response.put("httpStatus", httpStatus);
        response.put("dataTime", new Timestamp(System.currentTimeMillis()));
        response.put("data", responseObject);

        return new ResponseEntity<>(response, httpStatus);
    }
}
