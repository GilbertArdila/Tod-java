package com.youtube.todoApp.service;

import com.youtube.todoApp.dto.TaskdTO;
import com.youtube.todoApp.exceptions.TodoExeptions;
import com.youtube.todoApp.mapper.TaskDtoToTask;
import com.youtube.todoApp.persistence.entitis.Task;
import com.youtube.todoApp.persistence.entitis.TaskStatus;
import com.youtube.todoApp.persistence.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
   private final TaskRepository repository;
    @Autowired
    private final TaskDtoToTask mapper;

    //por buenas practicas se implementa este constructor
    public TaskService(TaskRepository repository, TaskDtoToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskdTO taskDto){
        /*el repositorio recibe un task, pero a nosotros nos lleva un taskDto
        por tanto usamos el mapper para convertir un objeto de tipo
        TaskDto a un objeto de tipo task
         */
        Task task = mapper.map(taskDto);
       return  this.repository.save(task);

    }

    public List<Task> getAll(){
        return this.repository.findAll();
    }



    public List<Task> findByStatus(TaskStatus status){
        return  this.repository.findAllByTaskStatus(status);
    }

    public Optional<Task> findById(Long id){
        if(!this.repository.findById(id).isPresent()){
            throw new TodoExeptions("El task con id "+id+" no fue econtrado",
                    HttpStatus.NOT_FOUND);
        }
        return this.repository.findById(id);
    }
    /*
    la debemos marcar con transactional para que nos nos arroje un error
    al tratar de actualizar la tarea
     */
 @Transactional
    public void updateFinished(Long id){
     if(!this.repository.findById(id).isPresent()){
         throw new TodoExeptions("El id no fue encontrado",
                 HttpStatus.NOT_FOUND);
     }
        this.repository.checkFinished(id);
    }

    public void deleteById(Long id){
        if(!this.repository.findById(id).isPresent()){
            throw new TodoExeptions("Esta tarea no existe, revisa tu id",
                    HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }

   public Task updateTask(Task newTask, Long id){
       if(this.repository.findById(id).isEmpty()){
           throw new TodoExeptions("id no existe",
                   HttpStatus.NOT_FOUND);
       }
     return
       repository.findById(id)
               .map(
                       task-> {
                           task.setTitle(newTask.getTitle());
                           task.setDescription(newTask.getDescription());
                           task.setEta(newTask.getEta());
                           task.setCreatedAt(newTask.getCreatedAt());
                           task.setTaskStatus(newTask.getTaskStatus());
                           task.setFinished(newTask.isFinished());
                           return repository.save(task);
                       }
               ).get();

   }






}
