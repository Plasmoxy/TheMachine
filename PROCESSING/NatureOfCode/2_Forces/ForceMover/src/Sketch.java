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
		
		Mover(float x, float y) {
			p = new PVector(x, y);
			v = new PVector();
			a = new PVector();
		}
		
		void move() {
			
			
			v.add(a);
			p.add(v);
			
			a.mult(0); // clear acceleration
		}
		
		void draw() {
			pushStyle();
			fill(50);
			ellipse(p.x, p.y, 30, 30);
			popStyle();
		}
		
		void applyForce(PVector force) {
			a.add(force);
		}
		
	}
	
	class Baloon extends Mover {
		
		Baloon(float x, float y) {
			super(x,y);
		}
		
		@Override void move() {
			PVector helium = new PVector(0, -0.05f);

			applyForce(helium);

			if (p.y < 15) {
				v.y *= -1;
			}
			
			if (p.x < 15 || p.x > width-15) {
				v.x *= -1;
			}
			
			super.move();
		}
		
	}
	
	ArrayList<Baloon> baloons = new ArrayList<>();
	
	float xoff = 0;

	@Override public void settings() {
		size(800, 400);
	}

	@Override public void setup() {
		surface.setTitle("Baluuunz xddd");
		for (int i = 0; i<20; i++) {
			baloons.add(new Baloon(random(width), 100 + random(height-100)));
		}
	}

	@Override public void draw() {
		background(200);

		PVector wind = new PVector(0.05f*(2*noise(xoff)-1), 0);
		xoff += 0.01f;
		
		for (Baloon b : baloons) {
			b.applyForce(wind);
			b.move();
			
			line(b.p.x, b.p.y, b.p.x, b.p.y + 50);
			b.draw();
		}
		
	}
}
