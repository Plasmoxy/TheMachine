import processing.core.PApplet;

import java.util.ArrayList;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	private float angle = 0;
	
	private ArrayList<Box> boxes = new ArrayList<>();
	
	private class Box {
		
		private float x, y, z, size;
		
		public Box(float _size, float _x, float _y, float _z) {
			x = _x;
			y = _y;
			z = _z;
			size = _size;
		}
		
		public void draw(float ang) {
			pushMatrix();
			translate(x, y, z);
			rotateX(ang);
			rotateY(ang);
			box(size);
			popMatrix();
		}
		
	}

	@Override public void settings() {
		size(800, 400, P3D);
	}

	@Override public void setup() {
		background(0);
		
		for (int xi = 0; xi < 1000; xi += 50) {
			for (int zi = 0; zi < 1000; zi += 50) {
				boxes.add(new Box(20*sin(xi/100f)*cos(zi/100f), xi, 0, zi));
			}
		}
	}

	@Override public void draw() {
		background(0);
		perspective();
		translate(width/2, height/2);
		translate(-500, 100, -200);
		rotateY(PI/4);
		for (Box b : boxes) {
			b.draw(angle);
		}
		
		angle += 0.01f;
	}
}
