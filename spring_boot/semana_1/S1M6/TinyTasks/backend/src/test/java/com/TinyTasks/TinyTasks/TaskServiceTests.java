package com.TinyTasks.TinyTasks;

import com.TinyTasks.TinyTasks.entity.Task;
import com.TinyTasks.TinyTasks.repository.TaskRepository;
import com.TinyTasks.TinyTasks.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTests {

    private TaskService taskService;

    @BeforeEach
    void setup() {
        taskService = new TaskService(new TaskRepository());
    }

    @Test
    void createTask_withValidTitle_shouldReturnTask() {
        Task created = taskService.create(new Task(0, "Learn JUnit"));
        assertNotNull(created);
        assertEquals("Learn JUnit", created.getTitle());
        assertFalse(created.isDone());
        assertEquals(1, taskService.getAll().size());
    }

    @Test
    void createTask_withInvalidTitle_shouldThrowException() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                taskService.create(new Task(0, "Hi"))
        );
        assertEquals("Title is required", ex.getMessage());
    }

    @Test
    void toggleTask_shouldChangeDoneState() {
        Task task = taskService.create(new Task(0, "Test toggle"));
        boolean before = task.isDone();

        Task toggled = taskService.toggle(task.getId());
        assertNotEquals(before, toggled.isDone());
    }

    @Test
    void toggleTask_withInvalidId_shouldThrowException() {
        assertThrows(NoSuchElementException.class, () ->
                taskService.toggle(999)
        );
    }

    @Test
    void deleteTask_shouldRemoveFromList() {
        Task task = taskService.create(new Task(0, "Delete me"));
        taskService.delete(task.getId());
        assertTrue(taskService.getAll().isEmpty());
    }

    @Test
    void deleteTask_withInvalidId_shouldThrowException() {
        assertThrows(NoSuchElementException.class, () ->
                taskService.delete(404)
        );
    }

}
