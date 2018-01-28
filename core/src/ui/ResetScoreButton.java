package ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ResetScoreButton extends Button {

    public ResetScoreButton(final IClickCallback callback) {
        super(prepareResetButtonStyle());
        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setWidth(50);
        this.setHeight(35);
        this.setX(400);
        this.setY(630);
        this.setDebug(true);
        this.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("Click");
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }

        });
    }
    private static ButtonStyle prepareResetButtonStyle(){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("resetButton.atlas"));
        Skin skin = new Skin(atlas);
        ButtonStyle buttonStyle = new ButtonStyle();
        buttonStyle.up = skin.getDrawable("ButtonUp");
        buttonStyle.down = skin.getDrawable("ButtonDown");
        return buttonStyle;
    }

}
