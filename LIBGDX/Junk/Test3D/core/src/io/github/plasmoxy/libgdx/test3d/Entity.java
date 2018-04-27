package io.github.plasmoxy.libgdx.test3d;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Entity {
	
	// these shouldnt be accesible but whatever its ze POWER xDDD
	public ModelInstance modeli;
	public Vector3 pos = new Vector3();
	public Vector3 rot = new Vector3();
	
	// METHODS : Constructors
	
	public Entity(Vector3 position, ModelInstance modelInstance) {
		modeli = modelInstance;
		setPos(position);
	}
	
	// METHODS : Position
	
	public void setPos(Vector3 newPos) {
		pos.set(newPos);
		updateTranslation();
	}

	public void setPos(float x, float y, float z) {
		pos.x = x;
		pos.y = y;
		pos.z = z;
		updateTranslation();
	}
	
	public void addPos(Vector3 increment) {
		pos.add(increment);
		updateTranslation();
	}
	
	public Vector3 getPos() {
		return new Vector3(pos);
	}
	
	public void updateTranslation() {
		Vector3 dOrigin = modeli.transform.getTranslation(new Vector3()); // getTranslation MODIFIES object
		Vector3 dTranslation = new Vector3(pos).sub(dOrigin); // MODIFIES newPos !!
		modeli.transform.translate(dTranslation);
	}
	
	
	public String debug() {
		return
				"== Entity " + this.toString() + " ==\n"
				+"POSITION = " + pos.toString() + "\n"
				+"ROTATION = " + rot.toString() + "\n"
		;
	}
	
}
