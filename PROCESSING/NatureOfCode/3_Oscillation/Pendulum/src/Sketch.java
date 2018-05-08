import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	class Pendulum {
		
		float r, angle;
		
		float aVelocity = 0.1f;
		float aAcceleration;
		
		PVector origin, pos;
		
		float gravity = 1f;
		
		Pendulum(float originX, float originY, float radius) {
			origin = new PVector(originX, originY);
			pos = new PVector();
			r = radius;
		}
		
		void update() {
			aAcceleration = ( -1 * gravity * sin(angle) )/r;
			aVelocity += aAcceleration;
			angle += aVelocity;
			
			pos.x = origin.x + r*sin(angle);
			pos.y = origin.y + r*cos(angle);
			
			stroke(0);
			fill(127);
			line(origin.x, origin.y, pos.x, pos.y);
			ellipse(pos.x, pos.y, 20, 20);
		}
		
	}
	
	Pendulum p;

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		p = new Pendulum(width/2, height/2, 100);
	}

	@Override public void draw() {
		background(200);
		p.update();
	}
}
