package dagger.coding02

import javax.inject.Inject
import javax.inject.Singleton


class Car @Inject constructor(private val engine: Engine,
                              private val wheels: Wheels) {

    val id = Math.random()

    fun drive() {
        println("$id Driving ...")
    }
}

