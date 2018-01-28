package screens;


import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pl.firstclicker.FirstClickerGame;

import entities.Player;
import ui.IClickCallback;
import ui.PlayerButton;

public class GameplayScreen extends AbstractScreen{

    private Player player;
    private PlayerButton playerButton;
    private Button resetScoreButton;
    private Label scoreLabel;

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
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont();
        scoreLabel = new Label("",labelStyle);
        scoreLabel.setX(20);
        scoreLabel.setY(650);
        stage.addActor(scoreLabel);
    }

    private void initResetScoreButton() {
        resetScoreButton = new Button(new ButtonStyle());
        resetScoreButton.setWidth(50);
        resetScoreButton.setHeight(25);
        resetScoreButton.setX(400);
        resetScoreButton.setY(650);
        resetScoreButton.setDebug(true);
        stage.addActor(resetScoreButton);

        resetScoreButton.addListener(new ClickListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.resetGameScore();
                return super.touchDown(event,x,y,pointer,button);
            }
        });
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
