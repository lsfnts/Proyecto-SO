package com.grupo2.proyecto.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.grupo2.proyecto.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                //config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
		new LwjglApplication(new Main(), config);
	}
}
