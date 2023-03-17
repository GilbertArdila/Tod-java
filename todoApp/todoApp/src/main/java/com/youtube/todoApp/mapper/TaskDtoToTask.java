package com.youtube.todoApp.mapper;

import com.youtube.todoApp.dto.TaskdTO;
import com.youtube.todoApp.persistence.entitis.Task;
import com.youtube.todoApp.persistence.entitis.TaskStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * lo que hacemos en este mapper es convertir el objeto
 * TaskDto que el usiario nos ingresa a un objeto Task que es el que nos recibe
 * el repositorio, tomando los datos que nos envia el usuario
 * y poniendo nosotros los que hacen falta pra cpompletar el objeto Task
 */
@Component
public class TaskDtoToTask  implements Imapper<TaskdTO, Task>{
    @Override
    public Task map(TaskdTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedAt(LocalDateTime.now());
        task.setTaskStatus(TaskStatus.ON_TIME);
        task.setFinished(false);
        return task;
    }

}
