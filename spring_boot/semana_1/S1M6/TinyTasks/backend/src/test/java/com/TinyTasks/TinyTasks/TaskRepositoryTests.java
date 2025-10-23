package com.TinyTasks.TinyTasks;

import com.TinyTasks.TinyTasks.entity.Task;
import com.TinyTasks.TinyTasks.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TaskRepositoryTests {

    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = new TaskRepository();
    }

    @Test
    void save_shouldAssignUniqueIdsAndStoreTasks() {
        Task t1 = taskRepository.save("Task One");
        Task t2 = taskRepository.save("Task Two");

        assertEquals(1, t1.getId());
        assertEquals(2, t2.getId());
        assertEquals(2, taskRepository.findAll().size());
    }

    @Test
    void findById_shouldReturnTaskWhenExists() {
        Task created = taskRepository.save("Find me");
        Optional<Task> found = taskRepository.findById(created.getId());

        assertTrue(found.isPresent());
        assertEquals("Find me", found.get().getTitle());
    }

    @Test
    void findById_shouldReturnEmptyWhenNotExists() {
        Optional<Task> notFound = taskRepository.findById(999);
        assertTrue(notFound.isEmpty());
    }

    @Test
    void toggleTask_shouldChangeDoneState() {
        Task created = taskRepository.save("Toggle test");
        assertFalse(created.isDone());

        taskRepository.toggleTask(created.getId());
        Task toggled = taskRepository.findById(created.getId()).get();
        assertTrue(toggled.isDone());
    }

    @Test
    void delete_shouldRemoveTask() {
        Task created = taskRepository.save("To delete");
        boolean deleted = taskRepository.delete(created.getId());

        assertTrue(deleted);
        assertTrue(taskRepository.findAll().isEmpty());
    }

    @Test
    void delete_withInvalidId_shouldReturnFalse() {
        boolean deleted = taskRepository.delete(404);
        assertFalse(deleted);
    }
}