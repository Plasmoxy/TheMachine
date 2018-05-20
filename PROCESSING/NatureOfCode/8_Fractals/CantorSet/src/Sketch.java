import processing.core.PApplet;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	void cantor(float x, float y, float len) {
		strokeWeight(1);
		if (len >= 1) {
			line(x, y, x+len, y);

			y += 30;

			cantor(x, y, len/3);
			cantor(x + len*2/3, y, len/3);
		}
	}
	

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		cantor(10, 20, width-20);
	}

	@Override public void draw() {
		
	}
}
