package dagger.coding02

fun main(args: Array<String>) {
    val comp = DaggerCarComponent.create()

    val car = comp.getCar()
    car.drive()

    val car2 = comp.getCar()
    car2.drive()
}