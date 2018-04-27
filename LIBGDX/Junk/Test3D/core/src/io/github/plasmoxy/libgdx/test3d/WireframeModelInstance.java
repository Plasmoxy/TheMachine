package io.github.plasmoxy.libgdx.test3d;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodePart;

public class WireframeModelInstance extends ModelInstance {
	
	public WireframeModelInstance(Model m) {
		super(m);
	}

	@Override
	public Renderable getRenderable(final Renderable out, final Node node,
									final NodePart nodePart) {
		super.getRenderable(out, node, nodePart);
		out.meshPart.primitiveType = GL20.GL_LINE_STRIP;
		return out;
	}
	
}
