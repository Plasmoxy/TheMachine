import org.junit.Test

class AddTest {
	
	private val adder = Adder()
	
	@Test
	fun `check if add works`() {
		assert(adder.add(1, 2) == 1 + 2)
	}
	
	@Test
	fun `check if add isnt fake`() {
		assert(adder.add(1, 2) != 5)
	}
	
}