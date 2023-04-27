package com.avanade.todo.service;

import com.avanade.todo.exception.InvalidInputException;
import com.avanade.todo.exception.ResourceNotFoundException;
import com.avanade.todo.model.Task;
import com.avanade.todo.model.dto.Activity;
import com.avanade.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BoredApiService boredApiService;

    public Task createTask(Task task){
        task.setCreatedAt(LocalDateTime.now());
        task.setIsCompleted(false);
        task.setCompletedAt(null);

        return this.taskRepository.save(task);
    }

    public List<Task> listAllTasks(){
        return this.taskRepository.findAll();
    }

    public Task findById(Long id){
        return this.taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with this id" + id));
    }

    public Task updateTask(Task task){
        if(task.getId() == null){
            throw new InvalidInputException("This task doesn't exist");
        }

        return this.taskRepository.save(task);
    }

    public void deleteTask(Long id){
        this.taskRepository.deleteById(id);
    }

    public Task generateRandom(){
        Activity activity = boredApiService.callBoredApi();
        Task task = new Task();
        task.setTitle(activity.getActivity());
        task.setDescription(activity.getType() + "Task");


        return createTask(task);
    }

}
