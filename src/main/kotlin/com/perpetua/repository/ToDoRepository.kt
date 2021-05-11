package com.perpetua.repository

import ToDoDraft
import com.perpetua.entities.ToDo

interface ToDoRepository {

    fun getAllToDos():List<ToDo>

    fun getTodo(id: Int): ToDo?

    fun addTodo(draft: ToDoDraft): ToDo

    fun updateTodo(id: Int, draft: ToDoDraft): Boolean

    fun deleteTodo(id: Int): Boolean
}