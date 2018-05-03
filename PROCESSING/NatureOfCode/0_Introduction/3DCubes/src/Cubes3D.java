import processing.core.PApplet;

public class Cubes3D extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Cubes3D");
	}

	@Override public void settings() {
		size(640, 360, P3D);
	}

	@Override public void setup() {
		noStroke();
	}

	@Override public void draw() {
		lights();
		background(0);
		float cameraY = height/2.0f;
		float fov = mouseY/parseFloat(width) * PI/2f;
		float cameraZ = cameraY / tan(fov / 2.0f);
		float aspect = parseFloat(width)/parseFloat(height);
		perspective(fov, aspect, cameraZ/10.0f, cameraZ*10.0f);

		translate(width/2, height/2, 0);
		rotateX(-PI/6);
		rotateY(PI/3 + mouseX/parseFloat(height) * PI);
		box(45);
		translate(0, 0, -50);
		box(30);
	}
}
