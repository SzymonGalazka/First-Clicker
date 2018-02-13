package ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.pl.firstclicker.FirstClickerGame;

public class ScoreLabel extends Label{

    public ScoreLabel(){
        super("",prepareLabelStyle());
        init();
    }

    private void init() {
        this.setX(80);
        this.setY(1166);
    }

    private static LabelStyle prepareLabelStyle(){
        LabelStyle labelStyle = new LabelStyle();
        BitmapFont scorefont = new BitmapFont(Gdx.files.internal("poorich.fnt"));

        labelStyle.font = scorefont;
        labelStyle.fontColor = FirstClickerGame.pinky;

        return labelStyle;
    }


}
