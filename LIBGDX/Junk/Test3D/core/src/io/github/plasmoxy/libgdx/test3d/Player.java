package io.github.plasmoxy.libgdx.test3d;

import com.badlogic.gdx.math.Vector3;

public class Player extends Entity {
	
	public GameCamera cam;
	
	public Player(Vector3 pos, GameCamera camera) {
		super(pos);
		cam = camera;
	}
	
	public void updateCamera() {
		if (cam != null) cam.position.set(pos);
	}
	
	// METHODS : Override position
	
	@Override
	public void setPos(float x, float y, float z) {
		super.setPos(x, y, z);
		updateCamera();
	}
	
	@Override
	public void addPos(float dx, float dy, float dz) {
		super.addPos(dx, dy, dz);
		updateCamera();
	}
}
