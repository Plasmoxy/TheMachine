import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	class Ball {
		
		PVector pos, vel, acceleration, target;
		
		Ball(float startx, float starty) {
			pos = new PVector(startx + random(-100, 100), starty + random(-100, 100));
			vel = new PVector();
			acceleration = new PVector();
			target = new PVector();
		}
		
		void move() {
			PVector direction = PVector.sub(target, pos);
			
			direction.normalize();
			acceleration.set(direction);
			vel.add(acceleration);
			vel.mult(0.993f);
			pos.add(vel);
		}
		
		void draw() {
			noStroke();
			fill(0, 200);
			ellipse(pos.x, pos.y, 1, 1);
		}
		
	}
	
	
	int maxBalls = 100000;
	int currentBalls = 30;
	ArrayList<Ball> balls = new ArrayList<>();
	HScrollbar ballScrollbar;


	@Override public void settings() {
		size(1000, 700);
	}

	@Override public void setup() {
		surface.setTitle("Dark gravity by Plasmoxy");
		frameRate(240);
		
		ballScrollbar = new HScrollbar(this, 50, 50, 500, 20, 1);
		ballScrollbar.newspos = ballScrollbar.sposMin;
	}

	@Override public void draw() {
		background(200);
		for (Ball b : balls) {
			b.target.x = mouseX;
			b.target.y = mouseY;
			b.move();
			b.draw();
		}
		
		ballScrollbar.update();
		ballScrollbar.display();
		
		currentBalls = parseInt(ballScrollbar.getVal(0, maxBalls));
		updateBalls();
		
		textSize(20);
		text("Ball count : " + parseInt(ballScrollbar.getVal(0, parseFloat(maxBalls))), 30, 30 );
	}
	
	void updateBalls() {
		
		while (balls.size() < currentBalls) {
			balls.add(new Ball(random(width), random(height)));
		}
		
		while (balls.size() > currentBalls) {
			balls.remove(balls.size() - 1);
		}
		
	}
}
