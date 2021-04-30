package com.perpetua.plugins

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

        val todo = listOf<ToDo>(
            ToDo(1, "Create a repository", true),
            ToDo(2, "Start engaging with the content", true),
            ToDo(3, "Finish the tutorial", false),
            ToDo(4, "Do my own project now", false)
        )
        get("/") {
            call.respondText("Hello World!")
        }
//        route to all todos
        get("/todos"){
            call.respond(todo)
        }
//        route to a single to do
        get("/todos/{id}"){
//            to get the parameters you specified in the url
            val id = call.parameters["id"]
            call.respondText("Todolist details for ToDo item no.$id")
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
