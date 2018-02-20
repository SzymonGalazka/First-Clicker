package screens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pl.firstclicker.FirstClickerGame;

import controllers.FlyingObjectController;
import controllers.RandomEventController;
import entities.Player;
import service.PassiveIncomeService;
import ui.BasicDialog;
import ui.GameLabel;
import ui.IClickCallback;
import ui.PlayerButton;
import ui.ResetScoreButton;

public class GameplayScreen extends AbstractScreen{

    private Image bgImg;
    private Player player;
    private PlayerButton playerButton;
    private Button resetScoreButton;
    private GameLabel gameLabel;
    private RandomEventController randomEventController;
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
        initPassiveIncomeDialog();
        initRandomEventController();
    }

    private void initRandomEventController() {
        randomEventController = new RandomEventController(game,stage);
    }

    private void initPassiveIncomeDialog() {
        if(passiveIncomeService.getPointsToAdd()>0){
            BasicDialog basicDialog = new BasicDialog();
            basicDialog.showDialog(stage, "Passive income gained: " + passiveIncomeService.getPointsToAdd());
        }
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
        gameLabel.setText("PIEROGIES: "+game.getScoreService().getPoints());
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
        gameLabel = new GameLabel();
        stage.addActor(gameLabel);
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
