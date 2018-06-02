package ui;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pl.firstclicker.FirstClickerGame;

public class ResetScoreButton extends Button {

    public ResetScoreButton(final IClickCallback callback) {
        super(new ButtonStyle());
        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setWidth(FirstClickerGame.WIDTH/3);
        this.setHeight(150);
        this.setX(2*FirstClickerGame.WIDTH/3);
        this.setY(1124);
        this.setDebug(false);
        this.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("Click");
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }

        });
    }

}
