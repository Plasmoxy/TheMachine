import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}

	class Mover {

		PVector pos;// position
		PVector vel; // velocity
		PVector acc; // acceleration

		Mover(float x, float y) {
			pos = new PVector(x, y);
			vel = new PVector();
			acc = new PVector();
		}

		void update() {

			vel.add(acc);
			pos.add(vel);

			acc.mult(0); // clear acceleration
		}

		void display() {
			pushStyle();
			fill(50);
			ellipse(pos.x, pos.y, 30, 30);
			popStyle();
		}

		void applyForce(PVector force) {
			acc.add(force);
		}

	}




	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		
	}

	@Override public void draw() {
		
	}
}
