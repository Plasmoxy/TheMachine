import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Iterator;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	class Particle {
		PVector p, v, a; // pos, vel, acceleration
		
		float lifespan;
		
		Particle(PVector position) {
			p = position.copy();
			v = new PVector();
			a = new PVector();
			lifespan = 255f;
		}
		
		void update() {
			lifespan -= 2f;
			v.add(a);
			p.add(v);
			a.mult(0);
		}
		
		void display() {
			stroke(0, lifespan);
			fill(100, lifespan);
			ellipse(p.x, p.y, 8, 8);
		}
		
		boolean isDead() {
			return lifespan < 0f;
		}
		
		void applyForce(PVector f) {
			a.add(f);
		}
		
	}
	
	class Confetti extends Particle {
		
		int pColor;
		
		Confetti(PVector pos) {
			super(pos);
			pColor = color(random(255), random(255), random(255));
			v.set(random(-2, 2), random(-2, 2));
		}

		@Override void display() {
			noStroke();
			fill(pColor, lifespan);
			ellipse(p.x, p.y, 8, 8);
		}
	}
	
	
	class ParticleSystem {

		ArrayList<Particle> particles;

		PVector gravity = new PVector(0, 0.05f);
		
		ParticleSystem() {
			particles = new ArrayList<>();
		}
		
		void addParticle(Particle p) {
			particles.add(p);
		}
		
		void run() {
			Iterator<Particle> it = particles.iterator();

			while (it.hasNext()) {
				Particle part = it.next();
				
				part.applyForce(gravity);
				part.update();
				if (part.isDead()) {
					it.remove();
				}
				part.display();
			}
		}
		
	}
	
	
	PVector mouse;
	ParticleSystem ps;

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		mouse = new PVector(mouseX, mouseY);
		ps = new ParticleSystem();
	}

	@Override public void draw() {
		mouse.x = mouseX; mouse.y = mouseY;
		background(200);
		
		ps.addParticle(new Confetti(mouse));
		ps.run();
	}
}
