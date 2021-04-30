package com.perpetua.repository

import com.perpetua.entities.ToDo

interface ToDoRepository {

    fun getAllToDos():List<ToDo>

    fun getTodo(id: Int): ToDo?
}