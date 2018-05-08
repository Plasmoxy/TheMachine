import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	class Baton {
		PVector pos;
		
		float angle = 0;
		
		
		Baton(float x, float y) {
			pos = new PVector(x, y);
		}
		
		void update() {
			angle += 0.05f;
			angle %= TWO_PI;
		}
		
		void draw() {
			pushMatrix();
			translate(pos.x, pos.y);
			rotate(angle);
			fill(0, 200);
			stroke(0);
			ellipse(0, 0, 30, 30);
			line(0, 0, 100, 0);
			ellipse(100, 0, 20, 20);
			popMatrix();
		}
	}
	
	Baton b;

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		b = new Baton(width/2, height/2);
	}

	@Override public void draw() {
		fill(200, 30);
		noStroke();
		rect(0, 0, width, height);
		b.update();
		b.draw();
	}
}
