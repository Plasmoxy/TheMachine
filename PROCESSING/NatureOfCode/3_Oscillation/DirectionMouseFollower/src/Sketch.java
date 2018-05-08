import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	class Follower {
		
		PVector pos, vel, target;
		
		Follower(float x, float y, PVector target) {
			pos = new PVector(x, y);
			vel = new PVector();
			this.target = target;
		}
		
		void update() {
			PVector d = PVector.sub(target, pos);
			d.normalize();
			d.mult(5);
			vel.set(d);
			pos.add(vel);
		}
		
		void draw() {
			fill(0);
			noStroke();
			ellipse(pos.x, pos.y, 30, 30);
		}
		
	}
	
	PVector mouse;
	Follower f;

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		mouse = new PVector(mouseX, mouseY);
		f = new Follower(mouse.x, mouse.y, mouse);
		background(200);
	}

	@Override public void draw() {
		mouse.x = mouseX; mouse.y = mouseY;
		fill(200, 20);
		noStroke();
		rect(0, 0, width, height);

		f.update();
		f.draw();
		
	}
}
