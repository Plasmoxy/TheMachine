
data class WeatherData(var temperature: Float, var humidity: Float)

class WeatherSource : Subject {

	val currentData = WeatherData(0f, 0f)
	val observers = mutableListOf<Observer>()
	
	// test
	fun testSetWeather(temp: Float, hum: Float) {
		currentData.temperature = temp
		currentData.humidity = hum
		onWeatherChanged()
	}
	
	fun onWeatherChanged() {
		notifyObservers()
	}
	
	override fun notifyObservers() {
		observers.forEach {
			it.update(currentData.copy())
		}
	}
	
	override fun registerObserver(o: Observer) {
		observers += o
	}
	
	override fun removeObserver(o: Observer) {
		observers -= o
	}

}