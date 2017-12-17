package screens;


import com.pl.firstclicker.FirstClickerGame;

import entities.Player;

public class GameplayScreen extends AbstractScreen{

    private Player player;

    public GameplayScreen(FirstClickerGame game) {
        super(game);
        init();
    }

    public void init(){
        initPlayer();
    }

    private void initPlayer() {
        player = new Player();
        stage.addActor(player);
    }
    @Override
    public void render(float delta){
        super.render(delta);
        update();
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        stage.act();
    }
}
