package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import service.ShopService;
import ui.IClickCallback;
import ui.ShopBuyButton;

public class ShopItem extends Table{


    private Texture texture;
    private String title,descr, texturePath, itemType;
    private Label.LabelStyle labelStyle, bigLabelStyle;
    private float price,toAdd;
    private ShopBuyButton x1, x10;

    public ShopItem(String texturePath, String title,String descr, float price, float toAdd, String itemType) {
        this.texturePath = texturePath;
        this.title = title;
        this.descr = descr;
        this.price = price;
        this.toAdd = toAdd;
        this.itemType = itemType;
        prepareContents();
        displayItem();
    }

    private void displayItem() {
        this.add(new Image(texture)).width(100f).height(100f);
        this.add(new Label(" ",labelStyle)).width(30f).expandY().fillY();
        this.add(new Label(title,bigLabelStyle)).width(100f).height(260f).padTop(-100f);
        this.add(new Label(" ",labelStyle)).width(160f).expandY().fillY();
        this.add(new Label((int)price+"\npierogies",bigLabelStyle)).width(10f).height(260f).padTop(-60f);
        this.add(new Label(descr,labelStyle)).width(100f).height(60f).padLeft(-440f).padTop(30f);
        this.add(new Label(" ",labelStyle)).width(140f).expandY().fillY();
        this.add(x1 = new ShopBuyButton(false,new IClickCallback() {
            @Override
            public void onClick() {
                if(!x1.isDisabled()) buyItem(1);
            }
        })).width(150f).height(35f).padTop(-100f);
        this.add(x10 = new ShopBuyButton(true,new IClickCallback() {
            @Override
            public void onClick() {
                if(!x10.isDisabled()) buyItem(10);
            }
        })).width(150f).height(35f).padLeft(-150f).padBottom(-40);

    }

    private void buyItem(int mul){
        if(itemType=="PASSIVE") ShopService.addPassiveIncomeFromShop(mul*toAdd,mul*price);
        else if(itemType=="POINTS") ShopService.addPointsFromShop(mul*toAdd,mul*price);
    }


    private void prepareContents() {
        texture = new Texture(texturePath);
        labelStyle = new Label.LabelStyle();
        bigLabelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("descfont.fnt"));
        bigLabelStyle.font = new BitmapFont(Gdx.files.internal("scriptfontbig.fnt"));
    }

    public float getPrice() {
        return price;
    }

    public ShopBuyButton getX1() {
        return x1;
    }

    public ShopBuyButton getX10() {
        return x10;
    }
}
