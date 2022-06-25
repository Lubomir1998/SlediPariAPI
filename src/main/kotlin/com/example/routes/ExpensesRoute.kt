package com.example.routes

import com.example.data.addExpense
import com.example.data.getAllMonths
import com.example.data.getMonth
import com.example.data.models.PostExpenseRequest
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun Route.expenseRoute() {

    route("/addExpense") {
        post {
            withContext(Dispatchers.IO) {

                val request = try {
                    call.receive<PostExpenseRequest>()
                } catch (e: ContentTransformationException) {
                    print("****** ${e.localizedMessage}")
                    call.respond(BadRequest)
                    return@withContext
                }

                if (addExpense(request)) {
                    call.respond(OK, true)
                } else {
                    call.respond(OK, false)
                }

            }
        }
    }

    route("/getExpense") {
        get {
            withContext(Dispatchers.IO) {

                val month = call.parameters["month"]

                month?.let {
                    call.respond(OK, getMonth(it))
                } ?: call.respond(BadRequest)
            }
        }
    }

    route("/getAllMonths") {
        get {
            withContext(Dispatchers.IO) {

                val months = getAllMonths()
                call.respond(OK, months)
            }
        }
    }
}