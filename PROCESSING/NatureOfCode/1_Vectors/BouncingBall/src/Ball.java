import processing.core.PApplet;
import processing.core.PVector;

public class Ball {
	
	PApplet p;
	
	PVector pos = new PVector();
	PVector vel = new PVector();
	
	float radius = 20;
	
	Ball(PApplet applet, float x, float y) {
		p = applet;
		pos.x = x;
		pos.y = y;
		
		vel.x = 5;
		vel.y = 6;
	}
	
	void draw() {
		p.pushStyle();
		p.noStroke();
		p.fill(0);
		p.ellipse(pos.x, pos.y, 2*radius, 2*radius);
		p.popStyle();
	}
	
	void move() {
		
		if (pos.x < radius || pos.x > p.width - radius) {
			vel.x *= -1; // invert velocity
		}
		
		if (pos.y < radius || pos.y > p.height - radius) {
			vel.y *= -1;
		}
		
		pos.x += vel.x;
		pos.y += vel.y;
	}
	
}
