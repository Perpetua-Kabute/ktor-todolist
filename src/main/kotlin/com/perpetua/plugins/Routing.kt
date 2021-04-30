package com.perpetua.plugins

import InMemoryToDoRepository
import com.perpetua.entities.ToDo
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
        gson{
            setPrettyPrinting()
            disableHtmlEscaping()
        }
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
//            to get the parameters you specified in the url
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

        }
//         edit a single to do
        put("todos/{id}"){

        }

        delete("/todos/{id}"){

        }
    }

}
