package com.codesoom.assignment.utils;

import com.codesoom.assignment.models.Task;
import com.codesoom.assignment.models.Title;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class TodoHttpHandlerUtilsTest {

    private Map<Long, Task> tasks = new HashMap<>();

    @BeforeEach
    public void cleanTasks() {
        tasks.clear();;
    }

    @Test
    @DisplayName("Http body에 온 content를 Title로 반환한다")
    void toTitle() throws JsonProcessingException {
        // given
        String content = "{\"title\" : \"과제하기\"}";

        // when
        Title title = TodoHttpHandlerUtils.toTitle(content);

        // then
        Assertions.assertEquals("과제하기", title.getTitle());
    }

    @Test
    @DisplayName("URL에서 id 파라미터를 추출한다")
    void getId() {
        // given
        Long id = 100L;
        String path = "localhost:8000/tasks/100";

        // when
        Long resultId = TodoHttpHandlerUtils.getId(path);

        // then
        Assertions.assertEquals(id, resultId);
    }

    @Test
    @DisplayName("문자열을 Task 객체로 변환한다")
    void toTask() throws JsonProcessingException {
        // given
        String content = "{\"title\" : \"과제하기\"}";

        // when
        Task task = TodoHttpHandlerUtils.toTask(content);

        // then
        Assertions.assertTrue("과제하기".equals(task.getTitle()));
    }
}
