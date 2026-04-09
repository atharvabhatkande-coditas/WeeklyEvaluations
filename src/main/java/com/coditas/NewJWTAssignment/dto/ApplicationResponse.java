package com.coditas.NewJWTAssignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse<T> {
    private T data;

    private List<ErrorResponse> errors;

    public ApplicationResponse(List<ErrorResponse> errors) {
        this.errors = errors;
    }

    public ApplicationResponse(T data) {
        this.data = data;
    }

    public HttpStatusCode Ht() {
        return null;
    }
}