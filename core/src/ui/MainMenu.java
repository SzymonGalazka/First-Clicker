package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pl.firstclicker.PierogiClicker;

public class MainMenu extends Table {

    private Label.LabelStyle labelStyle;
    private Image menuBackground;
    private boolean menuHidden, warned;
    private PierogiClicker game;
    private Label warningLabel;

    public MainMenu(PierogiClicker game){
        this.game = game;
        this.setWidth(2* PierogiClicker.WIDTH/3);
        this.setHeight(PierogiClicker.HEIGHT/3);
        this.setPosition(PierogiClicker.WIDTH/6,3* PierogiClicker.WIDTH/4);
        this.setVisible(false);

        prepareContents();
        init();
    }

    private void init() {
        this.add(new Label("MAIN MENU",labelStyle)).height(100f).padTop(20f);
        this.row();
        this.add(new MenuUIButton(true, new IClickCallback() {
            @Override
            public void onClick() {
                if(!warned) showWarning();
                else {
                    hideWarning();
                    game.getScoreService().resetGameScore();
                }
            }
        })).padTop(120f);
        this.row();
        this.add(new MenuUIButton(false, new IClickCallback() {
            @Override
            public void onClick() {
               Gdx.app.exit();
            }
        })).padTop(50f);
        this.top();
    }

    private void showWarning() {
        this.row();
        this.add(warningLabel).padTop(-400f);
        warned=true;
    }

    private void hideWarning() {
        this.removeActor(warningLabel);
        warned = false;
    }

    public void displayMenu(){
        if(menuHidden){
            hideWarning();
            this.setVisible(false);
            menuHidden = false;
        }else{
            this.setVisible(true);
            menuHidden = true;
        }
    }

    private void prepareContents() {
        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("scriptfontbig.fnt"));
        menuBackground = new Image(new Texture("shopBg.png"));
        this.setBackground(menuBackground.getDrawable());
        warningLabel = new Label("THIS WILL ERASE \nYOUR WHOLE PROGRESS.\nARE YOU SURE?",labelStyle);
    }

}
