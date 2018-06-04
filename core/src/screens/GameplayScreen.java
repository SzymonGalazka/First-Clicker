package screens;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pl.firstclicker.PierogiClicker;

import controllers.FlyingObjectController;
import controllers.RandomEventController;
import entities.ClickablePierogi;
import entities.Shelves;
import service.FeatureFlagService;
import service.PassiveIncomeService;
import ui.BasicDialog;
import ui.GameLabel;
import ui.IClickCallback;
import ui.MainMenu;
import ui.PlayerButton;
import ui.MainMenuButton;
import ui.ShopButton;
import ui.ShopMenu;

public class GameplayScreen extends AbstractScreen{

    private Image bgImg;
    private ClickablePierogi clickablePierogi1, clickablePierogi2, clickableShadows1, clickableShadows2;
    private PlayerButton playerButton;
    private ShopButton shopButton;
    private ShopMenu shopMenu;
    private Shelves shelves;
    private Button mainMenuButton;
    private GameLabel gameLabel;
    private RandomEventController randomEventController;
    private FlyingObjectController flyingObjectController;
    private PassiveIncomeService passiveIncomeService;
    private boolean pierogiSwitch = false;
    private MainMenu mainMenu;
    private String pointsFormatted, passiveIncomeFormatted;

    public GameplayScreen(PierogiClicker game) {
        super(game);
    }

    @Override
    protected void init(){
        initBg();
        initPierogi();
        initPlayerButton();
        initMainMenu();
        initScoreLabel();
        initFlyingStuffController();
        initPassiveIncomeService();
        initPassiveIncomeDialog();
        initRandomEventController();
        initShop();
    }

    private void initShop() {
        if(game.getFeatureFlagService().hasFeature(FeatureFlagService.FEATURE_SHOP)){
            shopMenu = new ShopMenu(game);
            shopButton = new ShopButton(new IClickCallback() {
                @Override
                public void onClick() {
                    game.getShopService().openShop();
                    shopMenu.displayMenu();
                    if(game.isPaused()) game.setPaused(false);
                    else pause();
                }
            });
            stage.addActor(shopButton);
            stage.addActor(shopMenu);
        }
    }

    private void initRandomEventController() {
        randomEventController = new RandomEventController(game,stage);
    }

    private void initPassiveIncomeDialog() {
        if(passiveIncomeService.getPointsToAdd()>0){
            BasicDialog basicDialog = new BasicDialog();
            basicDialog.showDialog(stage, "Passive income \ngained: " + String.format("%.2f",passiveIncomeService.getPointsToAdd()));
        }
    }

    @Override
    public void render(float delta){
        super.render(delta);
        update();
        //System.out.println("Points:  "+game.getPoints());
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    @Override
    public void pause() {
        super.pause();
        game.getScoreService().saveCurrentGameState();

    }

    private void update() {
        shopMenu.setButtons();
        pointsFormatted = "PIEROGIES: "+String.format("%.2f", game.getScoreService().getPoints());
        passiveIncomeFormatted = "\n\nPASSIVE\nINCOME: "+ String.format("%.2f", game.getScoreService().getPassiveIncome());
        gameLabel.setText(pointsFormatted+passiveIncomeFormatted);
        stage.act();

    }

    private void initPassiveIncomeService() {
        passiveIncomeService = new PassiveIncomeService(game.getScoreService());
        passiveIncomeService.start();
    }

    private void initFlyingStuffController() {
        flyingObjectController = new FlyingObjectController(game, stage);
    }

    private void initBg() {
        bgImg = new Image(new Texture("bg.png"));
        bgImg.setSize(PierogiClicker.WIDTH, PierogiClicker.HEIGHT);
        stage.addActor(bgImg);
        shelves = new Shelves();
        stage.addActor(shelves);

}

    private void initScoreLabel() {
        gameLabel = new GameLabel();
        stage.addActor(gameLabel);
    }

    private void initMainMenu() {
        mainMenu = new MainMenu(game);
        mainMenuButton = new MainMenuButton(new IClickCallback() {
            @Override
            public void onClick() {
                mainMenu.displayMenu();
                if(game.isPaused()) game.setPaused(false);
                else pause();
            }
        });
        stage.addActor(mainMenuButton);
        stage.addActor(mainMenu);
    }

    private void initPlayerButton() {
        playerButton = new PlayerButton(new IClickCallback() {
            @Override
            public void onClick() {

                if(pierogiSwitch){
                    clickablePierogi2.reactOnClick(false);
                    clickableShadows2.reactOnClick(true);
                    pierogiSwitch = false;
                }else {
                    clickablePierogi1.reactOnClick(false);
                    clickableShadows1.reactOnClick(true);
                    pierogiSwitch = true;
                }
                game.getScoreService().addPoint();
            }
        });
        stage.addActor(playerButton);
    }

    private void initPierogi() {
        clickableShadows1 = new ClickablePierogi("Pierogi Shadows 1");
        clickableShadows2 = new ClickablePierogi("Pierogi Shadows 2");
        clickablePierogi1 = new ClickablePierogi("Pierogi Clickable 1");
        clickablePierogi2 = new ClickablePierogi("Pierogi Clickable 2");
        stage.addActor(clickableShadows1);
        stage.addActor(clickableShadows2);
        stage.addActor(clickablePierogi1);
        stage.addActor(clickablePierogi2);
    }
}
