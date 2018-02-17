package screens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pl.firstclicker.FirstClickerGame;

import controllers.FlyingObjectController;
import entities.Player;
import service.PassiveIncomeService;
import ui.IClickCallback;
import ui.PlayerButton;
import ui.ResetScoreButton;
import ui.ScoreLabel;

public class GameplayScreen extends AbstractScreen{

    private Image bgImg;
    private Player player;
    private PlayerButton playerButton;
    private Button resetScoreButton;
    private ScoreLabel scoreLabel;
    private FlyingObjectController flyingObjectController;
    private PassiveIncomeService passiveIncomeService;

    public GameplayScreen(FirstClickerGame game) {
        super(game);
    }

    @Override
    protected void init(){
        initBg();
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        initScoreLabel();
        initFlyingStuffController();
        initPassiveIncomeService();
    }

    @Override
    public void render(float delta){
        super.render(delta);
        update();

        //System.out.println("Points:  "+game.getPoints());
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void pause() {
        super.pause();
        game.getScoreService().saveCurrentGameState();

    }

    private void update() {
        scoreLabel.setText("POINTS: "+game.getScoreService().getPoints());
        stage.act();
    }

    private void initPassiveIncomeService() {
        passiveIncomeService = new PassiveIncomeService(game.getScoreService());
        passiveIncomeService.start();
    }

    private void initFlyingStuffController() {
        flyingObjectController = new FlyingObjectController(game, stage);
    }

    private void initBg() {
        bgImg = new Image(new Texture("bg.png"));
        bgImg.setSize(FirstClickerGame.WIDTH,FirstClickerGame.HEIGHT);
        stage.addActor(bgImg);
}

    private void initScoreLabel() {
        scoreLabel = new ScoreLabel();
        stage.addActor(scoreLabel);
    }

    private void initResetScoreButton() {
        resetScoreButton = new ResetScoreButton(new IClickCallback() {
            @Override
            public void onClick() {
                game.getScoreService().resetGameScore();
            }
        });
        stage.addActor(resetScoreButton);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.getScoreService().addPoint();
            }
        });
        stage.addActor(playerButton);
    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);
    }

}
