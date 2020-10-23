package com.mygdx.space.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.space.Space;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Space.TITLE;
		config.width = Space.WIDTH;
		config.height = Space.HEIGHT;
		new LwjglApplication(new Space(), config);
	}
}
