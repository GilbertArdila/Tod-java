package com.youtube.todoApp.persistence.repositories;

import com.youtube.todoApp.persistence.entitis.Task;
import com.youtube.todoApp.persistence.entitis.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    //este TaskStatus ese el enum que creamos para definir los posibloes estado de una tarea
    public List<Task> findAllByTaskStatus(TaskStatus status);

    /*
    creamos una query nativa para actualizar el campo finished del task
     */
    @Modifying
    @Query(value = "UPDATE task SET finished = true WHERE id =:id"
            ,nativeQuery = true)
    public void checkFinished(@Param("id") Long id);


}
