package ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BasicDialog extends Image {

    private final static int WIDTH = 720;
    private final static int HEIGHT = 350;

    private GameLabel label;

    public BasicDialog(){
        super(new Texture("dialog.png"));

        this.setOrigin(WIDTH/2,HEIGHT/2);
        this.setSize(WIDTH,HEIGHT);

        this.setPosition(0,465);

        label = new GameLabel();
        label.setPosition(180,640);

        this.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                fadeOutDialog();
                
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    public void initContent(String text){
        label.setText(text);
        this.getStage().addActor(label);
    }

    private void fadeOutDialog() {
        SequenceAction sequence = Actions.sequence();
        sequence.addAction(Actions.fadeOut(1.5f));
        sequence.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                BasicDialog.this.remove();
                label.remove();
                return false;
            }
        });
        this.addAction(sequence);
        label.addAction(Actions.fadeOut(1.5f));
    }
}