package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pl.firstclicker.PierogiClicker;

import java.util.ArrayList;

import entities.ShopItem;


public class ShopMenu extends Table {


    private ScrollPane scrollPane;
    private ArrayList<ShopItem> shopItems = new ArrayList<ShopItem>();
    private boolean shopHidden;
    private Image shopBg;
    private static PierogiClicker game;

    public ShopMenu(PierogiClicker game) {
        super();
        ShopMenu.game = game;
        this.setWidth(PierogiClicker.WIDTH);
        this.setHeight(PierogiClicker.HEIGHT-280f);
        this.setPosition(0,30);
        this.setVisible(false);
        setBackground();
        showContents();
        this.setX(-PierogiClicker.WIDTH);
    }

    private void setBackground(){
        shopBg = new Image(new Texture("shopBg.png"));
        this.setBackground(shopBg.getDrawable());
    }
    private void showContents(){
        shopItems.add(new ShopItem("BlueEnergyDrink","Blue Energy drink","Gives your grandma a solid boost!",20,0.1f,"PASSIVE"));
        shopItems.add(new ShopItem("RedEnergyDrink","Red Energy drink","Gives youur grandma enormous boost!",50,0.3f,"PASSIVE"));
        shopItems.add(new ShopItem("BlackEnergyDrink","Black Energy drink","Gives your grandma incredible boost!",200,0.5f,"PASSIVE"));
        shopItems.add(new ShopItem("Stock","Chicken Stock","Delicious, healthy grandma's stock!",500,2,"POINTS"));
        shopItems.add(new ShopItem("NoodleStock","Extra Chicken Stock","Stock with tasty handmade noodles!",2000,3,"POINTS"));
        shopItems.add(new ShopItem("BettysVisit","Betty's visit","Grandma's friend came here to help!",5000,5,"POINTS"));
        shopItems.add(new ShopItem("FarmViwes","Farm wives","The whole village is helping!",100000,2,"PASSIVEMULTIPLY"));

        Table innerContainer = new Table();
        innerContainer.pad(50f,0,50f,0);
        for(ShopItem i : shopItems){
            innerContainer.add(i).expand().fill();
            innerContainer.row();
        }
        innerContainer.setClip(true);
        scrollPane = new ScrollPane(innerContainer);
        this.add(scrollPane).fill().expand();

    }

    public void displayMenu(){
            if(shopHidden){
                animate(false);
                shopHidden = false;
            }else{
                animate(true);
                this.setVisible(true);
                shopHidden = true;
            }
    }

    private void animate(boolean forward) {
        Action moveAction;
        if(forward) {
            moveAction = Actions.sequence(
                    Actions.moveBy(PierogiClicker.WIDTH+50, 0, 0.5f, Interpolation.smoother),
                    Actions.moveBy(-50,0,0.2f,Interpolation.smoother)
            );
        } else{
            moveAction = Actions.sequence(
                    Actions.moveBy(-PierogiClicker.WIDTH, 0, 0.5f, Interpolation.circleOut)
            );
        }this.addAction(moveAction);
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
