package com.codesoom.assignment.models;

import com.sun.net.httpserver.HttpExchange;

public class Response {
    private HttpExchange exchange;
    private String content;
    private StatusCode statusCode;

    public Response(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public Response() {
    }

    public HttpExchange getExchange() {
        return exchange;
    }

    public void setExchange(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
