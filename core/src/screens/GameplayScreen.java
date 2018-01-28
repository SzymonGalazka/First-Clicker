package screens;


import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.pl.firstclicker.FirstClickerGame;

import entities.Player;
import ui.IClickCallback;
import ui.PlayerButton;
import ui.ResetScoreButton;
import ui.ScoreLabel;

public class GameplayScreen extends AbstractScreen{

    private Player player;
    private PlayerButton playerButton;
    private Button resetScoreButton;
    private ScoreLabel scoreLabel;

    public GameplayScreen(FirstClickerGame game) {
        super(game);
    }

    @Override
    protected void init(){
        initPlayer();
        initPlayerButton();
        initResetScoreButton();
        initScoreLabel();
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
              //eturn super.touchDown(event,x,y,pointer,button);
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

        System.out.println("Points:  "+game.getPoints());
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        scoreLabel.setText("Points:"+game.getPoints());
        stage.act();
    }
}
