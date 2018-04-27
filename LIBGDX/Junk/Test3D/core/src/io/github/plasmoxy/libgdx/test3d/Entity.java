package io.github.plasmoxy.libgdx.test3d;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Entity {
	
	public static final Vector3 NEGATIVE = new Vector3(-1f, -1f, -1f);
	
	// TODO: change access
	public ModelInstance modeli;
	public Vector3 pos = new Vector3();
	public Vector3 rot = new Vector3();
	
	public Entity(Vector3 position, ModelInstance modelInstance) {
		pos.set(position);
		modeli = modelInstance;
	}
	
	public void setPos(Vector3 newPos) {
		Vector3 dTranslation = modeli.transform.getTranslation(newPos);
		modeli.transform.translate(dTranslation.mul());
	}
	
}
