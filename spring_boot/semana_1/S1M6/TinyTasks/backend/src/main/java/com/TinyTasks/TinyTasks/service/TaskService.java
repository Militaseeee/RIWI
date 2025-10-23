package com.TinyTasks.TinyTasks.service;

import com.TinyTasks.TinyTasks.entity.Task;
import com.TinyTasks.TinyTasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task create(Task task) {
        if (task == null || task.getTitle().trim().length() < 3) {
            throw new IllegalArgumentException("Title is required");
        }
        return taskRepository.save(task.getTitle());
    }

    public Task toggle(int id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        task.setDone(!task.isDone());
        return task;
    }

    public void delete(int id) {
        boolean removed = taskRepository.delete(id);
        if (!removed) throw new NoSuchElementException("Not found");
    }
}
