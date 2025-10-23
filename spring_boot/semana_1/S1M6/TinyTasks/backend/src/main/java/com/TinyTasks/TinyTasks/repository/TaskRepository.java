package com.TinyTasks.TinyTasks.repository;

import com.TinyTasks.TinyTasks.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {

    private final List<Task> tasks = new ArrayList<>();
    private int idCounter = 1;

    public List<Task> findAll() {
        return tasks;
    }

    public Optional<Task> findById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    public Task save(String title) {
        Task task = new Task(idCounter++, title);
        tasks.add(task);
        return task;
    }

    public void toggleTask(int id){
        Optional<Task> optional = findById(id);

        if(optional.isEmpty()){
            System.out.println("task not found");
            return;
        }
        Task taskFound = optional.get();
        taskFound.setDone(!taskFound.isDone());

        tasks.set(tasks.indexOf(taskFound), taskFound);

    }

    public boolean delete(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }

//    public boolean deletee(int id) {
//        Optional<Task> optional = findById(id);
//        if (optional.isPresent()) {
//            tasks.remove(optional.get());
//            return true;
//        }
//        return false;
//    }
}
