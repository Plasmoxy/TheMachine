/*
 * Ultra cool Entity class for LibGDX by Plasmoxy
 */

package io.github.plasmoxy.libgdx.test3d;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class Entity {
	
	// these shouldn't be accessible but whatever its ze POWER xDDD
	public ModelInstance modeli;
	public Vector3 pos = new Vector3();
	public Vector3 rot = new Vector3(); // EULER ANGLES ~ corresponding to default axes analogy
	
	// METHODS : Constructors
	
	public Entity(Vector3 position) {
		pos.set(position);
	}
	
	public Entity(Vector3 position, ModelInstance modelInstance) {
		this(position);
		modeli = modelInstance;
		updateTransform();
	}
	
	// METHODS : Util ? Position

	public void setPos(float x, float y, float z) {
		pos.x = x;
		pos.y = y;
		pos.z = z;
		updateTransform();
	}
	
	public void addPos(float dx, float dy, float dz) {
		pos.x += dx;
		pos.y += dy;
		pos.z += dz;
		updateTransform();
	}
	
	// METHODS : Util ? Rotation
	
	public void setRot(float x, float y, float z) {
		rot.x = x;
		rot.y = y;
		rot.z = z;
		updateTransform();
	}
	
	public void addRot(float dx, float dy, float dz) {
		rot.x += dx;
		rot.y += dy;
		rot.z += dz;
		updateTransform();
	}
	
	// METHODS : Internal
	
	// updates translation of model instance
	public void updateTransform() {
		if (modeli != null ) {
			modeli.transform.setToTranslation(pos);
			modeli.transform.rotate(1, 0, 0, rot.x);
			modeli.transform.rotate(0, 1, 0, rot.y);
			modeli.transform.rotate(0, 0, 1, rot.z);
		}
	}
	
	
	public String debug() {
		return
				"== Entity " + this.toString() + " ==\n"
				+"POSITION = " + pos.toString() + "\n"
				+"ROTATION = " + rot.toString() + "\n"
		;
	}
	
}
