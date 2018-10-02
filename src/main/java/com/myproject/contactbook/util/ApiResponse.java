package com.myproject.contactbook.util;

import com.google.common.collect.ImmutableMap;
import com.myproject.contactbook.pojo.response.ListResponse;
import com.myproject.contactbook.pojo.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiResponse<T> {

    @Autowired
    private LoggerUtil loggerUtil;
    @Autowired
    private Serializer serializer;

    public ResponseEntity<?> ok(BodyWrapper body, HttpStatus status) {
        Map<Object, Object> responseBody = generateDefault();
        Map<Object, Object> bodyData = body.getBody();

        if (bodyData.containsKey("data")) {
            responseBody.put("data", body.get("data"));
            responseBody.put("metadata", body.get("metadata"));
            responseBody.put("links", body.get("links"));
        } else {
            responseBody.put("data", body.getBody());
        }

        return new ResponseEntity<>(responseBody, status);
    }

    public ResponseEntity<?> error(T errorPayload, HttpStatus status) {
        loggerUtil.error(ImmutableMap.of(
                "statusCode", status.value(),
                "message", serializer.serialize(errorPayload)
        ));
        return new ResponseEntity<>(errorPayload, status);
    }

    private Map<Object, Object> generateDefault() {

        return new HashMap<>();
    }

    public ResponseEntity<?> ok(ListResponse response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> ok(Response response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> send(ListResponse response) {
        if (response.getError() != null) {

            return error((T) response, response.getError().getStatus());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    public ResponseEntity<?> send(Response response) {
        if (response.getError() != null) {
            return error((T) response, response.getError().getStatus());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
