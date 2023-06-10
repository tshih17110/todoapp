package com.application.todoapp.services;

import com.application.todoapp.models.Task;
import com.application.todoapp.models.Task.Progress;
import com.application.todoapp.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.getById(id);
    }

    public List<Task> findByTaskProgress(Progress taskProgress) {
        return taskRepository.findByTaskProgress(taskProgress);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Task editTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteAll() {
        taskRepository.deleteAll();
    }
    
}
