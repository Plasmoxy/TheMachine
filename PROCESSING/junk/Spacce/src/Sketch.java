import processing.core.PApplet;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}

	@Override public void settings() {
		size(400, 400, P3D);
	}

	@Override public void setup() {
		background(0);
	}
	
	
	private float angle = 0;
	
	public void renderEnv() {
		lights();
		perspective();
		translate(width/2, height/2, 100);
	}

	@Override public void draw() {
		background(0);
		renderEnv();
		rotateY(angle);
		rotateX(angle);
		box(100);
		angle += 0.02;
	}
}
