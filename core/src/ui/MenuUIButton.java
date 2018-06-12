package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuUIButton extends Button {

    public MenuUIButton(boolean r,IClickCallback callback){
        super(prepareMenuButtonStyle(r));
        init(callback);
    }

    private void init(final IClickCallback callback) {
        this.setWidth(140);
        this.setHeight(35);

        this.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                callback.onClick();
                return super.touchDown(event, x, y, pointer, button);
            }

        });
    }

    private static ButtonStyle prepareMenuButtonStyle(boolean r){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buttons.atlas"));
        Skin skin = new Skin(atlas);
        ButtonStyle buttonStyle = new ButtonStyle();
        if(r) buttonStyle.up = skin.getDrawable("resetButton");
        else buttonStyle.up = skin.getDrawable("exitButton");
        return buttonStyle;
    }
}
