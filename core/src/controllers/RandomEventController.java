package controllers;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.pl.firstclicker.PierogiClicker;

import ui.BasicDialog;

public class RandomEventController {

    public static final int RANDOM_TICK_INTERVAL = 15; //TODO change to higher value

    private PierogiClicker game;
    private Stage stage;

    public RandomEventController(PierogiClicker game, Stage stage){
        this.game = game;
        this.stage = stage;
        init();
    }

    public void init(){
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(MathUtils.randomBoolean() && !game.isPaused()){
                    triggerRandomEvent();
                }
            }
        },RANDOM_TICK_INTERVAL,RANDOM_TICK_INTERVAL);
    }

    private void triggerRandomEvent() {
        int randomNumber = MathUtils.random(1,3);
        switch(randomNumber){
            case 1:
                addMoneyEvent();
                break;
            case 2:
                if(game.getScoreService().getPoints()>150)  loseMoneyEvent();
                break;
            case 3:
                addPassiveIncome();
                break;
            default:
                break;
        }
    }

    private void triggerDialog(String text){
        BasicDialog basicDialog = new BasicDialog();
        basicDialog.showDialog(stage,text);
    }

    private void addPassiveIncome() {
        float val = game.getScoreService().getClickvalue()*20;
        game.getScoreService().addPassiveIncome(val);
        triggerDialog("Your grandma is \nfeeling better today,\nshe is making pierogies faster! \n (Passive income +"+val+")");
    }

    private void addMoneyEvent() {
        float val = game.getScoreService().getClickvalue()*200;
        game.getScoreService().addPoints(val);
        triggerDialog( "You just found  some \npierogies on the floor! \n(Pierogies +"+val+")");
    }
    private void loseMoneyEvent() {
        float val = game.getScoreService().getClickvalue()*-150;
        game.getScoreService().addPoints(val);
        triggerDialog( "You ruined\n some good pierogies! \n(Pierogies -"+val+")");
    }

}
