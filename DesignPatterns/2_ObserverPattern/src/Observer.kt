
interface Subject {
	fun registerObserver(o: Observer)
	fun removeObserver(o: Observer)
	fun notifyObservers()
}

interface Observer {
	fun update(data: WeatherData)
}

interface Displayer {
	fun display(data: WeatherData)
}