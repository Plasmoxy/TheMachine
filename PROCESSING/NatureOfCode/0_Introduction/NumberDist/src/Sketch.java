import processing.core.PApplet;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	int[] randomCounts;
	

	@Override public void settings() {
		size(640, 240);
	}

	@Override public void setup() {
		randomCounts = new int[20];
	}

	@Override public void draw() {
		background(255);
		
		int index = parseInt(random(randomCounts.length));
		randomCounts[index]++;
		
		stroke(0);
		fill(175);
		int w = width/randomCounts.length;
		
		for (int i = 0; i < randomCounts.length; i++) {
			rect(i*w, height-randomCounts[i], w-1, randomCounts[i]);
		}
		
	}
}
