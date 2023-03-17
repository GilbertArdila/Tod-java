package com.youtube.todoApp.mapper;

/**
 * esta interface nos recibe un objeto TaskDto y nos devuelve un objeto  Task
 * @param <I>
 * @param <O>
 */
public interface Imapper <I,O>{

    O map( I  in);
}
