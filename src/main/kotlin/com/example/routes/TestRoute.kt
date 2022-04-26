package com.example.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun Route.test() {

    route("/test") {
        get {
            withContext(Dispatchers.IO) {

                call.respond(HttpStatusCode.OK, "hello world")
            }
        }
    }
}