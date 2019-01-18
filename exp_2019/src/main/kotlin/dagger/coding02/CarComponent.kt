package dagger.coding02

import dagger.Component

@Component
interface CarComponent {
    fun getCar(): Car
}