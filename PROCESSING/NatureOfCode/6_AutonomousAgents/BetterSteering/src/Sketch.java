import processing.core.PApplet;
import processing.core.PVector;

import java.util.LinkedList;
import java.util.List;

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


	class Vehicle extends Mover {

		PVector desv; // desired velocity
		PVector steeringForce;

		float maxspeed = 5f;
		float maxforce = 1f;

		Vehicle(float x, float y) {
			super(x, y);
			desv = new PVector();
			steeringForce = new PVector();
		}
		
		void seek(PVector target) {
			desv.set(target);
			desv.sub(pos);
			
			desv.normalize();
			desv.mult(maxspeed);
		}

		@Override void update() {


			// Apply steering force
			// F_steer = v_desired - v_current
			steeringForce.set(desv);
			steeringForce.sub(vel);
			steeringForce.limit(maxforce);
			applyForce(steeringForce);

			super.update();
		}

		@Override void display() {
			fill(0, 200);
			noStroke();
			pushMatrix();
			translate(pos.x, pos.y);
			rotate(atan2(desv.y, desv.x));
			triangle(10, 0, -10, -8, -10, 8);
			popMatrix();
		}

	}
	
	PVector mouse;
	
	List<Vehicle> vehicles = new LinkedList<>();

	@Override public void settings() {
		size(800, 600);
	}

	@Override public void setup() {
		mouse = new PVector(mouseX, mouseY);
		
		for (int i = 0; i<20; i++) {
			Vehicle v = new Vehicle(random(width), random(height));
			v.maxforce = random(0.1f, 0.2f);
			vehicles.add(v);
		}
	}

	@Override public void draw() {
		mouse.x = mouseX; mouse.y = mouseY;
		background(200);
		
		vehicles.forEach(v -> v.seek(mouse));
		vehicles.forEach(Vehicle::update);
		vehicles.forEach(Vehicle::display);
		
	}
}
