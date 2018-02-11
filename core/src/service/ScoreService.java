package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ScoreService {

    public final static String GAME_PREFS = "com.pl.firstclicker.prefs";
    public final static String GAME_SCORE = "com.pl.firstclicker.prefs.score";
    public final static String GAME_PASSIVE_INCOME = "com.pl.firstclicker.prefs.passiveincome";

    private Preferences prefs;
    private int points;
    private int passiveIncome;

    public ScoreService(){
        init();
    }

    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScore();
        loadPassiveIncome();
    }

    private void loadScore() {
        points = prefs.getInteger(GAME_SCORE);
    }

    private void loadPassiveIncome() {
        passiveIncome = prefs.getInteger(GAME_PASSIVE_INCOME);
    }

    public void addPoint(){
        points++;
        storeGameScoreAndPassiveIncome();
    }
    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
        storeGameScoreAndPassiveIncome();
    }

    public void addPassiveIncome() {
        passiveIncome++;
        storeGameScoreAndPassiveIncome();
    }

    public void resetGameScore() {
        points = 0;
        passiveIncome = 0;
        storeGameScoreAndPassiveIncome();
    }

    private void storeGameScoreAndPassiveIncome() {
        prefs.putInteger(GAME_SCORE,points);
        prefs.putInteger(GAME_PASSIVE_INCOME,passiveIncome);
        prefs.flush();
    }

    public int getPoints() {
        return points;
    }
    public int getPassiveIncome() {
        return passiveIncome;
    }
}
