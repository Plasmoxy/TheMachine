import processing.core.PApplet;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	Ball b;
	

	@Override public void settings() {
		size(400, 400);
	}

	@Override public void setup() {
		b = new Ball(this, width/2, height/2);
	}

	@Override public void draw() {
		background(200);
		b.move();
		b.draw();
	}
}
