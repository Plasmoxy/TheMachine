import processing.core.PApplet;

public class Sketch extends PApplet {
	public static void main(String[] args) {
		PApplet.main("Sketch");
	}

	class Walker {
		float x, y;

		Walker() {
			x = width/2;
			y = height/2;
		}

		void draw() {
			stroke(255);
			point(x,y);
		}

		void step() {
			float dx = random(-4.5f, 4); // now it has tendency to move to down-right
			float dy = random(-4, 4.5f);
			x += dx;
			y += dy;
		}
	}

	Walker w;

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		background(0);
		w = new Walker();
		strokeWeight(5f);
	}

	@Override public void draw() {
		w.draw();
		w.step();
	}
}
