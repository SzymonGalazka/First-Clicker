package screens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.pl.firstclicker.FirstClickerGame;

public abstract class SplashScreen extends AbstractScreen{

    private Texture splashImage;

    public SplashScreen(final FirstClickerGame game) {
        super(game);
        init();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new GameplayScreen(game));
            }
        },2);
    }

    protected void init(){
        //todo implement better assets loading when game grows
        splashImage = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImage,0,0);
        spriteBatch.end();
    }
}
