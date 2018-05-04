import processing.core.PApplet;

import java.util.Arrays;

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
			float n = random(1);
			if (n < 0.1f) {
				y--;
			} else if (n < 0.3f) {
				x++;
			} else if (n < 0.6f) {
				y++;
			} else if (n < 1f) {
				x--;
			}
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
