import java.util.*
import kotlin.concurrent.schedule

fun main(args : Array<String>) {

	// kotlin class
	var h = Hello()
	h.say()
	println(h.add(1,2))

	// java class
	println(Matematix.add(5, 6))

	var obj = object {
		var value = 4
	}

	//KYS FAGGOT
	println(obj.value)


	var arr = arrayOf(
			1, 2, 3
	)

	println("arr[2] = " + arr[2])

	var active = true


	var printer = Runnable {
		while (active) {
			println("BUM")
			Thread.sleep(1000) // 2 times pre thread
		}
	}

	Thread(printer).start()
	Thread(printer).start()

	Timer("temp", true).schedule(2000 - 20) {
		println("AFTER 2 SEC")
		active = false
	}

	// two printers should together print 4 times "BUM" and the timer should stop them after 2 sec

	println("main thread ended")


}