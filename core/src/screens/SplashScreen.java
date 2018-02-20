package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pl.firstclicker.FirstClickerGame;
import com.pl.firstclicker.IRequestCallback;

public abstract class SplashScreen extends AbstractScreen{

    private Texture splashImage;

    public SplashScreen(final FirstClickerGame game) {
        super(game);
        init();
    }

    protected void init(){
        //todo implement better assets loading when game grows
        splashImage = new Texture("SplashScreen.png");

        game.getFeatureFlagService().makeFeatureFlagRequest(new IRequestCallback() {
            @Override
            public void onSucceed() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new GameplayScreen(game));
                    }
                });

            }

            @Override
            public void onError() {
                //TODO error message
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImage,0,0);
        spriteBatch.end();
    }
}
