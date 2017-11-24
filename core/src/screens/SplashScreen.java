package screens;


import com.badlogic.gdx.graphics.Texture;
import com.pl.firstclicker.FirstClickerGame;

public abstract class SplashScreen extends AbstractScreen{

    private Texture splashImage;

    public SplashScreen(FirstClickerGame game) {
        super(game);
        init();
    }

    private void init(){
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
