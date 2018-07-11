
class ConsoleWeatherDisplay(private val subject: Subject) : Observer, Displayer {
	
	init { subject.registerObserver(this) }
	
	override fun update(data: WeatherData) {
		display(data)
	}
	
	override fun display(data: WeatherData) {
		println("The weather is : $data")
	}
	
}