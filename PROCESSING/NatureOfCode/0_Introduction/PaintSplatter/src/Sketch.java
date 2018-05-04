import processing.core.PApplet;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.*;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	Runnable recordTask = () -> {
		saveFrame("S:/PFRAMES/gausspaint/gausspaint-######.png");
	};
	
	Timer t = new Timer();
	
	Random rand = new Random();

	@Override public void settings() {
		size(1280, 720);
	}

	@Override public void setup() {
		frameRate(1000);
		//executor.scheduleAtFixedRate(recordTask, 0, 16, TimeUnit.MILLISECONDS);
	}

	@Override public void draw() {
		float ny = (float) rand.nextGaussian();
		float nx = (float) rand.nextGaussian();
		
		// standard deviation
		float sdy = 100;
		float sdx = 100;
		
		float meany = height/2;
		float meanx = width/2;
		
		float y = ny * sdy + meany;
		float x = nx * sdx + meanx;
		
		noStroke();
		fill(0, 20);
		ellipse(x, y, 10, 10);
	}
}
