package com.application.todoapp.repositories;

import com.application.todoapp.models.Task;
import com.application.todoapp.models.Task.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public Task findByTask(String task);
    public List<Task> findByTaskProgress(Progress taskProgress);
    public List<Task> findAll();
    public Task getById(Long id);
}
