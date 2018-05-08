import processing.core.PApplet;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	float start = 0;
	float angleVel = 0.05f;
	

	@Override public void settings() {
		size(800, 400);
		fullScreen();
	}

	@Override public void setup() {
		
	}

	@Override public void draw() {
		background(200);
		
		stroke(0);
		strokeWeight(2);
		noFill();
		
		float angle = start;
		beginShape();
		
		for (int x = 0; x <= width; x++) {
			float y = map(sin(angle), -1, 1, 0, height);
			vertex(x, y);
			angle += angleVel;
		}
		
		angleVel += 0.001;
		
		endShape();
	}
}
