package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pl.firstclicker.FirstClickerGame;

public class ShopItem extends Table{
    Texture texture;
    String title, description, texturePath;
    Label.LabelStyle labelStyle;

    public ShopItem(String texturePath, String title, String description) {
        this.texturePath = texturePath;
        this.title = title;
        this.description = description;
        prepareContents();
        displayItem();
    }

    private void displayItem() {
        this.add(new Image(texture)).width(150f).height(150f);
        this.add(new Label(" ",labelStyle)).width(70f).expandY().fillY();
        this.add(new Label(title,labelStyle)).width(10f).expandY().fillY();
        this.add(new Label(" ",labelStyle)).width(50f).expandY().fillY();
        this.add(new Label(description,labelStyle)).width(10f).expandY().fillY();
        this.add(new Label(" ",labelStyle)).width(150f).expandY().fillY();
        this.add(new Label("BUY NOW",labelStyle)).width(20f).expandY().fillY();
    }


    private void prepareContents() {
        texture = new Texture(texturePath);
        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("scriptfont.fnt"));
        labelStyle.fontColor = FirstClickerGame.pinky;
    }
}
