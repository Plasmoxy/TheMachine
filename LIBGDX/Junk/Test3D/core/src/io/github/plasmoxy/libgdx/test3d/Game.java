package io.github.plasmoxy.libgdx.test3d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Gdx.*;
import static com.badlogic.gdx.math.MathUtils.*;

public class Game extends ApplicationAdapter
implements InputProcessor
{
	
	private GameCamera camera;
	private ModelBatch modelBatch;
	private ModelBuilder modelBuilder;
	private Environment environment;
	
	public Map<String, Model> models = new HashMap<String, Model>();
	public Map<String, Entity> entities = new HashMap<String, Entity>();
	
	private FrameRate frameRate;
	
	
	private Entity bottomPlane;
	public Player plr;

	private Entity cyanBox;
	private Entity orangeBox;
	
	
	private float oscill_angle = 0f;
	
	@Override
	public void create () {
		
		// disable cursor
		Pixmap pm = new Pixmap(16 , 16, Pixmap.Format.RGBA8888);
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
		pm.dispose();
		
		// setup input
		input.setInputProcessor(this);
		
		// setup camera
		camera = new GameCamera();

		// setup environment and lighting
		environment = new GameEnvironment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		// add fps meter
		frameRate = new FrameRate();
		
		// some model utilities
		modelBatch = new ModelBatch();
		modelBuilder = new ModelBuilder();
		
		// modelz
		models.put("bottomPlaneModel", modelBuilder.createBox(
				5f, 0.1f, 5f,
				new Material(ColorAttribute.createDiffuse(Color.GRAY)),
				VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal
		));

		models.put("cyanBoxModel", modelBuilder.createBox(
				1f, 1f, 1f,
				new Material(ColorAttribute.createDiffuse(Color.CYAN)),
				VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal
		));

		models.put("orangeBoxModel", modelBuilder.createBox(
				1f, 1f, 1f,
				new Material(ColorAttribute.createDiffuse(Color.ORANGE)),
				VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal
		));
		
		// -- GAME --
		plr = new Player(Vector3.Zero, camera);
		plr.setPos(0, 3, 5);
		camera.rotate(Vector3.X, -30f);
		
		bottomPlane = new Entity(Vector3.Zero, new ModelInstance(models.get("bottomPlaneModel")));
		entities.put("bottomPlane", bottomPlane);

		cyanBox = new Entity(new Vector3(1, 1, 0), new ModelInstance(models.get("cyanBoxModel")));
		entities.put("cyanBox", cyanBox);

		orangeBox = new Entity(new Vector3(-1, 1, 2), new ModelInstance(models.get("orangeBoxModel")));
		entities.put("orangeBox", orangeBox);
		
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		frameRate.update();
		frameRate.render();
		
		camera.viewportHeight = graphics.getHeight();
		camera.viewportWidth = graphics.getWidth();
		
		// === CUSTOM ===
		
		
		cyanBox.setPos(2*sinDeg(oscill_angle), 1, 2*cosDeg(oscill_angle));
		
		float cang = oscill_angle + 180f;
		orangeBox.setPos(2*sinDeg(cang), 1, 2*(cosDeg(cang)));
		
		oscill_angle += 180*dt;
		
		cyanBox.addRot(180*dt, 90*dt, 70*dt);
		orangeBox.addRot(110*dt, 130*dt, 98*dt);
		
		// === RENDER MODELS ===

		camera.update();
		modelBatch.begin(camera);

		for (String key : entities.keySet()) {
			Entity e = entities.get(key);
			if (e.modeli != null) modelBatch.render(e.modeli, environment);
		}
		
		modelBatch.end();
		
	}
	
	@Override
	public void dispose () {
		for (String key : models.keySet()) {
			models.get(key).dispose();
		}
		modelBatch.dispose();
	}
	
	// window resized
	
	@Override
	public void resize(int w, int h) {
		camera.viewportWidth = graphics.getWidth();
		camera.viewportHeight = graphics.getHeight();
	}
	
	// -------- INPUT ----------

	@Override
	public boolean keyDown(int keycode) {
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
