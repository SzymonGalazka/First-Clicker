package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pl.firstclicker.FirstClickerGame;

import java.util.ArrayList;

import entities.ShopItem;


public class ShopMenu extends Table {

    public enum ItemType{
        POINTS,PASSIVE
    }

    private ScrollPane scrollPane;
    private ArrayList<ShopItem> shopItems = new ArrayList<ShopItem>();
    private boolean shopHidden;
    private Image shopBg;
    private static FirstClickerGame game;

    public ShopMenu(FirstClickerGame game) {
        super();
        this.game = game;
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
        shopItems.add(new ShopItem("shopIcon.png","Blue Energy drink","(+1 passive income)\nGives your grandma a solid boost!",20,1,"PASSIVE"));
        shopItems.add(new ShopItem("shopIcon.png","Red Energy drink","(+3 passive income)\nGives youur grandma enormous boost!",50,3,"PASSIVE"));
        shopItems.add(new ShopItem("shopIcon.png","Black Energy drink","(+10 passive income)\nGives your grandma incredible boost!",200,10,"PASSIVE"));
        shopItems.add(new ShopItem("shopIcon.png","Neighbour visit","(+600 pierogies)\nYour grandma's sweetheart came here to help!",500,600,"POINTS"));
        shopItems.add(new ShopItem("shopIcon.png","Country club","(+2600 pierogies)\nThe whole village is helping!",2000,2600,"POINTS"));
        shopItems.add(new ShopItem("shopIcon.png","Title","gives you a solid boost!",5000,5600,"POINTS"));
        shopItems.add(new ShopItem("shopIcon.png","Title","gives you a solid boost!",100000,10600,"POINTS"));


        Table innerContainer = new Table();
        innerContainer.padBottom(20f);
        innerContainer.padTop(20f);
        for(ShopItem i : shopItems){
            innerContainer.add(i).expand().fill();
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

    public void setButtons(){
        for(ShopItem i : shopItems) {
            if (game.getScoreService().getPoints() < i.getPrice()) i.getX1().setDisabled(true);
            else i.getX1().setDisabled(false);
            if (game.getScoreService().getPoints() < 10*i.getPrice()) i.getX10().setDisabled(true);
            else i.getX10().setDisabled(false);
        }
    }


}
