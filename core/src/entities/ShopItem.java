package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import service.ShopService;
import ui.IClickCallback;
import ui.ShopBuyButton;

public class ShopItem extends Table{

    private String title,descr, iconName, itemType;
    private Label.LabelStyle labelStyle, bigLabelStyle;
    private float price,toAdd;
    private ShopBuyButton x1, x10;

    public ShopItem(String iconName, String title,String descr, float price, float toAdd, String itemType) {
        this.iconName = iconName;
        this.title = title;
        if (itemType=="PASSIVE") this.descr = "(+"+toAdd+" passive income)\n"+descr;
        else if(itemType=="POINTS") this.descr = "(+"+toAdd+" tap value)\n"+descr;
        this.price = price;
        this.toAdd = toAdd;
        this.itemType = itemType;
        prepareContents();
        displayItem();
    }

    private void displayItem() {
        this.add(new Label(" ",labelStyle)).width(30f).expandY().fillY();
        this.add(new Image(prepareIcon())).width(100f).height(100f);
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
        })).width(130f).height(40f).padTop(-90f);
        this.add(x10 = new ShopBuyButton(true,new IClickCallback() {
            @Override
            public void onClick() {
                if(!x10.isDisabled()) buyItem(10);
            }
        })).width(130f).height(40f).padLeft(-130f).padBottom(-40);

    }

    private void buyItem(int mul){
        if(itemType=="PASSIVE") ShopService.addPassiveIncomeFromShop(mul*toAdd,mul*price);
        else if(itemType=="POINTS") ShopService.addPointsFromShop(mul*toAdd,mul*price);
    }

    private TextureRegion prepareIcon(){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("shopIcons.atlas"));
        TextureRegion textureRegion = atlas.findRegion(iconName);
        return textureRegion;
    }

    private void prepareContents() {
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
