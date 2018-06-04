package ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.pl.firstclicker.PierogiClicker;

public class GameLabel extends Label{

    public GameLabel(){
        super("",prepareLabelStyle());
        init();
    }

    private void init() {
        this.setX(PierogiClicker.WIDTH/3+30);
        this.setY(PierogiClicker.HEIGHT-80);
    }

    private static LabelStyle prepareLabelStyle(){
        LabelStyle labelStyle = new LabelStyle();
        BitmapFont scorefont = new BitmapFont(Gdx.files.internal("scriptfont.fnt"));

        labelStyle.font = scorefont;
        labelStyle.fontColor = Color.BLACK;

        return labelStyle;
    }


}
