package com.avi.learning.sandbox.model;

import lombok.Data;

@Data
public class Response<T> {

    T data;

    public Response(T data) {
        this.data = data;
    }
}
