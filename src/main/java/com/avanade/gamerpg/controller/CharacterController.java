package com.avanade.todo.controller;

import com.avanade.todo.model.Task;
import com.avanade.todo.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/todo")
@Api(value = "TASK API REST")
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping("/tasks")
    @ApiOperation("To list all the tasks created")
    public ResponseEntity<List<Task>> GetAll(){
        return new ResponseEntity<>( service.listAllTasks(), HttpStatus.OK );
    }

    @GetMapping("/tasks/{id}")
    @ApiOperation("to find a task by id")
    public ResponseEntity<Task> GetById(@PathVariable( value = "id") Long taskId){
        return new ResponseEntity<>(service.findById(taskId), HttpStatus.OK);
    }

    @PostMapping("/tasks")
    @ApiOperation("to create a new task")
    public ResponseEntity<Task> CreateTask(@RequestBody Task task){
        return new ResponseEntity<>( service.createTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/tasks")
    @ApiOperation("to update this task")
    public ResponseEntity<Task> UpdateTask(@RequestBody Task task){
        return new ResponseEntity<>(service.updateTask(task), HttpStatus.OK);
    }

    @DeleteMapping("/tasks")
    @ApiOperation("Delete this task by id")
    public ResponseEntity<HttpStatus> DeleteTask(@RequestHeader Long taskId){
        service.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/tasks/random")
    @ApiOperation("To create random tasks")
    public ResponseEntity<Task> CreateRandomTask(){
        return new ResponseEntity<>(service.generateRandom(), HttpStatus.CREATED);
    }

}
