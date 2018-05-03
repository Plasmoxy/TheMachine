import processing.core.PApplet;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	int[] numbers = {1, 1, 2, 3, 3}; // 2 has the least probability to be picked
	

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		int index = parseInt(random(numbers.length));
		println(numbers[index]);
	}

	@Override public void draw() {
		
	}
}
