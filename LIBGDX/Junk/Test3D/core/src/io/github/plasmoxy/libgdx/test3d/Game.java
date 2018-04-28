package io.github.plasmoxy.libgdx.test3d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Gdx.*;
import static com.badlogic.gdx.math.MathUtils.cosDeg;
import static com.badlogic.gdx.math.MathUtils.sinDeg;

public class Game extends ApplicationAdapter
implements InputProcessor
{
	
	private GameCamera cam;
	private FirstPersonCameraController fpscontroller;
	private int mouseX;
	private int mouseY;
	private float rotSpeed = 0.2f;
	
	private boolean loading = true;
	
	private ModelBatch modelBatch;
	private ModelBuilder modelBuilder;
	private Environment environment;
	private AssetManager assets;
	
	public Map<String, Model> models = new HashMap<String, Model>();
	public Map<String, Entity> entities = new HashMap<String, Entity>();
	
	private FrameRate fpsmeter;
	
	private Entity bottomPlane;

	private Entity cyanBox;
	private Entity orangeBox;
	private Entity handBox;
	private Entity monkey, monkey2;
	
	// setup oscillator for 2 cubes
	private float oscill_angle;
	private float oscill_speed = 1f;
	
	@Override
	public void create () {

		// some utilities
		modelBatch = new ModelBatch();
		modelBuilder = new ModelBuilder();
		assets = new AssetManager();

		// setup cursor
		Pixmap pm = new Pixmap(16 , 16, Pixmap.Format.RGBA8888);
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
		pm.dispose();
		input.setCursorCatched(true);
		
		// setup input
		input.setInputProcessor(this);

		// setup first mouse position as camera rotation is relative to deltamouse and it can break
		mouseX = input.getX();
		mouseY = input.getY();


		// setup environment and lighting
		environment = new GameEnvironment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		// setup camera
		cam = new GameCamera();
		
		// add ze fps meter
		fpsmeter = new FrameRate();
		fpsmeter.text[0] = "=== FPSMonkey by Plasmoxy - LibGDX(Java, cross-platform) ===\n( look with mouse/touch,move with wasd/secondtouch,\nscroll to change fov )";
		
		// load ze assets
		assets.load("models/tree.g3db", Model.class);
		assets.load("models/monkey.obj", Model.class);
		
	}
	
	private void doneLoading() {
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

		models.put("handBoxModel", modelBuilder.createBox(
				1f, 1f, 1f,
				new Material(ColorAttribute.createDiffuse(Color.GOLD)),
				VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal
		));

		models.put("monkeyModel", assets.get("models/monkey.obj", Model.class));
		models.put("treeModel", assets.get("models/tree.g3db", Model.class));

		// -- CUSTOM --
		cam.position.set(0, 3, 5);
		cam.rotate(Vector3.X, -30f);

		fpscontroller = new FirstPersonCameraController(cam);
		fpscontroller.setVelocity(2f);

		bottomPlane = new Entity(Vector3.Zero, new ModelInstance(models.get("bottomPlaneModel")));
		entities.put("bottomPlane", bottomPlane);

		cyanBox = new Entity(new Vector3(1, 1, 0), new ModelInstance(models.get("cyanBoxModel")));
		entities.put("cyanBox", cyanBox);

		orangeBox = new Entity(new Vector3(-1, 1, 2), new ModelInstance(models.get("orangeBoxModel")));
		entities.put("orangeBox", orangeBox);

		handBox = new Entity(new Vector3(), new ModelInstance(models.get("handBoxModel")));
		entities.put("handBox", handBox);

		monkey = new Entity(new Vector3(0, 1, 0), new ModelInstance(models.get("monkeyModel")));
		entities.put("monkey", monkey);
		monkey.modeli.transform.scale(0.5f, 0.5f, 0.5f);

		monkey2 = new Entity(new Vector3(0, 0, -200f), new ModelInstance(models.get("monkeyModel")));
		entities.put("monkey2", monkey2);
		monkey2.modeli.transform.scale(150f, 150f, 150f);
		
		Entity tree = new Entity(new Vector3(1.5f, 0.05f, -2), new ModelInstance(models.get("treeModel")));
		entities.put("tree", tree);
		tree.setRot(90f, 0, 0);
		tree.modeli.transform.scale(0.5f, 0.5f, 0.5f);
		
		loading = false; // STOHMP LOADING :))))
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();

		// clear matrix
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		
		if (loading) {
			if (assets.update()) {
				doneLoading();
			}
			return;
		}
		
		// === CUSTOM ===
		
		// boxez
		cyanBox.setPos(2*sinDeg(oscill_angle), 1, 2*cosDeg(oscill_angle));
		
		float cang = oscill_angle + 180f;
		orangeBox.setPos(2*sinDeg(cang), 1, 2*(cosDeg(cang)));
		
		oscill_angle += oscill_speed*90*dt;
		
		cyanBox.addRot(180*dt, 90*dt, 70*dt);
		orangeBox.addRot(110*dt, 130*dt, 98*dt);
		
		// display info to fpsmeter
		fpsmeter.text[6] = "CAM POSITION = " + cam.position;
		
		// === RENDER MODELS ===
		fpscontroller.update(dt);
		cam.update();
		modelBatch.begin(cam);

		for (String key : entities.keySet()) {
			Entity e = entities.get(key);
			if (e.modeli != null) modelBatch.render(e.modeli, environment);
		}

		modelBatch.end();

		// render frame rate on top
		fpsmeter.update();
		fpsmeter.render();
		
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
		cam.viewportWidth = w;
		cam.viewportHeight = h;
		fpsmeter.resize(w, h);
	}
	
	public void fpsRotate(int screenX, int screenY) {
		int magX = Math.abs(mouseX - screenX);
		int magY = Math.abs(mouseY - screenY);
		
		fpsmeter.text[4] = "mouseY=" + screenY;
		fpsmeter.text[5] = "mouseX=" + screenX;

		if (mouseX > screenX) {
			cam.rotate(Vector3.Y, 1 * magX * rotSpeed);
			cam.update();
		}

		if (mouseX < screenX) {
			cam.rotate(Vector3.Y, -1 * magX * rotSpeed);
			cam.update();
		}

		if (mouseY < screenY) {
			if (cam.direction.y > -0.965)
				cam.rotate(cam.direction.cpy().crs(Vector3.Y), -1 * magY * rotSpeed);
			cam.update();
		}

		if (mouseY > screenY) {

			if (cam.direction.y < 0.965)
				cam.rotate(cam.direction.cpy().crs(Vector3.Y), 1 * magY * rotSpeed);
			cam.update();
		}

		mouseX = screenX;
		mouseY = screenY;
	}
	
	// -------- INPUT ----------

	@Override
	public boolean keyDown(int keycode) {
		fpscontroller.keyDown(keycode);
		
		//oscill_speed += 1f;
		
		switch (keycode) {
			case Input.Keys.ESCAPE:
				app.exit();
				break;
			case Input.Keys.SHIFT_LEFT:
				fpscontroller.setVelocity(8f);
				break;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		fpscontroller.keyUp(keycode);

		switch (keycode) {
			case Input.Keys.SHIFT_LEFT:
				fpscontroller.setVelocity(2f);
				break;
		}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		switch (pointer) {
			case 0:
				// reset relativity if first touch
				mouseX = screenX;
				mouseY = screenY;
				break;
			case 1:
				// simulate W
				fpscontroller.keyDown(Input.Keys.W);
				break;
		}
		
		//oscill_speed += 1f;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		switch (pointer) {
			case 1:
				// simulate W
				fpscontroller.keyUp(Input.Keys.W);
				break;
		}


		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (!loading) {
			if ( pointer == 0) {
				fpsRotate(screenX, screenY);
			}
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if (!loading) {
			fpsRotate(screenX, screenY);
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		cam.fieldOfView += 2*amount;
		fpsmeter.text[7] = "FOV = " + cam.fieldOfView;
		return false;
	}
}
