package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.pl.firstclicker.PierogiClicker;

public class MainMenu extends Table {

    private Label.LabelStyle labelStyle, warningLabelStyle;
    private Image menuBackground;
    private boolean menuHidden, warned;
    private PierogiClicker game;
    private Label warningLabel, menuLabel;

    public MainMenu(PierogiClicker game){
        this.game = game;
        this.setWidth(2* PierogiClicker.WIDTH/3);
        this.setHeight(PierogiClicker.HEIGHT/3);
        this.setPosition(PierogiClicker.WIDTH,3* PierogiClicker.WIDTH/4);
        this.setVisible(false);

        prepareContents();
        init();
    }

    private void init() {
        this.add(menuLabel).height(100f).padTop(20f);
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
            animate(false);
            menuHidden = false;
        }else{
            animate(true);
            this.setVisible(true);
            menuHidden = true;
        }
    }

    private void animate(boolean forward) {
        Action moveAction;
        if(forward) {
            moveAction = Actions.sequence(
                    Actions.moveBy(-5*PierogiClicker.WIDTH/6-50, 0, 0.5f, Interpolation.smoother),
                    Actions.moveBy(50,0,0.2f,Interpolation.smoother)
            );
        } else{
            moveAction = Actions.sequence(
                    Actions.moveBy(5*PierogiClicker.WIDTH/6, 0, 0.5f, Interpolation.circleOut)
            );
        }this.addAction(moveAction);
    }

    private void prepareContents() {
        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("scriptfontbig.fnt"));
        warningLabelStyle = new Label.LabelStyle();
        warningLabelStyle.font = new BitmapFont(Gdx.files.internal("descfont.fnt"));
        menuBackground = new Image(new Texture("mainMenuBg.png"));
        this.setBackground(menuBackground.getDrawable());
        menuLabel = new Label("MAIN MENU \n Created by Szymon Galazka",labelStyle);
        menuLabel.setAlignment(Align.center);
        warningLabel = new Label("THIS WILL ERASE \nYOUR WHOLE PROGRESS.\nARE YOU SURE?",warningLabelStyle);
    }

}
