package com.perpetua.plugins

import InMemoryToDoRepository
import ToDoDraft
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {
    install(CallLogging)
    install(ContentNegotiation){
       gson()
    }
    // Starting point for a Ktor app:
    routing {
        val repository = InMemoryToDoRepository()

        get("/") {
            call.respondText("Hello World!")
        }
//        route to all todos
        get("/todos"){
            call.respond(repository.getAllToDos())
        }
//        route to a single to do
        get("/todos/{id}"){
//          to get the parameters you specified in the url
            val id = call.parameters["id"]?.toIntOrNull()
            if(id == null){
                call.respond(HttpStatusCode.BadRequest, "Id has to be a number")
                return@get
            }

            val todo = repository.getTodo(id)
            if(todo == null){
                call .respond(
                    HttpStatusCode.NotFound,
                    "Id does not exist"
                )
            }else{
                call.respond(todo)
            }


        }

        post("/todos"){
            val todoDraft = call.receive<ToDoDraft>()
            val todo = repository.addTodo(todoDraft)
            call.respond(todo)
        }
//         edit a single to do
        put("todos/{id}"){
            val toDoDraft = call.receive<ToDoDraft>()
            val todoId = call.parameters["id"]?.toIntOrNull()
            if(todoId == null){
                call.respond(HttpStatusCode.BadRequest, "Id has to be a number")
                return@put
            }
            val updated = repository.updateTodo(todoId, toDoDraft)

            if(updated){
                call.respond(HttpStatusCode.OK)
            }else{
                call.respond(HttpStatusCode.NotFound, "No todo with the id $todoId")
            }

        }

        delete("/todos/{id}"){
            val todoId = call.parameters["id"]?.toIntOrNull()

            if(todoId == null){
                call.respond(HttpStatusCode.BadRequest, "Id has to be a number")
                return@delete
            }

            val deleted = repository.deleteTodo(todoId)

            if(deleted){
                call.respond(HttpStatusCode.OK)
            }else{
                call.respond(HttpStatusCode.NotFound, "No todo with the id $todoId")
            }
        }
    }

}
