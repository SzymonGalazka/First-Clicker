package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;

public class ScoreService {

    public final static String GAME_PREFS = "com.pl.firstclicker.prefs";
    public final static String GAME_SCORE = "com.pl.firstclicker.prefs.score";
    public final static String GAME_PASSIVE_INCOME = "com.pl.firstclicker.prefs.passiveincome";
    public final static String GAME_SAVED_TIMESTAMP = "com.pl.firstclicker.prefs.savedtimestamp";

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
    }
    public void addPoints(int pointsToAdd){
        points += pointsToAdd;
    }

    public void addPassiveIncome() {
        passiveIncome++;
    }

    public void resetGameScore() {
        points = 0;
        passiveIncome = 0;
    }

    public int getPoints() {
        return points;
    }
    public int getPassiveIncome() {
        return passiveIncome;
    }

    public long getSavedTimestamp(){
        return prefs.getLong(GAME_SAVED_TIMESTAMP);
    }

    public void saveCurrentGameState() {
        prefs.putLong(GAME_SAVED_TIMESTAMP,TimeUtils.millis());
        prefs.putInteger(GAME_SCORE,points);
        prefs.putInteger(GAME_PASSIVE_INCOME,passiveIncome);
        prefs.flush();
    }
}
