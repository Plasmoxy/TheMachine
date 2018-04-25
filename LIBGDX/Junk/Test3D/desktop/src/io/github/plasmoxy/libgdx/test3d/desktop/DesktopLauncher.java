package io.github.plasmoxy.libgdx.test3d.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.plasmoxy.libgdx.test3d.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		new LwjglApplication(new Game(), new DesktopConfig());
	}
}
