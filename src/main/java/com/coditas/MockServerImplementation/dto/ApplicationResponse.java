package com.coditas.MockServerImplementation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ApplicationResponse <T>{

    private T data;
    private List<ErrorResponse> errorResponseList;

    public ApplicationResponse(T data) {
        this.data = data;
    }

    public ApplicationResponse(List<ErrorResponse> errorResponseList) {
        this.errorResponseList = errorResponseList;
    }

    public ApplicationResponse(T data, List<ErrorResponse> errorResponseList) {
        this.data = data;
        this.errorResponseList = errorResponseList;
    }
}
