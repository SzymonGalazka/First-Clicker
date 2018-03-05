package ui;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pl.firstclicker.FirstClickerGame;


import java.util.ArrayList;

import entities.ShopItem;


public class ShopMenu extends Table {
    private ScrollPane scrollPane;
    private ArrayList<ShopItem> shopItems = new ArrayList<ShopItem>();
    private boolean shopHidden;

    public ShopMenu() {
        super();
        this.setWidth(FirstClickerGame.WIDTH-100f);
        this.setHeight(FirstClickerGame.HEIGHT-600f);
        this.setPosition(50,300);
        this.setVisible(false);
        show();
    }

    private void show(){

        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));
        shopItems.add(new ShopItem("shopIcon.png","Title","Description"));


        Table innerContainer = new Table();
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
