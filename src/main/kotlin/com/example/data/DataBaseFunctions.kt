package com.example.data

import com.example.Expenses
import com.example.data.models.*
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.setValue

val client = KMongo.createClient().coroutine
val database = client.getDatabase("Razhodi")

val months = database.getCollection<Month>()

suspend fun addExpense(request: PostExpenseRequest): Boolean {

    val month = months.findOneById(request.monthId)

    return if (month == null) {
        months.insertOne(insertMonthUtil(request.monthId, Expenses.valueOf(request.title.uppercase()), request.price)).wasAcknowledged()
    } else {
        months.updateOneById(request.monthId, getBson(request.monthId, Expenses.valueOf(request.title.uppercase()), request.price)).wasAcknowledged()
    }

}

suspend fun getMonth(month: String): Month? {

    return months.findOneById(month)
}

suspend fun getAllMonths(): List<Month> {

    return months.find().toList()
}

fun insertMonthUtil(id: String, title: Expenses, price: Float): Month {

    return when (title) {
        Expenses.CLOTHES -> Month(clothes = price, id = id)
        Expenses.WORKOUT -> Month(workout = price, id = id)
        Expenses.REMONT -> Month(remont = price, id = id)
        Expenses.POSUDA -> Month(posuda = price, id = id)
        Expenses.TRAVEL -> Month(travel = price, id = id)
        Expenses.GIFTS -> Month(gifts = price, id = id)
        Expenses.SNACKS -> Month(snacks = price, id = id)
        Expenses.MEDICINE -> Month(medicine = price, id = id)
        Expenses.MACHOVE -> Month(machove = price, id = id)
        Expenses.FURNITURE -> Month(furniture = price, id = id)
        Expenses.TEHNIKA -> Month(tehnika = price, id = id)
        Expenses.DOM_POTREBI -> Month(domPotrebi = price, id = id)
        Expenses.EDUCATION -> Month(education = price, id = id)
        Expenses.ENTERTAINMENT -> Month(entertainment = price, id = id)
        Expenses.SUBSCRIPTIONS -> Month(subscriptions = price, id = id)
        Expenses.TATTOO -> Month(tattoo = price, id = id)
        Expenses.TOYS -> Month(toys = price, id = id)
        Expenses.RESTAURANT -> {
            val food = Food(restaurant = price)
            Month(food = food, id = id)
        }
        Expenses.HOME -> {
            val food = Food(home = price)
            Month(food = food, id = id)
        }
        Expenses.TOK -> {
            val smetki = Smetki(tok = price)
            Month(smetki = smetki, id = id)
        }
        Expenses.TOPLO -> {
            val smetki = Smetki(toplo = price)
            Month(smetki = smetki, id = id)
        }
        Expenses.VODA -> {
            val smetki = Smetki(voda = price)
            Month(smetki = smetki, id = id)
        }
        Expenses.INTERNET -> {
            val smetki = Smetki(internet = price)
            Month(smetki = smetki, id = id)
        }
        Expenses.VHOD -> {
            val smetki = Smetki(vhod = price)
            Month(smetki = smetki, id = id)
        }
        Expenses.TELEFON -> {
            val smetki = Smetki(telefon = price)
            Month(smetki = smetki, id = id)
        }
        Expenses.PUBLIC -> {
            val transport = Transport(public = price)
            Month(transport = transport, id = id)
        }
        Expenses.TAXI -> {
            val transport = Transport(taxi = price)
            Month(transport = transport, id = id)
        }
        Expenses.CAR -> {
            val transport = Transport(car = price)
            Month(transport = transport, id = id)
        }
        Expenses.HIGIEN -> {
            val cosmetics = Cosmetics(higien = price)
            Month(cosmetics = cosmetics, id = id)
        }
        Expenses.OTHER -> {
            val cosmetics = Cosmetics(other = price)
            Month(cosmetics = cosmetics, id = id)
        }
        Expenses.CLEAN -> {
            val preparati = Preparati(clean = price)
            Month(preparati = preparati, id = id)
        }
        Expenses.WASH -> {
            val preparati = Preparati(wash = price)
            Month(preparati = preparati, id = id)
        }
    }
}

