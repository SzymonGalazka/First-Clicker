package com.pl.firstclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import screens.SplashScreen;

public class FirstClickerGame extends Game {

	public final static String GAME_PREFS = "com.pl.firstclicker.prefs";
	public final static String GAME_SCORE = "com.pl.firstclicker.prefs.score";
	public final static String GAME_NAME = "First Clicker";

	public static int WIDTH = 480;
	public static int HEIGHT = 700;

	private boolean paused;
	private int points;
	private Preferences prefs;

	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this) {
		});
	}

	private void init(){
		prefs = Gdx.app.getPreferences(GAME_PREFS);
		loadScore();
	}

	private void loadScore() {
		points = prefs.getInteger(GAME_SCORE);
	}

	public void addPoint(){
		points++;
		storeGameScore();
	}
	public void addPoints(int pointsToAdd){
		points += pointsToAdd;
		storeGameScore();
	}

	public void addPassiveIncome() {
		//TODO implement passive income
		System.out.println("passive income click");
	}

	public void resetGameScore() {
		points = 0;
		storeGameScore();
	}

	private void storeGameScore() {
		prefs.putInteger(GAME_SCORE,points);
		prefs.flush();
	}
// GETTERS AND SETTERS

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getPoints() {
		return points;
	}

}
