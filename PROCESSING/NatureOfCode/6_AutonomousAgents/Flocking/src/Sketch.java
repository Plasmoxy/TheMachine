import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
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
		int vehicleColor = color(100);

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
			fill(vehicleColor);
			ellipse(pos.x, pos.y, 30, 30);
			popStyle();
		}

		void applyForce(PVector force) {
			acc.add(force);
		}
		
		void bounce(float minX, float maxX, float minY, float maxY) {
			if (pos.x < minX || pos.x > maxX) {
				vel.x *= -1;
			}
			
			if (pos.y < minY || pos.y > maxY) {
				vel.y *= -1;
			}
		}

	}


	class Vehicle extends Mover {

		float r = 5f;
		float maxspeed = 5f;
		float maxforce = 1f;
		
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

		PVector separate(List<? extends Vehicle> others, float separationLimit) {

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

		@Override void update() {
			super.update();
		}

		@Override void display() {
			float theta = vel.heading() + PI/2;
			fill(vehicleColor);
			stroke(0);
			pushMatrix();
			translate(pos.x, pos.y);
			rotate(theta);
			beginShape();
			vertex(0, -r*2);
			vertex(-r, r*2);
			vertex(r, r*2);
			endShape(CLOSE);
			popMatrix();
		}

	}
	
	// boid == bird
	
	class Boid extends Vehicle {
		Boid(float x, float y) {
			super(x, y);
		}
		
		void flock(List<? extends Boid> others) {
			PVector sep = separate(others, 8*r);
			PVector ali = align(others);
			PVector coh = cohesion(others);
			
			sep.mult(1.5f);
			ali.mult(1.0f);
			coh.mult(1.0f);
			
			applyForce(sep);
			applyForce(ali);
			applyForce(coh);
		}
		
		PVector align(List<? extends Boid> others) {
			float neighborDist = 50; // distance to neighbor
			
			PVector sum = new PVector();
			
			int count = 0; // count of boids visible from this boid's view
			for (Boid b : others) {
				
				float d = PVector.dist(pos, b.pos);
				if ((d > 0) && (d < neighborDist)) {
					sum.add(b.vel);
					count++;
				}
			}
			
			if (count > 0) {
				sum.div(count);
				sum.setMag(maxspeed);

				PVector steer = PVector.sub(sum, vel);
				steer.limit(maxforce);
				return steer;
			} else {
				return new PVector();
			}
			
		}
		
		PVector cohesion(List<?extends Boid> others) {
			float neighborDist = 100;
			PVector sum = new PVector();
			int count = 0;
			
			for (Boid b : others) {
				float d = PVector.dist(pos, b.pos);
				
				if ((d > 0) && (d < neighborDist)) {
					sum.add(b.pos);
					count++;
				}
			}
			
			if (count > 0) {
				sum.div(count);
				return seek(sum);
			} else {
				return new PVector();
			}
		}
		
	}


	PVector mouse;
	List<Boid> boids = new LinkedList<>();
	
	Boid player;

	@Override public void settings() {
		fullScreen();
	}

	@Override public void setup() {
		for (int i = 0; i < 100; i++) {
			boids.add(new Boid(random(width), random(height)));
		}
		player = new Boid(100, 100);
		player.vehicleColor = color(255, 0, 0);
		boids.add(player);
		
		mouse = new PVector(mouseX, mouseY);
	}

	@Override public void draw() {
		mouse.x = mouseX; mouse.y = mouseY;
		background(200);

		for (Boid b : boids) {
			if (b == player) {
				b.applyForce(b.seek(mouse));
			} else {
				b.flock(boids);
			}
			
			b.bounce(0, width, 0, height);
			b.update();
			b.display();
		}
		
		fill(0);
		text("\nFlocking by Plasmoxy in Processing\nTHE WILL FLY WITH YOU ;)\npress esc to exit faggit\nthe red guy follows mouse", 50, 50);
	}
}
