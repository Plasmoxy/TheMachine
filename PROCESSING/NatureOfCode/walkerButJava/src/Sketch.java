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
			float dx = random(-1, 1);
			float dy = random(-1, 1);
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
	}

	@Override public void draw() {
		w.draw();
		w.step();
	}
}
