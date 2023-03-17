package com.youtube.todoApp.dto;

import lombok.Data;

import java.time.LocalDateTime;

//valida los getters y setters
@Data
public class TaskdTO {

    private  String title;
    private String description;

    private LocalDateTime eta;

}
