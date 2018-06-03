package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import ui.IClickCallback;
import ui.ShopBuyButton;

public class ShopItem extends Table{
    Texture texture;
    String title, description, texturePath;
    Label.LabelStyle labelStyle;
    ShopBuyButton x1, x10;

    public ShopItem(String texturePath, String title, String description) {
        this.texturePath = texturePath;
        this.title = title;
        this.description = description;
        prepareContents();
        displayItem();
    }

    private void displayItem() {
        this.add(new Image(texture)).width(150f).height(150f);
        this.add(new Label(" ",labelStyle)).width(100f).expandY().fillY();
        this.add(new Label(title,labelStyle)).width(10f).expandY().fillY();
        this.add(new Label(" ",labelStyle)).width(50f).expandY().fillY();
        this.add(new Label(description,labelStyle)).width(10f).expandY().fillY();
        this.add(new Label(" ",labelStyle)).width(140f).expandY().fillY();
        this.add(x1 = new ShopBuyButton(false,new IClickCallback() {
            @Override
            public void onClick() {
                System.out.println("klikam sobie");
                x1.setDisabled(true);
            }
        })).width(150f).height(35f).padTop(-100f);
        this.add(new ShopBuyButton(true,new IClickCallback() {
            @Override
            public void onClick() {
                System.out.println("klikam sobie x10");
            }
        })).width(150f).height(35f).padLeft(-150f);
    }


    private void prepareContents() {
        texture = new Texture(texturePath);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("scriptfont.fnt"));
    }
}
