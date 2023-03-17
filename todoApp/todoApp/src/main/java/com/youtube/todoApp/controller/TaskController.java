package com.youtube.todoApp.controller;

import com.youtube.todoApp.dto.TaskdTO;
import com.youtube.todoApp.persistence.entitis.Task;
import com.youtube.todoApp.persistence.entitis.TaskStatus;
import com.youtube.todoApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private final TaskService service;
  //se crea este constructor por buenas practicas
    public TaskController(TaskService service) {
        this.service = service;
    }

    /**
     * este controlador nos recibe un objeto de tipo TaskDto y
     * lo envia al servicio, quien a su vez utiliza el
     * mapper para transformarlo en un objeto de tipo Task y
     * lo envia al repositorio
     * @param taskDto
     * @return taskDto
     */
    @PostMapping
    public Task createTask(@RequestBody TaskdTO taskDto){
           return this.service.createTask(taskDto);
    }


    @GetMapping
    public List<Task> findAll(){
        return  this.service.getAll();
    }

    /*
    con el ptahVariable le indicamos que va a recibir por path
     una variable que es el status que va a enviar al servicio
     */

    @GetMapping("/status/{status}")
    public List<Task> findByStatus(@PathVariable("status") TaskStatus status){
        return  this.service.findByStatus(status);
    }

    @GetMapping("/{id}")
    public Optional<Task> findById(@PathVariable("id")Long id){
        return this.service.findById(id);
    }

    @PatchMapping("/finished/{id}")
    public ResponseEntity<Void> checkFinished(@PathVariable("id")Long id){
        this.service.updateFinished(id);
        //retorna 204
        return  ResponseEntity.noContent().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id
            ,@RequestBody Task newTask){
        this.service.updateTask(newTask,id);
        //retorna 204
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id")Long id){
        this.service.deleteById(id);
        return  ResponseEntity.noContent().build();
    }
}
