package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class ShopBuyButton extends Button {



    public ShopBuyButton(boolean multipleClick, IClickCallback callback) {
        super(prepareShopButtonStyle(multipleClick));
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
    private static ButtonStyle prepareShopButtonStyle(boolean multipleClick){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("buyButtons.atlas"));
        Skin skin = new Skin(atlas);
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        if(!multipleClick) {
            buttonStyle.up = skin.getDrawable("buyButton");
            buttonStyle.disabled = skin.getDrawable("buyGrayButton");
        }else {
            buttonStyle.up = skin.getDrawable("buy10Button");
            buttonStyle.disabled = skin.getDrawable("buy10GrayButton");
        }
        return buttonStyle;
    }


}