suspend fun getBson(id: String, expenses: Expenses, price: Float): Bson {

    return when (expenses) {
        Expenses.CLOTHES -> {
            val clothes = months.findOneById(id)!!.clothes
            val newPrice = clothes + price
            setValue(Month::clothes, newPrice)
        }
        Expenses.WORKOUT -> {
            val workout = months.findOneById(id)!!.workout
            val newPrice = workout + price
            setValue(Month::workout, newPrice)
        }
        Expenses.REMONT -> {
            val remont = months.findOneById(id)!!.remont
            val newPrice = remont + price
            setValue(Month::remont, newPrice)
        }
        Expenses.RESTAURANT -> {
            val food = months.findOneById(id)!!.food
            food.restaurant += price
            setValue(Month::food, food)
        }
        Expenses.HOME-> {
            val food = months.findOneById(id)!!.food
            food.home += price
            setValue(Month::food, food)
        }
        Expenses.TOK-> {
            val smetki = months.findOneById(id)!!.smetki
            smetki.tok += price
            setValue(Month::smetki, smetki)
        }
        Expenses.VODA-> {
            val smetki = months.findOneById(id)!!.smetki
            smetki.voda += price
            setValue(Month::smetki, smetki)
        }
        Expenses.TOPLO-> {
            val smetki = months.findOneById(id)!!.smetki
            smetki.toplo += price
            setValue(Month::smetki, smetki)
        }
        Expenses.VHOD-> {
            val smetki = months.findOneById(id)!!.smetki
            smetki.vhod += price
            setValue(Month::smetki, smetki)
        }
        Expenses.INTERNET-> {
            val smetki = months.findOneById(id)!!.smetki
            smetki.internet += price
            setValue(Month::smetki, smetki)
        }
        Expenses.TELEFON-> {
            val smetki = months.findOneById(id)!!.smetki
            smetki.telefon += price
            setValue(Month::smetki, smetki)
        }
        Expenses.PUBLIC-> {
            val transport = months.findOneById(id)!!.transport
            transport.public += price
            setValue(Month::transport, transport)
        }
        Expenses.TAXI-> {
            val transport = months.findOneById(id)!!.transport
            transport.taxi += price
            setValue(Month::transport, transport)
        }
        Expenses.CAR-> {
            val transport = months.findOneById(id)!!.transport
            transport.car += price
            setValue(Month::transport, transport)
        }
        Expenses.POSUDA -> {
            val posuda = months.findOneById(id)!!.posuda
            val newPrice = posuda + price
            setValue(Month::posuda, newPrice)
        }
        Expenses.TRAVEL -> {
            val travel = months.findOneById(id)!!.travel
            val newPrice = travel + price
            setValue(Month::travel, newPrice)
        }
        Expenses.GIFTS -> {
            val gifts = months.findOneById(id)!!.gifts
            val newPrice = gifts + price
            setValue(Month::gifts, newPrice)
        }
        Expenses.SNACKS -> {
            val snacks = months.findOneById(id)!!.snacks
            val newPrice = snacks + price
            setValue(Month::snacks, newPrice)
        }
        Expenses.MEDICINE -> {
            val medicine = months.findOneById(id)!!.medicine
            val newPrice = medicine + price
            setValue(Month::medicine, newPrice)
        }
        Expenses.HIGIEN-> {
            val cosmetics = months.findOneById(id)!!.cosmetics
            cosmetics.higien += price
            setValue(Month::cosmetics, cosmetics)
        }
        Expenses.OTHER-> {
            val cosmetics = months.findOneById(id)!!.cosmetics
            cosmetics.other += price
            setValue(Month::cosmetics, cosmetics)
        }
        Expenses.DOM_POTREBI -> setValue(Month::domPotrebi, price)
        Expenses.CLEAN-> {
            val preparati = months.findOneById(id)!!.preparati
            preparati.clean += price
            setValue(Month::preparati, preparati)
        }
        Expenses.WASH-> {
            val preparati = months.findOneById(id)!!.preparati
            preparati.wash += price
            setValue(Month::preparati, preparati)
        }
        Expenses.MACHOVE -> {
            val machove = months.findOneById(id)!!.machove
            val newPrice = machove + price
            setValue(Month::machove, newPrice)
        }
        Expenses.FURNITURE -> {
            val furniture = months.findOneById(id)!!.furniture
            val newPrice = furniture + price
            setValue(Month::furniture, newPrice)
        }
        Expenses.TEHNIKA -> {
            val tehnika = months.findOneById(id)!!.tehnika
            val newPrice = tehnika + price
            setValue(Month::tehnika, newPrice)
        }
        Expenses.EDUCATION -> {
            val education = months.findOneById(id)!!.education
            val newPrice = education + price
            setValue(Month::education, newPrice)
        }
        Expenses.ENTERTAINMENT -> {
            val entertainment = months.findOneById(id)!!.entertainment
            val newPrice = entertainment + price
            setValue(Month::entertainment, newPrice)
        }
        Expenses.SUBSCRIPTIONS -> {
            val subscriptions = months.findOneById(id)!!.subscriptions
            val newPrice = subscriptions + price
            setValue(Month::subscriptions, newPrice)
        }
        Expenses.TATTOO -> {
            val tattoo = months.findOneById(id)!!.tattoo
            val newPrice = tattoo + price
            setValue(Month::tattoo, newPrice)
        }
        Expenses.TOYS -> {
            val toys = months.findOneById(id)!!.toys
            val newPrice = toys + price
            setValue(Month::toys, newPrice)
        }
    }

}