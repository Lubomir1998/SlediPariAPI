package com.example.plugins

import com.example.routes.expenseRoute
import com.example.routes.test
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        expenseRoute()
        test()
    }
}
