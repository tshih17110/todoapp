package com.application.todoapp.models;

import com.application.todoapp.models.Task.Progress;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskUnitTest {

    @Test
    void testTaskModel() {
        Long taskId = 1L;
        String taskName = "Sample Task";
        Progress taskProgress = Progress.INCOMPLETE;
        Task task = new Task(taskId, taskName, taskProgress);

        assertEquals(taskId, task.getId());
        assertEquals(taskName, task.getTask());
        assertEquals(taskProgress, task.getTaskProgress());
    }

    @Test
    void testEnumProgress() {
        assertEquals("INCOMPLETE", Progress.INCOMPLETE.name());
        assertEquals("ONGOING", Progress.ONGOING.name());
        assertEquals("COMPLETE", Progress.COMPLETE.name());
    }
}

