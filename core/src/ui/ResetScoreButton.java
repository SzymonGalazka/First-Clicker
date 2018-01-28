package ui;


import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ResetScoreButton extends Button {

    public ResetScoreButton(final IClickCallback callback) {
        super(new ButtonStyle());
        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setWidth(50);
        this.setHeight(25);
        this.setX(400);
        this.setY(650);
        this.setDebug(true);

        this.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("Click");
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }

        });
    }
}
