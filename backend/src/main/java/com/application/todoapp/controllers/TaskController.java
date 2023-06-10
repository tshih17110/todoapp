package com.application.todoapp.controllers;

import com.application.todoapp.models.Task;
import com.application.todoapp.models.Task.Progress;
import com.application.todoapp.services.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}) 
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("TodoApp - Welcome!");
    }

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping("/progress")
    public ResponseEntity<List<Task>> getByTaskProgress(Progress taskProgress) {
        return ResponseEntity.ok(taskService.findByTaskProgress(taskProgress));
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createNewTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(taskService.editTask(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAllTasks() {
        taskService.deleteAll();
        return ResponseEntity.ok("All tasks have been deleted");
    }
}
