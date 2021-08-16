package com.codesoom.assignment;

import com.codesoom.assignment.models.HttpMethod;
import com.codesoom.assignment.models.Response;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.utils.TodoHttpMethods;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.codesoom.assignment.utils.TodoHttpHandlerUtils.writeOutputStream;

public class TodoHttpHandler implements HttpHandler {
    private final TodoHttpMethods todoHttpMethods = new TodoHttpMethods();
    private Map<Long, Task> taskMap = new ConcurrentHashMap<>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        final String method = exchange.getRequestMethod();
        final String path = exchange.getRequestURI().getPath();
        final InputStream inputStream = exchange.getRequestBody();
        final String body = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining("\n"));

        Response response = new Response();
        switch (HttpMethod.convert(method)) {
            case GET:
                response = todoHttpMethods.handleBasicGetMethod(path, exchange, taskMap);
            case POST:
                response = todoHttpMethods.handlePostMethodWithParameter(exchange, body, taskMap);
                break;
            case PUT:
                response = todoHttpMethods.handlePutMethod(path, exchange, body, taskMap);
                break;
            case PATCH:
                response = todoHttpMethods.handlePatchMethod(path, exchange, body, taskMap);
                break;
            case DELETE:
                response = todoHttpMethods.handleDeleteMethod(path, exchange, taskMap);
                break;
        }

        writeOutputStream(response);
    }
}
