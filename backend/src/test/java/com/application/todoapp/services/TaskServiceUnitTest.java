package com.application.todoapp.services;

import com.application.todoapp.models.Task;
import com.application.todoapp.models.Task.Progress;
import com.application.todoapp.repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceUnitTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNewTask() {
        Task task = new Task(1L, "Sample Task", Progress.INCOMPLETE);
        when(taskRepository.save(task)).thenReturn(task);
        Task result = taskService.createNewTask(task);
        assertEquals(task, result);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testGetAllTask() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task 1", Progress.INCOMPLETE));
        tasks.add(new Task(2L, "Task 2", Progress.COMPLETE));
        when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> result = taskService.getAllTask();
        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testFindTaskById() {
        Long taskId = 1L;
        Task task = new Task(taskId, "Task 1", Progress.INCOMPLETE);
        when(taskRepository.getById(taskId)).thenReturn(task);
        Task result = taskService.findTaskById(taskId);
        assertEquals(task, result);
        verify(taskRepository, times(1)).getById(taskId);
    }

    @Test
    void testFindByTaskProgress() {
        Progress taskProgress = Progress.INCOMPLETE;
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task 1", taskProgress));
        tasks.add(new Task(2L, "Task 2", taskProgress));
        when(taskRepository.findByTaskProgress(taskProgress)).thenReturn(tasks);
        List<Task> result = taskService.findByTaskProgress(taskProgress);
        assertEquals(tasks, result);
        verify(taskRepository, times(1)).findByTaskProgress(taskProgress);
    }

    @Test
    void testDeleteTask() {
        Task task = new Task(1L, "Task 1", Progress.INCOMPLETE);
        taskService.deleteTask(task);
        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    void testDeleteById() {
        Long taskId = 1L;
        taskService.deleteById(taskId);
        verify(taskRepository, times(1)).deleteById(taskId);
    }
       

}
