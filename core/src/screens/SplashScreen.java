package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.pl.firstclicker.FirstClickerGame;
import com.pl.firstclicker.IRequestCallback;

public abstract class SplashScreen extends AbstractScreen{

    private Texture splashImage, noInternetImage;
    private boolean showError = false;

    public SplashScreen(final FirstClickerGame game) {
        super(game);
        init();
    }

    protected void init(){
        //todo implement better assets loading when game grows
        splashImage = new Texture("SplashScreen.png");
        noInternetImage = new Texture(("NoInternet.png"));


        game.getFeatureFlagService().makeFeatureFlagRequest(new IRequestCallback() {
            @Override
            public void onSucceed() {
                showError = false;
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new GameplayScreen(game));
                    }
                });

            }

            @Override
            public void onError() {
                showError = true;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        if(showError){
            spriteBatch.draw(noInternetImage,0,0);
        }else{
            spriteBatch.draw(splashImage,0,0);}
        spriteBatch.end();
    }
}
