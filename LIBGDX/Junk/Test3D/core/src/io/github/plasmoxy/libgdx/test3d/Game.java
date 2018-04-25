package io.github.plasmoxy.libgdx.test3d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.Gdx.graphics;
import static com.badlogic.gdx.Gdx.input;

public class Game extends ApplicationAdapter
implements InputProcessor
{
	
	private PerspectiveCamera camera;
	private ModelBatch modelBatch;
	private ModelBuilder modelBuilder;
	private Environment environment;
	
	private Model redBoxModel;
	private Model cyanBoxModel;
	
	private Player plr;
	private ModelEntity box2;
	
	private FrameRate frameRate;
	
	private final Vector3 YAXIS = new Vector3(0f, 1f, 0f);
	
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
		camera.position.set(0f, 0f, 10f);
		camera.lookAt(0f, 0f, 0f);

		// setup environment and lighting
		environment = new GameEnvironment();
		
		// add fps meter
		frameRate = new FrameRate();
		
		// some model utilities
		modelBatch = new ModelBatch();
		modelBuilder = new ModelBuilder();
		
		// modelz
		redBoxModel = modelBuilder.createBox(
				2f, 2f, 2f,
				new Material(ColorAttribute.createDiffuse(Color.RED)),
				VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal
		);

		cyanBoxModel = modelBuilder.createBox(
				3f, 1f, 3f,
				new Material(ColorAttribute.createDiffuse(Color.CYAN)),
				VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal
		);

		// entities
		plr = new Player(new Vector3(0f, 0f, 0f), redBoxModel);
		box2 = new ModelEntity(new Vector3(1f, -2f, 0f), cyanBoxModel);
		
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
		
		plr.update(dt);

		camera.update();
		modelBatch.begin(camera);
		modelBatch.render(plr.modeli);
		modelBatch.render(box2.modeli);
		modelBatch.end();
		
	}
	
	@Override
	public void dispose () {
		redBoxModel.dispose();
		cyanBoxModel.dispose();
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
