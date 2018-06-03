package com.pl.firstclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;

import screens.SplashScreen;
import service.BalanceService;
import service.FeatureFlagService;
import service.ScoreService;
import service.ShopService;

public class FirstClickerGame extends Game {

	public final static String GAME_NAME = "First Clicker";

	public static int WIDTH = 720;
	public static int HEIGHT = 1280;

	public static Color pinky = new Color(192f/255f, 132f/255f, 151f/255f, 255f/255f);
	private ScoreService scoreService;
	private FeatureFlagService featureFlagService;
	private BalanceService balanceService;
	private ShopService shopService;

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
		initBalanceService();
		initShopService();
	}

	private void initShopService() {
		shopService = new ShopService(this);
	}

	private void initBalanceService() {
		balanceService = new BalanceService();
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

	public BalanceService getBalanceService() {
		return balanceService;
	}

	public ShopService getShopService() {
		return shopService;
	}
}
