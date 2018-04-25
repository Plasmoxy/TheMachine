package io.github.plasmoxy.libgdx.test3d;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

public class GameEnvironment extends Environment {
	
	public GameEnvironment() {

		set(new ColorAttribute(
				ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f
		));
		
	}
	
}
