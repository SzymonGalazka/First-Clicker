package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pl.firstclicker.FirstClickerGame;

import java.util.ArrayList;

import entities.ShopItem;


public class ShopMenu extends Table {
    private ScrollPane scrollPane;
    private ArrayList<ShopItem> shopItems = new ArrayList<ShopItem>();
    private boolean shopHidden;
    private Image shopBg;

    public ShopMenu() {
        super();
        this.setWidth(FirstClickerGame.WIDTH);
        this.setHeight(FirstClickerGame.HEIGHT-280f);
        this.setPosition(0,30);
        this.setVisible(false);
        showBackground();
        showContents();
    }
    private void showBackground(){
        shopBg = new Image(new Texture("shopBg.png"));
        this.setBackground(shopBg.getDrawable());
    }

    private void showContents(){
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));


        Table innerContainer = new Table();
        innerContainer.padBottom(20f);
        innerContainer.padTop(20f);
        for(int i=0;i<shopItems.size();i++){
            innerContainer.add(shopItems.get(i)).expand().fill();
            innerContainer.row();
        }
        scrollPane = new ScrollPane(innerContainer);
        this.add(scrollPane).fill().expand();

    }

    public void displayMenu(){
            if(shopHidden){
                this.setVisible(false);
                shopHidden = false;
            }else{
                this.setVisible(true);
                shopHidden = true;
            }
    }




}
