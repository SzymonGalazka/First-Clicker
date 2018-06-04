package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.pl.firstclicker.FirstClickerGame;

public class MainMenu extends Table {

    private Label.LabelStyle labelStyle;
    private Image menuBackground;
    private boolean menuHidden;

    public MainMenu(){
        this.setWidth(2*FirstClickerGame.WIDTH/3);
        this.setHeight(FirstClickerGame.HEIGHT/3);
        this.setPosition(FirstClickerGame.WIDTH/6,3*FirstClickerGame.WIDTH/4);
        this.setVisible(false);

        prepareContents();
        init();
    }

    private void init() {
        this.add(new Label("MAIN MENU",labelStyle)).height(100f).padTop(50f);
        this.row();
        this.add(new MenuUIButton(true, new IClickCallback() {
            @Override
            public void onClick() {
                System.out.println("Reseeeet");
            }
        })).padTop(50f);
        this.row();
        this.add(new MenuUIButton(false, new IClickCallback() {
            @Override
            public void onClick() {
               Gdx.app.exit();
            }
        })).padTop(50f);
        this.top();
    }

    public void displayMenu(){
        if(menuHidden){
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
    }
}
