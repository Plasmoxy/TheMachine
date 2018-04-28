package io.github.plasmoxy.libgdx.test3d.desktop;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopConfig extends LwjglApplicationConfiguration {
	
	public DesktopConfig() {

		Graphics.DisplayMode mode = getDesktopDisplayMode();
		fullscreen = true;
		samples = 4;
		width = mode.width;
		height = mode.height;
		foregroundFPS = 240;
		vSyncEnabled = true;
		
	}
	
}
