package com.TinyTasks.TinyTasks.controller;

import com.TinyTasks.TinyTasks.entity.Task;
import com.TinyTasks.TinyTasks.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAll();
    }

    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody Task task){ // Es un objeto que representa la respuesta HTTP
        try {
            Task savedTask = taskService.create(task);
            return ResponseEntity.ok(savedTask);
        } catch (IllegalArgumentException e) {
            // En caso de error de validación, puedes retornar un Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // O puedes incluir un mensaje de error en el cuerpo de la respuesta
        } catch (Exception e) {
            // En caso de otros errores generales, puedes retornar un Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<Task> toggleTask(@PathVariable int id) {
        try {
            Task updatedTask = taskService.toggle(id);
            return ResponseEntity.ok(updatedTask);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        try {
            taskService.delete(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
