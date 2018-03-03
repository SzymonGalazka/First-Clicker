package ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayerButton extends Button{

        public PlayerButton(final IClickCallback callback) {
            super(new ButtonStyle());
            init(callback);
        }

    private void init(final IClickCallback callback) {
        this.setWidth(720);
        this.setHeight(200);
        this.setX(0);
        this.setY(300);
        this.setDebug(false);

        this.addListener(new ClickListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //System.out.println("Click");
                callback.onClick();
                return super.touchDown(event,x,y,pointer,button);
            }

        });

    }
}
