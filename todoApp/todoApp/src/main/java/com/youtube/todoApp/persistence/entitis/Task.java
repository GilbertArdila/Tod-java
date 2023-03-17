package com.youtube.todoApp.persistence.entitis;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "task")
public class Task {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @Column(length = 50,nullable = false,unique = true)
    private  String title;
   @Column(length = 255,nullable = false)
    private String description;
   @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)

    private LocalDateTime eta;

    private boolean finished;
    private TaskStatus taskStatus;
}
