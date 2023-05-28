package com.application.todoapp.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.application.todoapp.TodoappApplication;
import com.application.todoapp.models.Task;
import com.application.todoapp.models.Task.Progress;
import com.application.todoapp.services.TaskService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = TaskController.class)
@ContextConfiguration(classes = {TodoappApplication.class})
public class TaskControllerUnitTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        Task task1 = new Task(1L, "Task 1", Progress.INCOMPLETE);
        Task task2 = new Task(2L, "Task 2", Progress.ONGOING);
        Task task3 = new Task(3L, "Task 3", Progress.COMPLETE);    
        List<Task> tasks = Arrays.asList(task1, task2, task3);

        when(taskService.getAllTask()).thenReturn(tasks);

        ResponseEntity<List<Task>> response = taskController.getAllTasks();
        assertEquals(tasks, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(taskService, times(1)).getAllTask();
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void testGetByTaskProgress() {
        Progress progress = Progress.INCOMPLETE;
        Task task1 = new Task(1L, "Task 1", Progress.INCOMPLETE);
        Task task2 = new Task(2L, "Task 2", Progress.ONGOING);
        Task task3 = new Task(3L, "Task 3", Progress.COMPLETE);    

        List<Task> tasks = Arrays.asList(task1, task2, task3);

        when(taskService.findByTaskProgress(progress)).thenReturn(tasks);

        ResponseEntity<List<Task>> response = taskController.getByTaskProgress(progress);
        assertEquals(tasks, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(taskService, times(1)).findByTaskProgress(progress);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void testCreateTask() {
        Task task = new Task(null, "New Task", Progress.INCOMPLETE);

        when(taskService.createNewTask(task)).thenReturn(task);

        ResponseEntity<Task> response = taskController.createTask(task);
        assertEquals(task, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(taskService, times(1)).createNewTask(task);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void testUpdateTask() {
        Long taskId = 1L;
        Task task = new Task(taskId, "Updated Task", Progress.ONGOING);

        when(taskService.editTask(task)).thenReturn(task);

        ResponseEntity<Task> response = taskController.updateTask(taskId, task);
        assertEquals(task, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(taskService, times(1)).editTask(task);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void testDeleteTask() {
        Long taskId = 1L;

        ResponseEntity<Boolean> response = taskController.deleteTask(taskId);
        assertEquals(true, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(taskService, times(1)).deleteById(taskId);
        verifyNoMoreInteractions(taskService);
    }

}
