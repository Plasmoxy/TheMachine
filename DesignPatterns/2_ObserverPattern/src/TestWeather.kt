import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

fun main(args: Array<String>) {
	
	val active = AtomicBoolean(true)
	
	val weatherSource = WeatherSource()
	ConsoleWeatherDisplay(weatherSource) // self-attaches, subject keeps reference
	
	thread {
		while (active.get()) {
			weatherSource.testSetWeather(Math.random().toFloat()*40, Math.random().toFloat())
			Thread.sleep(1000)
		}
	}
	
	readLine()
	active.set(false)
	
}