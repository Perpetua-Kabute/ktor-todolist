package com.perpetua.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {

        val todo = listOf<>()
        get("/") {
            call.respondText("Hello World!")
        }
//        route to all todos
        get("/todos"){

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
