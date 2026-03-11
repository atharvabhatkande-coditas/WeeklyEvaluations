package com.example.SbHibernate.Exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationResponse<T> {
    private T data;
    private List<ErrorResponse> error;

    public ApplicationResponse(T data, List<ErrorResponse> error) {
        this.data = data;
        this.error = error;
    }

    public ApplicationResponse(List<ErrorResponse> error) {
        this.error = error;
    }

    public ApplicationResponse(T data) {
        this.data = data;
    }
}
