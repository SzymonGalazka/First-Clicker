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
        this.setWidth(100);
        this.setHeight(40);
        this.setX(540);
        this.setY(1150);
        this.setDebug(false);
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
