package com.avi.learning.sandbox.model;


import lombok.Data;

@Data
public class DummyResponse {

    private String status;

    private String method;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
