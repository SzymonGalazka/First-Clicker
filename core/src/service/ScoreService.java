package service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.TimeUtils;

public class ScoreService {

    public final static String GAME_PREFS = "com.pl.firstclicker.prefs";
    public final static String GAME_SCORE = "com.pl.firstclicker.prefs.score";
    public final static String GAME_PASSIVE_INCOME = "com.pl.firstclicker.prefs.passiveincome";
    public final static String GAME_CLICK_VALUE = "com.pl.firstclicker.prefs.clickvalue";
    public final static String GAME_SAVED_TIMESTAMP = "com.pl.firstclicker.prefs.savedtimestamp";

    private Preferences prefs;
    private float points;
    private float passiveIncome;
    private float clickvalue = BalanceService.getMoneyClickValue();

    public ScoreService(){
        init();
    }


    private void init() {
        prefs = Gdx.app.getPreferences(GAME_PREFS);
        loadScore();
        loadPassiveIncome();
        loadClickValue();
    }


    private void loadScore() {
        points = prefs.getFloat(GAME_SCORE);
    }
    private void loadPassiveIncome() {
        passiveIncome = prefs.getFloat(GAME_PASSIVE_INCOME);
    }
    private void loadClickValue(){ clickvalue = prefs.getFloat(GAME_CLICK_VALUE);}

    public void addPoint(){
        points += clickvalue;
    }
    public void addPoints(float pointsToAdd){
        points += pointsToAdd;
    }

    public void addPassiveIncome() {
        passiveIncome+=clickvalue;
    }

    public void addPassiveIncome(float income){
        passiveIncome+=income;
    }

    public void resetGameScore() {
        points = 0;
        passiveIncome = 0;
        clickvalue = BalanceService.getMoneyClickValue();
    }

    public float getPoints() {
        return points;
    }

    public void setClickvalue(float clickvalue) {
        this.clickvalue = clickvalue;
    }

    public  float getClickvalue() {
        return clickvalue;
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
        prefs.putFloat(GAME_CLICK_VALUE,clickvalue);
        prefs.putFloat(GAME_PASSIVE_INCOME,passiveIncome);
        prefs.flush();
    }
}
