package ui;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pl.firstclicker.PierogiClicker;

public class MainMenuButton extends Button {

    public MainMenuButton(final IClickCallback callback) {
        super(new ButtonStyle());
        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setWidth(PierogiClicker.WIDTH/3);
        this.setHeight(150);
        this.setX(2* PierogiClicker.WIDTH/3);
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
