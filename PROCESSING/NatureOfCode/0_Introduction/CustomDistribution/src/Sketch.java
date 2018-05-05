import processing.core.PApplet;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	class Walker {
		
		float x, y;
		int color;
		
		Walker(int color) {
			x = width/2;
			y = height/2;
			this.color = color;
		}
		
		void draw() {
			pushStyle();
			noStroke();
			fill(color);
			ellipse(x, y, 5, 5);
			popStyle();
		}
		
		void step() {
			float stepsize = 10*random(1);
			float stepx = random(-stepsize, stepsize);
			float stepy = random(-stepsize, stepsize);
			
			x += stepx;
			y += stepy;
		}
		
		void qualifiedStep() {
			float stepsize = 10*qualifiedDistribution();

			float stepx = random(-stepsize, stepsize);
			float stepy = random(-stepsize, stepsize);

			x += stepx;
			y += stepy;
		}
	}
	
	
	Walker walker, qualifiedWalker;

	@Override public void settings() {
		size(1000, 700);
	}

	@Override public void setup() { 
		walker = new Walker(color(0, 0, 0));
		qualifiedWalker = new Walker(color(0, 0, 155));
	}

	@Override public void draw() {
		background(100);
		walker.step();
		qualifiedWalker.qualifiedStep();
		
		walker.draw();
		qualifiedWalker.draw();
	}
	
	float qualifiedDistribution() {
		// the probability that a random number will be qualified is the random number itself
		// P = R1
		// the larger the number is, the more probably it will be qualified
		while (true) {
			float r1 = random(1);
			float p = r1; // probability
			float r2 = random(2); // we will qualify by this random value
			
			if (r2 < p) {
				return r1;
			}
			
		}
	}
	
}
