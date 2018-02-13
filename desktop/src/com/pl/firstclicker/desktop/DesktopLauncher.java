package com.pl.firstclicker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pl.firstclicker.FirstClickerGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = FirstClickerGame.GAME_NAME;
		config.width = 432;
		config.height = 768;
		config.resizable = false;
		config.samples = 10;

		new LwjglApplication(new FirstClickerGame(), config);
	}
}
