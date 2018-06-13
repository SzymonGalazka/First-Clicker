package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.pl.firstclicker.PierogiClicker;

public class BasicDialog extends Image {

    private final static int WIDTH = 720;
    private final static int HEIGHT = 350;

    private GameLabel label;
    private Label.LabelStyle labelStyle;

    public BasicDialog(){
        super(new Texture("dialog.png"));

        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);

        this.setPosition(0, PierogiClicker.HEIGHT/3);

        label = new GameLabel();
        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(Gdx.files.internal("scriptfontbig.fnt"));
        label.setStyle(labelStyle);
        label.setPosition(this.getWidth()/3,this.getY()+this.getHeight()/2);
        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                fadeOutDialog();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void showDialog(Stage stage,String text){
        stage.addActor(this);
        label.setText(text);
        this.getStage().addActor(label);
    }

    private void fadeOutDialog() {
        Action fadeOutAction, labelAction;
        fadeOutAction = Actions.sequence(
                        Actions.moveBy(0, 150, 0.3f, Interpolation.smoother),
                        Actions.moveBy(PierogiClicker.WIDTH,0,1.1f,Interpolation.circleOut)

                );
        labelAction = Actions.sequence(
                Actions.moveBy(0, 150, 0.3f, Interpolation.smoother),
                Actions.moveBy(PierogiClicker.WIDTH,0,1.1f,Interpolation.circleOut)

        );

        label.addAction(labelAction);
        this.addAction(fadeOutAction);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                BasicDialog.this.remove();
                label.remove();
            }
        },1.5f);
    }

}
