import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	class Ball {
		
		PVector origin, pos;
		
		float freq; // f = 1/T
		
		Ball(float x, float y, float frequency) {
			origin = new PVector(x, y);
			pos = new PVector();
			pos.set(origin);
			freq = frequency;
		}
		
		
		void update() {
			pos.x = origin.x + 100*sin(TWO_PI * frameCount * freq);
			pos.y = origin.y + 100*cos(TWO_PI * frameCount * freq);
		}
		
		void draw() {
			pushMatrix();
			fill(0);
			noStroke();
			translate(pos.x, pos.y);
			ellipse(0, 0, 30, 30);
			popMatrix();
		}
		
	}
	
	Ball b;

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		b = new Ball(width/2, height/2, 0.01f);
		background(200);
	}

	@Override public void draw() {
		noStroke();
		fill(200, 30);
		rect(0, 0, width, height);
		
		
		b.update();
		b.draw();
	}
}
