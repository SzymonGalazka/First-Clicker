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
    private Texture texture;
    private String title,descr, texturePath;
    private Label.LabelStyle labelStyle, bigLabelStyle;
    private int price;
    ShopBuyButton x1, x10;

    public ShopItem(String texturePath, String title,String descr, int price) {
        this.texturePath = texturePath;
        this.title = title;
        this.descr = descr;
        this.price = price;
        prepareContents();
        displayItem();
    }

    private void displayItem() {
        this.add(new Image(texture)).width(100f).height(100f);
        this.add(new Label(" ",labelStyle)).width(30f).expandY().fillY();
        this.add(new Label(title,bigLabelStyle)).width(100f).height(260f).padTop(-100f);
        this.add(new Label(" ",labelStyle)).width(160f).expandY().fillY();
        this.add(new Label(price+"\npierogies",bigLabelStyle)).width(10f).height(260f).padTop(-60f);
        this.add(new Label(descr,labelStyle)).width(100f).height(60f).padLeft(-440f).padTop(30f);
        this.add(new Label(" ",labelStyle)).width(140f).expandY().fillY();
        this.add(x1 = new ShopBuyButton(false,new IClickCallback() {
            @Override
            public void onClick() {
                System.out.println("klikam sobie"+x1);
            }
        })).width(150f).height(35f).padTop(-100f);
        this.add(x10 = new ShopBuyButton(true,new IClickCallback() {
            @Override
            public void onClick() {
                System.out.println("klikam sobie x10"+x10);
            }
        })).width(150f).height(35f).padLeft(-150f).padBottom(-40);

    }


    private void prepareContents() {
        texture = new Texture(texturePath);
        labelStyle = new Label.LabelStyle();
        bigLabelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("scriptfont.fnt"));
        bigLabelStyle.font = new BitmapFont(Gdx.files.internal("scriptfontbig.fnt"));
    }

    public int getPrice() {
        return price;
    }

    public ShopBuyButton getX1() {
        return x1;
    }

    public ShopBuyButton getX10() {
        return x10;
    }
}
