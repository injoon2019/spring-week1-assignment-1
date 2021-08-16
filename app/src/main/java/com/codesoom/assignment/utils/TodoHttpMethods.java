package com.codesoom.assignment.utils;

import com.codesoom.assignment.models.Response;
import com.codesoom.assignment.models.StatusCode;
import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.TaskIdGenerator;
import com.codesoom.assignment.models.Title;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

import static com.codesoom.assignment.utils.TodoHttpHandlerUtils.getId;
import static com.codesoom.assignment.utils.TodoHttpHandlerUtils.toTitle;
import static com.codesoom.assignment.utils.TodoHttpHandlerUtils.toTask;
import static com.codesoom.assignment.utils.TodoHttpHandlerUtils.taskToJSON;
import static com.codesoom.assignment.utils.TodoHttpHandlerUtils.tasksToJSON;

public class TodoHttpMethods {

    private static final String URI_WITHOUT_PARAMETERS = "/tasks";
    private String content = "";

    public Response handleDeleteMethod(String path, HttpExchange exchange, Map<Long, Task> taskMap) throws IOException {
        Long id = getId(path);
        Task task = taskMap.get(id);
        Response response = new Response(exchange);

        if (task == null) {
            response.setContent(content);
            response.setStatusCode(StatusCode.NOT_FOUND);
            return response;
        }

        taskMap.remove(id);
        content = "";

        response.setContent(content);
        response.setStatusCode(StatusCode.NOT_FOUND);
        return response;
    }

    public Response handlePatchMethod(String path, HttpExchange exchange, String body, Map<Long, Task> taskMap) throws IOException {
        Long id = getId(path);
        Task task = taskMap.get(id);
        Response response = new Response(exchange);

        if (task == null) {
            response.setContent(content);
            response.setStatusCode(StatusCode.NOT_FOUND);
            return response;
        }
        Title title = toTitle(body);
        task.setTitle(title.getTitle());
        taskMap.put(id, task);

        content = taskToJSON(task);
        response.setContent(content);
        response.setStatusCode(StatusCode.NOT_FOUND);
        return response;
    }

    public Response handlePutMethod(String path, HttpExchange exchange, String body, Map<Long, Task> taskMap) throws IOException {
        Long id = getId(path);
        Task task = taskMap.get(id);
        Response response = new Response(exchange);

        if (task == null) {
            response.setContent(content);
            response.setStatusCode(StatusCode.NOT_FOUND);
            return response;
        }

        Task changeTask = toTask(body);
        task.setTitle(changeTask.getTitle());
        taskMap.put(id, task);

        content = taskToJSON(task);
        response.setContent(content);
        response.setStatusCode(StatusCode.NOT_FOUND);
        return response;
    }

    public Response handlePostMethodWithParameter(HttpExchange exchange, String body, Map<Long, Task> taskMap) throws IOException {
        Task task = toTask(body);
        taskMap.put(task.getId(), task);
        Response response = new Response(exchange);

        Long lastSequence = TaskIdGenerator.getLastSequence();
        Task lastTask = taskMap.get(lastSequence);

        content = taskToJSON(lastTask);
        response.setContent(content);
        response.setStatusCode(StatusCode.NOT_FOUND);
        return response;
    }

    public Response handleBasicGetMethod(String path, HttpExchange exchange, Map<Long, Task> taskMap) throws IOException {
        Response response = new Response(exchange);

        if (URI_WITHOUT_PARAMETERS.equals(path)) {
            content = tasksToJSON(taskMap);
            response.setContent(content);
            response.setStatusCode(StatusCode.NOT_FOUND);
            return response;
        }

        Long id = getId(path);
        Task task = taskMap.get(id);

        if (task == null) {
            response.setContent(content);
            response.setStatusCode(StatusCode.NOT_FOUND);
            return response;
        }

        content = taskToJSON(task);
        response.setContent(content);
        response.setStatusCode(StatusCode.NOT_FOUND);
        return response;
    }
}
