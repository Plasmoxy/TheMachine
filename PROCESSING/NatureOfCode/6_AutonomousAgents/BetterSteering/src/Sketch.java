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
		
		float r;
		boolean active;

		float maxspeed = 5f;
		float maxforce = 1f;

		Vehicle(float x, float y, float radius) {
			super(x, y);
			desv = new PVector();
			steeringForce = new PVector();
			r = radius;
		}
		
		void seek(PVector target) {
			desv.set(target);
			desv.sub(pos);
			
			desv.normalize();
			desv.mult(maxspeed);
		}

		void flee(PVector target) {
			desv.set(target);
			desv.sub(pos);

			desv.normalize();
			desv.mult(-maxspeed);
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
			float theta = vel.heading() + PI/2;
			if (active) fill(255, 0, 0);
			else fill(0);
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
	
	PVector mouse;
	List<Vehicle> vehicles = new LinkedList<>();
	float chance = 0.5f;

	@Override public void settings() {
		fullScreen();
	}

	@Override public void setup() {
		
		mouse = new PVector(mouseX, mouseY);
		
		for (int i = 0; i<20; i++) {
			Vehicle v = new Vehicle(random(width), random(height), 5);
			v.maxforce = random(0.1f, 0.2f);
			vehicles.add(v);
		}
	}

	@Override public void draw() {
		mouse.x = mouseX; mouse.y = mouseY;
		background(200);
		
		fill(0, 255, 0);
		noStroke();
		ellipse(mouseX, mouseY, 30, 30);
		
		for (Vehicle v : vehicles) {
			
			v.active = chance != 0.5f;
			
			// 48% chance that the will flee
			if (random(0, 1) < chance) {
				v.seek(mouse);
			}
			else {
				v.flee(mouse);
			}
			v.update();
			v.display();
		}
		
		textSize(20);
		fill(0);
		text("chance to follow mouse = "+ chance
				+"\nHold right button to call them home ;)"
				+"\nLeft button to scare them away :("
				+"\nesc to exit"
		,50, 50);
		
	}
	
	@Override public void mousePressed() {
		if (mouseButton == LEFT) {
			chance = 0.7f;
		} else {
			chance = 0.3f;
		}
		
	}
	
	@Override public void mouseReleased() {
		chance = 0.5f;
	}
}
