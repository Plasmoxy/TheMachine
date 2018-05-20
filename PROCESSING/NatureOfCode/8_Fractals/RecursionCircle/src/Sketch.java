import processing.core.PApplet;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	void drawCircle(float x, float y, float radius) {
		ellipse(x, y, radius, radius);
		if (radius > 8) {
			drawCircle(x + radius/2, y, radius/2);
			drawCircle(x - radius/2, y, radius/2);
			drawCircle(x, y + radius/2, radius/2);
			drawCircle(x, y - radius/2, radius/2);
		}
	}
	

	@Override public void settings() {
		size(800, 800);
	}

	@Override public void setup() {
		background(255);
		stroke(0);
		drawCircle(width/2, height/2, 400);
	}

	@Override public void draw() {
		
	}
}
