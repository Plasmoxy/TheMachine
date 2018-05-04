import processing.core.PApplet;

import java.util.Random;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	Random random = new Random();
	

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		
	}

	@Override public void draw() {
		float num = (float) random.nextGaussian();
		float standard_deviation = 60;
		float mean = 200;
		
		float x = standard_deviation * num + mean;
		
		noStroke();
		fill(0, 10);
		ellipse(x, 200, 16, 16);
	}
}
