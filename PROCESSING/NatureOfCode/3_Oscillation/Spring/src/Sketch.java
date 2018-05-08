import processing.core.PApplet;
import processing.core.PVector;

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

		void update() {


			v.add(a);
			p.add(v);

			a.mult(0); // clear acceleration
		}

		void display() {
			pushStyle();
			fill(50);
			ellipse(p.x, p.y, 30, 30);
			popStyle();
		}

		void applyForce(PVector force) {
			a.add(force);
		}

	}
	
	class Spring {
		
		PVector anchor;
		
		float l;
		float k = 0.1f;
		
		Spring(float x, float y, float length) {
			anchor = new PVector(x, y);
			l = length;
		}
		
		void connect(Mover m) {
			PVector force = PVector.sub(m.p, anchor);
			float d = force.mag();
			float stretch = d - l; // stretch distance of string
			
			force.normalize();
			force.mult(-l * k * stretch);
			
			m.applyForce(force);
		}
		
		void display() {
			fill(100);
			rectMode(CENTER);
			rect(anchor.x, anchor.y, 10, 10);
		}
		
		void displayLine(Mover m) {
			stroke(0);
			line(m.p.x, m.p.y, anchor.x, anchor.y);
		}
		
	}
	
	Mover m;
	Spring s;
	
	PVector gravity = new PVector(0, 1);
	

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		m = new Mover(50, 50);
		s = new Spring(width/2, height/2, 0.1f);
	}

	@Override public void draw() {
		background(200);
		
		m.applyForce(gravity);
		s.connect(m);
		m.update();
		
		m.display();
		s.display();
		s.displayLine(m);
	}
}
