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
    private float points;
    private float passiveIncome;

    public ScoreService(){
        init();
    }


    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScore();
        loadPassiveIncome();
    }


    private void loadScore() {
        points = prefs.getFloat(GAME_SCORE);
    }

    private void loadPassiveIncome() {
        passiveIncome = prefs.getFloat(GAME_PASSIVE_INCOME);
    }

    public void addPoint(){
        points++;
    }
    public void addPoints(float pointsToAdd){
        points += pointsToAdd;
    }

    public void addPassiveIncome() {
        passiveIncome++;
    }

    public void addPassiveIncome(float income){
        passiveIncome+=income;
    }

    public void resetGameScore() {
        points = 0;
        passiveIncome = 0;
    }

    public float getPoints() {
        return points;
    }
    public float getPassiveIncome() {
        return passiveIncome;
    }

    public long getSavedTimestamp(){
        return prefs.getLong(GAME_SAVED_TIMESTAMP);
    }

    public void saveCurrentGameState() {
        prefs.putLong(GAME_SAVED_TIMESTAMP,TimeUtils.millis());
        prefs.putFloat(GAME_SCORE,points);
        prefs.putFloat(GAME_PASSIVE_INCOME,passiveIncome);
        prefs.flush();
    }
}
