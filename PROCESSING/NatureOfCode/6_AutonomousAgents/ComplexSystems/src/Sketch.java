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

		float r = 10f;
		float maxspeed = 5f;
		float maxforce = 1f;
		float separationLimit = 2*r; // how close to separate

		Vehicle(float x, float y) {
			super(x, y);
		}

		PVector seek(PVector target) {
			// (->)desv = (->)target - (->)pos
			
			PVector desv = PVector.sub(target , pos);

			desv.normalize();
			desv.mult(maxspeed);

			// Apply steering force
			// F_steer = v_desired - v_current
			PVector steeringForce = PVector.sub(desv, vel);
			steeringForce.limit(maxforce);
			
			return steeringForce;
		}
		
		PVector separate(List<Vehicle> others) {
			
			// mean = sum/elementsCount
			
			// a sum of UNIT vectors
			PVector vectorSum = new PVector();
			int vectorCount = 0;
			
			for (Vehicle o : others) {
				if (this == o) break; // don't look at yourself !
				
				float d = PVector.dist(pos, o.pos); // distance
				
				if ( (d > 0) && (d < separationLimit)) {
					PVector diff = PVector.sub(pos, o.pos);
					diff.normalize();
					diff.div(d); // the smaller the distance, the stronger the force
					
					vectorSum.add(diff);
					vectorCount++;
				}
			}
			
			if (vectorCount > 0) {
				// vectorSum.div(vectorCount);
				// morph -> vectorSum is now the average vector
				vectorSum.div(vectorCount);
				
				vectorSum.setMag(maxspeed); // set magnitude of the vector
				// now its desired velocity
				
				// morph -> vectorSum is now steering force
				// Fsteer = desv - vel
				vectorSum.sub(vel);
				vectorSum.limit(maxforce); // limit the force
			}
			
			return vectorSum;
		}
		
		void applyBehaviors(List<Vehicle> others) {
			PVector separateF = separate(vehicles);
			PVector seekF = seek(mouse);
			
			seekF.mult(0.5f);
			separateF.mult(3f);
			
			applyForce(separateF);
			applyForce(seekF);
		}

		@Override void update() {
			super.update();
		}

		@Override void display() {
			fill(150);
			stroke(0);
			pushMatrix();
			translate(pos.x, pos.y);
			ellipse(0, 0, 2*r, 2*r);
			popMatrix();
		}

	}
	
	
	PVector mouse;
	List<Vehicle> vehicles = new LinkedList<>();

	@Override public void settings() {
		size(800, 600);
	}

	@Override public void setup() {
		for (int i = 0; i < 100; i++) {
			vehicles.add(new Vehicle(random(width), random(height)));
		}
		mouse = new PVector(mouseX, mouseY);
	}

	@Override public void draw() {
		mouse.x = mouseX; mouse.y = mouseY;
		background(200);
		
		for (Vehicle v : vehicles) {
			v.applyBehaviors(vehicles);
			v.update();
			v.display();
		}
	}
}
