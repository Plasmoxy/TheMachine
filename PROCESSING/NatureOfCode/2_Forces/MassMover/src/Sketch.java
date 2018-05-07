import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}


	class Mover {

		PVector p;// position
		PVector v; // velocity
		PVector a; // acceleration
		
		float m; // mass
		float r; // radius

		Mover(float x, float y, float radius, float mass) {
			p = new PVector(x, y);
			v = new PVector();
			a = new PVector();
			m = mass;
			r = radius;
		}

		void move() {
			

			// bounce and stuff
			if (p.y >= height-r) {
				v.y = -abs(v.y);
			}
			
			else if (p.y <= r) {
				v.y = abs(v.y);
			}

			else if (p.x >= width-r) {
				v.x = -abs(v.x);
			}
			
			else if (p.x <= r) {
				v.x = abs(v.x);
			}
			
			else {
				// if not bouncing then apply forces
				v.add(a);
			}

			p.add(v);
			a.mult(0); // clear acceleration
		}

		void draw() {
			pushStyle();
			fill(50);
			ellipse(p.x, p.y, 2*r, 2*r);
			popStyle();
		}

		void applyForce(PVector force) {
			PVector f = PVector.div(force, m);
			a.add(f);
		}

	}
	
	
	
	PVector gravity = new PVector(0, 0.5f);
	PVector wind = new PVector(1, 0);
	float dwind = 0.1f;
	
	ArrayList<Mover> movers = new ArrayList<>();
	

	@Override public void settings() {
		size(800, 400);
	}

	@Override public void setup() {
		for (int i = 0; i < 20; i++) {
			float a = random(50);
			movers.add(new Mover(50 + random(width-100), 50 + random(height-100), a, a));
		}
	}

	@Override public void draw() {
		background(200);
		
		/*
		if (wind.x <= -2f) {
			dwind = 0.005f;
		}
		
		if (wind.x >= 2f) {
			dwind = -0.005f;
		}
		
		wind.x += dwind;
		
		*/
		
		fill(0);
		text("Wind = " + wind.x, 50, 50);
		
		for (Mover m : movers) {
			
			// friction : F = -c*N*v
			float c = 0.01f; // friction coeficient
			float N = 0.3f; // normal
			PVector f = m.v.copy();
			f.normalize();
			f.mult(-1);
			f.mult(N);
			
			m.applyForce(f);
			m.applyForce(wind);
			m.applyForce(gravity);
			m.move();
			m.draw();
		}
		
	}
}
