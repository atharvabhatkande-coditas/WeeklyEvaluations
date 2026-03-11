package com.example.SbHibernate.Exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    LocalDateTime dateTime;
    private int statusCode;
    private List<String>validationErrors;

    public ErrorResponse(String message, LocalDateTime dateTime, int statusCode) {
        this.message = message;
        this.dateTime = dateTime;
        this.statusCode = statusCode;
    }


}
