fun main(args: Array<String>) {
	
	var weatherSource = WeatherSource()
	ConsoleWeatherDisplay(weatherSource) // self-attaches, subject keeps reference
	
	weatherSource.testSetWeather(30f, 0.5f)
	
}