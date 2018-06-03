package ui;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pl.firstclicker.FirstClickerGame;

public class ShopButton extends Button{

    public ShopButton(IClickCallback callback) {
        super(new ButtonStyle());
        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setWidth(FirstClickerGame.WIDTH/3);
        this.setHeight(150);
        this.setX(0);
        this.setY(1124);
        this.setDebug(false);

        this.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }

        });
    }

}
