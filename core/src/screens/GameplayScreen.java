package screens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pl.firstclicker.FirstClickerGame;

import controllers.FlyingStuffController;
import entities.Player;
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
    private FlyingStuffController flyingStuffController;

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
    }

    private void initFlyingStuffController() {
        flyingStuffController = new FlyingStuffController(game, stage);
    }

    private void initBg() {
        bgImg = new Image(new Texture("bg.png"));
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
                game.resetGameScore();
            }
        });
        stage.addActor(resetScoreButton);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {
                player.reactOnClick();
                game.addPoint();
            }
        });
        stage.addActor(playerButton);
    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);
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

    private void update() {
        scoreLabel.setText("Points:"+game.getPoints());
        stage.act();
    }
}
