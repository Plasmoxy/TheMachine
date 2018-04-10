import java.util.*

fun main(args : Array<String>) {

	// kotlin class
	var h = Hello()
	h.say()
	println(h.add(1,2))

	// java class
	println(Math.add(5, 6))

	var obj = object {
		var value = 4
	}

	//KYS FAGGOT
	println(obj.value)


	var arr = arrayOf(
			1, 2, 3
	)

	println("arr[2] = " + arr[2])

	print("daj daco :")
	var sc = Scanner(System.`in`)
	var a = sc.nextLine()
	println(a)


}