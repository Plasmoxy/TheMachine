package io.github.plasmoxy.libgdx.test3d;

import com.badlogic.gdx.graphics.PerspectiveCamera;

import static com.badlogic.gdx.Gdx.graphics;

public class GameCamera extends PerspectiveCamera {
	
	public GameCamera() {
		super(75, graphics.getWidth(), graphics.getHeight());
		near = 0.1f;
		far = 300f;
		fieldOfView = 90;
	}
	
}
