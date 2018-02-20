package com.pl.firstclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;

import screens.SplashScreen;
import service.FeatureFlagService;
import service.ScoreService;

public class FirstClickerGame extends Game {

	public final static String GAME_NAME = "First Clicker";

	public static int WIDTH = 720;
	public static int HEIGHT = 1280;

	public static Color pinky = new Color(192f/255f, 132f/255f, 151f/255f, 255f/255f);
	private ScoreService scoreService;
	private FeatureFlagService featureFlagService;

	private boolean paused;


	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this) {
		});

	}

	private void init(){
		initScoreService();
		initFeatureFlagService();
	}

	private void initFeatureFlagService() {
		featureFlagService = new FeatureFlagService();
	}

	private void initScoreService() {
		scoreService = new ScoreService();
	}

// GETTERS AND SETTERS

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public ScoreService getScoreService() {
		return scoreService;
	}

	public FeatureFlagService getFeatureFlagService() {
		return featureFlagService;
	}
}
